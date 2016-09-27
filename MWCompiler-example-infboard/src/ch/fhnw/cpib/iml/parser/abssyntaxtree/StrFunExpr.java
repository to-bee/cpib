package ch.fhnw.cpib.iml.parser.abssyntaxtree;

import static ch.fhnw.cpib.iml.compiler.Compiler.COMPILER;
import ch.fhnw.cpib.iml.compiler.Variable;
import ch.fhnw.cpib.iml.lib.enums.ArrFun;
import ch.fhnw.cpib.iml.lib.enums.Type;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IExpr;
import ch.fhnw.cpib.iml.parser.exception.ContextException;
import ch.fhnw.cpib.iml.scanner.Token;
import ch.fhnw.cpib.iml.vm.IVirtualMachine;
import ch.fhnw.cpib.iml.vm.IVirtualMachine.CodeTooSmallError;
import ch.fhnw.cpib.iml.vm.IVirtualMachine.HeapTooSmallError;

public class StrFunExpr implements IExpr {

  private final Token<String> ident;

  /**
   * Either MAXLEN or STRLEN
   */
  private final Token<ArrFun> arrFun;

  public StrFunExpr(final Token<String> ident, final Token<ArrFun> arrFun) {
    this.ident = ident;
    this.arrFun = arrFun;
  }

  @Override
  public Token<String> getToken() {
    return this.ident;
  }

  @Override
  public Type getType() {
    return Type.INT32;
  }

  @Override
  public void check() throws ContextException {
    final Variable var = COMPILER.getCurrentContext().getVariable(this.ident);
    if (var == null)
      throw new ContextException("Variable " + this.ident.getAttribute()
          + " not found.", this.ident);

    if (var.getType() != Type.STRING)
      throw new ContextException("Invoke of " + this.arrFun.getAttribute()
          + " only possible on string variables.", this.ident);

    if (!var.isInitialized())
      throw new ContextException("Can't invoke " + this.arrFun.getAttribute()
          + " on uninitialized variable " + this.ident.getAttribute(),
          this.ident);

    if (this.arrFun == null || this.arrFun.getAttribute().getValue() == null)
      throw new ContextException("Array-Function is null", this.ident);
  }

  /** The maximal amount of characters some string can hold. */
  private int max = -1;
  /** The index used on some string. */
  private int idx = -1;

  private void initHeapCells() throws HeapTooSmallError {
    if (this.max > -1) return;
    final IVirtualMachine vm = COMPILER.getVM();
    this.max = vm.IntInitHeapCell();
    this.idx = vm.IntInitHeapCell();
  }

  @Override
  public int code(final int location) throws CodeTooSmallError,
      HeapTooSmallError {
    int loc = location;
    final Variable var = COMPILER.getCurrentContext().getVariable(this.ident);
    final IVirtualMachine vm = COMPILER.getVM();

    final ArrFun af = this.arrFun.getAttribute().getValue();
    final int varRelLoc = var.getRelLocation();
    if (af == ArrFun.MAXLEN) {
      vm.LoadRel(loc++, varRelLoc); // address of variable
      vm.Deref(loc++);// address of string
      vm.Deref(loc++);// maxlen
    } else if (af == ArrFun.STRLEN) {
      // @formatter:off
      /*
        max := str.MAXLEN;
        idx := 1;
        if(max > 0)
          while(idx <= max) {
            if(str[idx] == 0) break;
            idx++;
          }
        return idx-1;
       */
      // @formatter:on

      this.initHeapCells();
      vm.DebugInfo(loc++, "STRLEN of " + this.ident.getAttribute().toString(),
          this.ident);
      vm.LoadRel(loc++, varRelLoc); // address of variable
      vm.Deref(loc++);// address of string
      vm.Deref(loc++);// maxlen
      // vm.DebugInfo(loc++, "maxlen on stack", this.ident);

      // Index begins at 1:
      vm.IntLoad(loc++, 1);
      vm.IntLoad(loc++, this.idx);
      vm.Store(loc++);

      // is it 0? -> Empty String.
      final int jumpMax0 = loc++;
      vm.Alloc(loc++, 1);// maxlen again

      vm.IntLoad(loc++, this.max);
      vm.Store(loc++);

      final int locLoop = loc;
      vm.IntLoad(loc++, this.idx);
      vm.Deref(loc++);
      vm.IntLoad(loc++, this.max);
      vm.Deref(loc++);

      vm.IntLE(loc++);
      final int jmpMax = loc++;

      vm.LoadRel(loc++, varRelLoc); // address of variable
      vm.Deref(loc++);// address of string
      vm.IntLoad(loc++, this.idx);
      vm.Deref(loc++);
      vm.IntAdd(loc++);
      vm.Deref(loc++);
      // vm.DebugInfo(loc++, "char is now on stack", this.ident);

      final int jmpChar0 = loc++;

      // vm.DebugInfo(loc++, "Char > 0 -> Increment the index", this.ident);
      vm.IntLoad(loc++, this.idx); // index
      vm.Deref(loc++);
      vm.IntLoad(loc++, 1); // plus 1
      vm.IntAdd(loc++);
      vm.IntLoad(loc++, this.idx);
      vm.Store(loc++);
      // vm.DebugInfo(loc++, "reloop", this.ident);
      vm.UncondJump(loc++, locLoop);

      vm.CondJump(jumpMax0, loc);
      vm.CondJump(jmpMax, loc);
      vm.CondJump(jmpChar0, loc);

      vm.IntLoad(loc++, this.idx); // index of first 0 or maxlen
      vm.Deref(loc++);
      vm.IntLoad(loc++, 1);
      vm.IntSub(loc++); // -1

      // Leave the Result on the Stack
      // vm.DebugInfo(loc++, "STRLEN finished", this.ident);

    } else {
      throw new RuntimeException("Unknown: " + af);
    }
    return loc;
  }

}
