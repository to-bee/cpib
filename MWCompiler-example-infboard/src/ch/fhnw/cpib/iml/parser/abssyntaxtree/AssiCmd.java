package ch.fhnw.cpib.iml.parser.abssyntaxtree;

import static ch.fhnw.cpib.iml.compiler.Compiler.COMPILER;
import ch.fhnw.cpib.iml.compiler.Compiler;
import ch.fhnw.cpib.iml.compiler.Variable;
import ch.fhnw.cpib.iml.lib.enums.ChangeMode;
import ch.fhnw.cpib.iml.lib.enums.Type;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.ICmd;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IExpr;
import ch.fhnw.cpib.iml.parser.exception.ContextException;
import ch.fhnw.cpib.iml.scanner.Token;
import ch.fhnw.cpib.iml.vm.IVirtualMachine;
import ch.fhnw.cpib.iml.vm.IVirtualMachine.CodeTooSmallError;
import ch.fhnw.cpib.iml.vm.IVirtualMachine.HeapTooSmallError;

/**
 * Assignment.
 *
 * <p>
 * Example: <code>X := 123;</code>
 * */
public class AssiCmd implements ICmd {

  private final IExpr lExpr;
  private final IExpr rExpr;

  public AssiCmd(final IExpr lExpr, final IExpr rExpr) {
    this.lExpr = lExpr;
    this.rExpr = rExpr;
  }

  @Override
  public void check() throws ContextException {
    final Token<?> token = this.lExpr.getToken();
    if (!(this.lExpr instanceof StoreExpr))
      throw new ContextException("Lefthandside is not a variable.", token);

    final StoreExpr storeExpr = (StoreExpr) this.lExpr;
    storeExpr.setWrite(true);

    final Variable var1 = Compiler.COMPILER.getCurrentContext().getVariable(
        storeExpr.getToken());
    if (var1 == null)
      throw new ContextException("Varibale does not exist.", token);
    if (var1.getChangeMode() == ChangeMode.CONST && var1.isInitialized())
      throw new ContextException("Cannot assign value to CONST.", token);

    this.rExpr.check();
    final Type t = this.rExpr.getType();

    // The code is something like: str init := [4+2];
    if (var1.getType() == Type.STRING && t == Type.INT32) {
      if (!storeExpr.hasArrSizeExpr() && !storeExpr.isInit())
        throw new ContextException(
            "Invalid type on assignment (Missing INIT on initialisation of string?).",
            token);
    } else if (t != var1.getType())
      throw new ContextException("Invalid type on assignment. left: "
          + var1.getType() + " right: " + t, token);

    this.lExpr.check();
    this.rExpr.check();
  }

  @Override
  public Token<?> getToken() {
    return this.lExpr.getToken();
  }

  /**
   * This is the address of a cell on the heap which is used to store an address
   * of a string which is also on the heap.
   */
  private int strL = -1, strR = -1;
  /** The maximal amount of characters some string can hold. */
  private int max  = -1;
  /**
   * The index used on some string. 0 is where MAXLEN is stored. 1 is the first
   * character (if MAXLEN > 0).
   */
  private int idx  = -1;

  /**
   * These cells are used like registers, since the VM does not have them.
   *
   * @throws HeapTooSmallError
   */
  private void initHeapCells() throws HeapTooSmallError {
    if (this.strL > -1) return;
    final IVirtualMachine vm = Compiler.COMPILER.getVM();
    this.strL = vm.IntInitHeapCell();
    this.strR = vm.IntInitHeapCell();
    this.max = vm.IntInitHeapCell();
    this.idx = vm.IntInitHeapCell();
  }

  @Override
  public int code(final int location) throws CodeTooSmallError,
      HeapTooSmallError {
    final IVirtualMachine vm = COMPILER.getVM();
    final Variable var = Compiler.COMPILER.getCurrentContext().getVariable(
        ((StoreExpr) this.lExpr).getToken());
    int loc = location;
    vm.DebugInfo(loc++, this.getClass().getSimpleName() + ": " + var.getName()
        + " := ?", this.getToken());
    if (var.getType() == Type.STRING && this.rExpr.getType() == Type.INT32) {
      if (((StoreExpr) this.lExpr).hasArrSizeExpr()) {
        // Store only one Character:
        vm.DebugInfo(loc++, "str[X] := ? -> rExpr", this.rExpr.getToken());
        loc = this.rExpr.code(loc);
        vm.DebugInfo(loc++, "str[X] := ? -> lExpr", this.lExpr.getToken());
        loc = this.lExpr.code(loc);
        vm.DebugInfo(loc++, "str[X] := ? -> Store()", this.lExpr.getToken());
        vm.Store(loc++);
      } else {
        // Allocate empty String:
        this.initHeapCells();
        // The length (int-value of rExpr) is only known at runtime.
        // ArrExpr will put the value on top of the stack.
        // On the heap we need length + 1 of cells (as array of integers).
        // --> Load 1 and run ArrExpr and add the values:
        loc = this.rExpr.code(loc);
        vm.IntLoad(loc++, this.max);
        vm.Store(loc++);
        vm.Alloc(loc++, 1);

        // MAXLEN + 1:
        vm.IntLoad(loc++, 1);
        vm.IntAdd(loc++);
        // Set the MAXLEN:
        // Creates the array on the heap and leaves the address on the stack.
        vm.IntArrayInitHeap(loc++);
        vm.IntLoad(loc++, this.strR);
        vm.Store(loc++); // save address
        vm.IntLoad(loc++, this.max);
        vm.Deref(loc++);
        vm.IntLoad(loc++, this.strR);
        vm.Deref(loc++);
        vm.Store(loc++); // store MAXLEN
        // Get address back
        vm.IntLoad(loc++, this.strR);
        vm.Deref(loc++);

        // The address of that array will be the actual rExpr.
        // Now store that to lExpr:
        loc = this.lExpr.code(loc);
        vm.Store(loc++);
      }
    } else if (var.getType() == Type.STRING
        && this.rExpr.getType() == Type.STRING) {
      this.initHeapCells();

      // Copy all Characters from right to left!!!
      // Note that we only have two addresses on the stack right now!
      // -> Copy only up to MIN(rExpr.MAXLEN, lExpr.MAXLEN) of characters!
      // -> Use heap cells as it is done on DyadicExpr instead of registers.
      // -> Stop at first Zero in rExpr.
      // -> Fill with Zeroes (if rExpr is shorter).
      // -> Ignore rest if lExpr is shorter.

      // If lExpr is StoreExpr with INIT -> lExpr must be initialized first!
      loc = this.rExpr.code(loc);
      vm.IntLoad(loc++, this.strR);
      vm.Store(loc++);
      vm.Alloc(loc++, 1);// Reload address of rExpr-String
      vm.Deref(loc++); // Now it is MAXLEN

      // Get address of lExpr-String on the stack:
      loc = this.lExpr.code(loc);
      // lExpr should be a StoreExpr of type STRING.
      if (this.lExpr.getType() == Type.STRING
          && ((StoreExpr) this.lExpr).isInit()) {
        vm.DebugInfo(loc++, "isInit", null);
        vm.IntLoad(loc++, this.strL);// cell of StoreDelc
        vm.Store(loc++);
        vm.IntLoad(loc++, this.max);
        vm.Store(loc++);
        vm.Alloc(loc++, 1);
        vm.DebugInfo(loc++, "MAXLEN + 1", null);
        // MAXLEN + 1:
        vm.IntLoad(loc++, 1);
        vm.IntAdd(loc++);
        vm.IntArrayInitHeap(loc++);
        vm.IntLoad(loc++, this.strL);
        vm.Deref(loc++); // cell of StoreDecl
        vm.Store(loc++);

        vm.IntLoad(loc++, this.max);
        vm.Deref(loc++);

        vm.IntLoad(loc++, this.strL);
        vm.Deref(loc++); // cell of StoreDecl
        vm.Deref(loc++); // address of string

        vm.IntLoad(loc++, this.strL);
        vm.Store(loc++);
        vm.Alloc(loc++, 1); // address of string again

        vm.Store(loc++); // store MAXLEN
        vm.DebugInfo(loc++, "isInit finished", null);
      } else {
        vm.Deref(loc++); // Address of String
        vm.IntLoad(loc++, this.strL);
        vm.Store(loc++);
        vm.Alloc(loc++, 1);// Reload address of lExpr-String
        vm.Deref(loc++); // Now it is MAXLEN

        vm.IntLT(loc++);
        vm.CondJump(loc++, loc + 2); // IF
        vm.IntLoad(loc++, this.strR); // THEN
        vm.UncondJump(loc++, loc + 1);
        vm.IntLoad(loc++, this.strL); // ELSE

        vm.Deref(loc++); // address of string
        vm.Deref(loc++); // Now we have the minimal length
        vm.IntLoad(loc++, this.max);
        vm.Store(loc++);
      }

      vm.IntLoad(loc++, 1);
      vm.IntLoad(loc++, this.idx);
      vm.Store(loc++);

      final int locLoopA = loc;
      vm.IntLoad(loc++, this.idx);
      vm.Deref(loc++);
      vm.IntLoad(loc++, this.max);
      vm.Deref(loc++);
      vm.IntLE(loc++);

      final int locJumpA1 = loc++;// MAXLEN reached
      // Value (Character):
      vm.IntLoad(loc++, this.strR);
      vm.Deref(loc++);
      vm.IntLoad(loc++, this.idx);// offset
      vm.Deref(loc++);
      vm.IntAdd(loc++);
      vm.Deref(loc++); // character at *strR[idx]
      final int locJumpA2 = loc++; // character is 0
      vm.Alloc(loc++, 1); // Get character back.
      // Destination:
      vm.IntLoad(loc++, this.strL);
      vm.Deref(loc++);
      vm.IntLoad(loc++, this.idx);// offset
      vm.Deref(loc++);
      vm.IntAdd(loc++);

      vm.Store(loc++); // Copy character

      vm.IntLoad(loc++, this.idx);
      vm.Deref(loc++);
      vm.IntLoad(loc++, 1);
      vm.IntAdd(loc++);
      vm.IntLoad(loc++, this.idx);
      vm.Store(loc++);

      vm.UncondJump(loc++, locLoopA); // back to WHILE

      vm.CondJump(locJumpA2, loc);// character is 0
      vm.CondJump(locJumpA1, loc);// MAXLEN reached (but which one?!)

      final int locLoopB = loc;
      vm.IntLoad(loc++, this.idx);
      vm.Deref(loc++);
      vm.IntLoad(loc++, this.strL); // address of strL
      vm.Deref(loc++);// address of String
      vm.Deref(loc++);// MAXLEN
      vm.IntLE(loc++);
      final int locJumpB1 = loc++;
      vm.IntLoad(loc++, 0); // '\0'
      vm.IntLoad(loc++, this.strL);
      vm.Deref(loc++);
      vm.IntLoad(loc++, this.idx);// offset
      vm.Deref(loc++);
      vm.IntAdd(loc++);

      vm.Store(loc++); // Copy '\0'

      vm.IntLoad(loc++, this.idx);
      vm.Deref(loc++);
      vm.IntLoad(loc++, 1);
      vm.IntAdd(loc++);
      vm.IntLoad(loc++, this.idx);
      vm.Store(loc++);

      vm.UncondJump(loc++, locLoopB); // back to WHILE
      vm.CondJump(locJumpB1, loc);// MAXLEN reached

    } else {
      loc = this.rExpr.code(loc);
      loc = this.lExpr.code(loc);
      vm.Store(loc++);
    }
    return loc;
  }
}
