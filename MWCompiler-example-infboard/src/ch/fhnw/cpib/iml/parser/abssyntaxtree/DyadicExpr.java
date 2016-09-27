package ch.fhnw.cpib.iml.parser.abssyntaxtree;

import java.util.Arrays;
import java.util.List;

import ch.fhnw.cpib.iml.compiler.Compiler;
import ch.fhnw.cpib.iml.lib.enums.AddOpr;
import ch.fhnw.cpib.iml.lib.enums.BoolOpr;
import ch.fhnw.cpib.iml.lib.enums.IAttrEnum;
import ch.fhnw.cpib.iml.lib.enums.MultOpr;
import ch.fhnw.cpib.iml.lib.enums.RelOpr;
import ch.fhnw.cpib.iml.lib.enums.Type;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IExpr;
import ch.fhnw.cpib.iml.parser.exception.ContextException;
import ch.fhnw.cpib.iml.scanner.Token;
import ch.fhnw.cpib.iml.vm.IVirtualMachine;
import ch.fhnw.cpib.iml.vm.IVirtualMachine.CodeTooSmallError;
import ch.fhnw.cpib.iml.vm.IVirtualMachine.HeapTooSmallError;

public class DyadicExpr implements IExpr {

  private final Token<? extends IAttrEnum> operator;
  private final IExpr                      lExpr;
  private final IExpr                      rExpr;
  private Type                             type = null;

  public DyadicExpr(final Token<? extends IAttrEnum> operator,
      final IExpr lExpr, final IExpr rExpr) {
    this.operator = operator;
    this.lExpr = lExpr;
    this.rExpr = rExpr;
  }

  @Override
  public void check() throws ContextException {
    this.lExpr.check();

    final IAttrEnum op = this.operator.getAttribute().getValue();
    if (op instanceof RelOpr) this.type = Type.BOOL;
    else this.type = this.lExpr.getType();

    this.rExpr.check();

    if (this.lExpr.getType() != this.lExpr.getType())
      throw new ContextException("Two different types in dyadic expression.",
          this.operator);

    if (this.lExpr.getType() == Type.STRING) {
      // Allowed Operators for Strings:
      final List<? extends Enum<?>> allowed = Arrays.asList(RelOpr.EQ,
          RelOpr.NE, AddOpr.PLUS);
      if (!allowed.contains(op))
        throw new ContextException("Operator not allowed for stings: " + op,
            this.operator);
    }
  }

  @Override
  public Token<?> getToken() {
    return this.operator;
  }

  @Override
  public Type getType() {
    return this.type;
  }

  @Override
  public int code(final int location) throws CodeTooSmallError,
      HeapTooSmallError {
    final IVirtualMachine vm = Compiler.COMPILER.getVM();
    vm.DebugInfo(location, this.getClass().getSimpleName(), this.getToken());
    final int loc1 = this.lExpr.code(location + 1);
    // TODO: CAND and COR are conditional. rExpr is not always to be executed!
    final int loc2 = this.rExpr.code(loc1);
    final IAttrEnum opr = this.operator.getAttribute().getValue();
    if (opr instanceof AddOpr) {
      switch ((AddOpr) opr) {
      case PLUS:
        if (this.lExpr.getType() == Type.INT32) vm.IntAdd(loc2);
        else return this.codeConcat(loc2);
        return loc2 + 1;
      case MINUS:
        vm.IntSub(loc2);
        return loc2 + 1;
      default:
        throw new RuntimeException("Unknown Operator: " + opr);
      }
    } else if (opr instanceof MultOpr) {
      switch ((MultOpr) opr) {
      case DIV:
        vm.IntDiv(loc2);
        return loc2 + 1;
      case MOD:
        vm.IntMod(loc2);
        return loc2 + 1;
      case TIMES:
        vm.IntMult(loc2);
        return loc2 + 1;
      default:
        throw new RuntimeException("Unknown Operator: " + opr);
      }
    } else if (opr instanceof RelOpr) {
      switch ((RelOpr) opr) {
      case EQ:
        switch (this.lExpr.getType()) {
        case INT32:
        case BOOL:
          vm.IntEQ(loc2);
          return loc2 + 1;
        case STRING:
          return this.codeStrEq(loc2, false);
        default:
          throw new RuntimeException("Unknown Type: " + this.lExpr.getType());
        }
      case GE:
        vm.IntGE(loc2);
        return loc2 + 1;
      case GT:
        vm.IntGT(loc2);
        return loc2 + 1;
      case LE:
        vm.IntLE(loc2);
        return loc2 + 1;
      case LT:
        vm.IntLT(loc2);
        return loc2 + 1;
      case NE:
        switch (this.lExpr.getType()) {
        case INT32:
        case BOOL:
          vm.IntNE(loc2);
          return loc2 + 1;
        case STRING:
          return this.codeStrEq(loc2, true);
        default:
          throw new RuntimeException("Unknown Type: " + this.lExpr.getType());
        }
      default:
        throw new RuntimeException("Unknown Operator: " + opr);
      }
    } else if (opr instanceof BoolOpr) {
      // TODO: CAND and COR are conditional. rExpr is not always to be
      // executed!
      switch ((BoolOpr) opr) {
      case CAND:
        switch (this.lExpr.getType()) {
        case INT32:
        case BOOL:
          vm.IntMult(loc2);
          return loc2 + 1;
        case STRING:
        default:
          throw new RuntimeException("Illegal/Unknown Type: "
              + this.lExpr.getType());
        }
      case COR:
        switch (this.lExpr.getType()) {
        case INT32:
        case BOOL:
          vm.IntAdd(loc2); // 0 = FALSE / 1,2 = TRUE
          return loc2 + 1;
        case STRING:
        default:
          throw new RuntimeException("Illegal/Unknown Type: "
              + this.lExpr.getType());
        }
      default:
        throw new RuntimeException("Unknown Operator: " + opr);
      }// switch
    }
    throw new RuntimeException("Unknown Operator: " + opr);
  }

  private int codeConcat(final int location) throws HeapTooSmallError,
      CodeTooSmallError {
    this.initHeapCells();
    final IVirtualMachine vm = Compiler.COMPILER.getVM();
    int loc = location;

    // See where the strings are:
    // We have two addresses to strings on the stack.
    // --> Copy both so we don't lose them.
    vm.DebugInfo(loc++, "Will concatenate two strings.", null);
    vm.IntLoad(loc++, this.str2);
    vm.Store(loc++);
    vm.IntLoad(loc++, this.str1);
    vm.Store(loc++);

    // Get them back and load both MAXLENs:
    vm.IntLoad(loc++, this.str1);
    vm.Deref(loc++); // load address of String
    vm.Deref(loc++); // load MAXLEN of String
    vm.IntLoad(loc++, this.str2);
    vm.Deref(loc++); // load address of String
    vm.Deref(loc++); // load MAXLEN of String

    // Add values to get new MAXLEN:
    vm.IntAdd(loc++);

    //vm.DebugInfo(loc++, "Total MAXLEN now on stack.", null);

    // Now we have the new MAXLEN for the new string.
    // --> put on heap:
    vm.IntLoad(loc++, this.max);
    vm.Store(loc++);

    // Get it back again:
    vm.IntLoad(loc++, this.max);
    vm.Deref(loc++); // load MAXLEN of String
    vm.IntLoad(loc++, 1);
    vm.IntAdd(loc++);

    // Array will have size of MAXLEN + 1
    vm.IntArrayInitHeap(loc++);

    // Store address of new string on heap:
    vm.IntLoad(loc++, this.str3);
    vm.Store(loc++);

    vm.IntLoad(loc++, this.max);
    vm.Deref(loc++);// Total MAXLEN
    vm.IntLoad(loc++, this.str3);
    vm.Deref(loc++);// Address of str3
    vm.Store(loc++); // Store MAXLEN to str3[0]

    // Now load all characters of str1 and copy to str3

    //vm.DebugInfo(loc++, "Copy first String...", null);
    { // str1
      // Begin at Index 1.
      vm.IntLoad(loc++, 1);
      vm.IntLoad(loc++, this.idx);
      vm.Store(loc++);

      final int locLoop = loc;

      // run until MAXLEN is reached.
      // WHILE idx <= max DO
      vm.IntLoad(loc++, this.idx); // index
      vm.Deref(loc++);
      vm.IntLoad(loc++, this.str1);
      vm.Deref(loc++);
      vm.Deref(loc++);// MAXLEN of str1
      vm.IntLE(loc++);

      final int jmpStr1Max = loc++; // MAXLEN reached -> str1 is finished

      //vm.DebugInfo(loc++, "Copy next Char of str1", null);
      vm.IntLoad(loc++, this.str1);
      vm.Deref(loc++); // address of str1 (idx=0)
      vm.IntLoad(loc++, this.idx); // index
      vm.Deref(loc++);
      vm.IntAdd(loc++); // Add index to address
      vm.Deref(loc++); // load character

      // Stop if character is 0:
      final int jmpStr1Char0 = loc++; // character is 0 -> str1 is finished

      // Get Char back -> increase the SP by one:
      vm.Alloc(loc++, 1);
      //vm.DebugInfo(loc++, "Char is on stack now.", null);

      // Now get address where the char is copied to:
      vm.IntLoad(loc++, this.str3);
      vm.Deref(loc++); // address of str3 (idx=0)
      vm.IntLoad(loc++, this.idx); // index
      vm.Deref(loc++);
      vm.IntAdd(loc++); // Add index to address

      // Copy character:
      vm.Store(loc++);
      //vm.DebugInfo(loc++, "Char is now in destination string.", null);

      // Increment the index:
      vm.IntLoad(loc++, this.idx); // index
      vm.Deref(loc++);
      vm.IntLoad(loc++, 1); // plus 1
      vm.IntAdd(loc++);
      vm.IntLoad(loc++, this.idx);
      vm.Store(loc++);
      //vm.DebugInfo(loc++, "Reloop.", null);
      vm.UncondJump(loc++, locLoop);

      // Add cond jumps above:
      vm.CondJump(jmpStr1Max, loc);
      vm.CondJump(jmpStr1Char0, loc);
    } // str1

    // Index does not change since it's used on str3!
    //vm.DebugInfo(loc++, "Copy second String...", null);
    { // str2
      // NOTE: We no longer use str1, so str1 is now used for index in str2!
      // Begin at Index 1.
      vm.IntLoad(loc++, 1);
      vm.IntLoad(loc++, this.str1); // Used for index!!
      vm.Store(loc++);

      final int locLoop = loc;
      // run until MAXLEN is reached.
      // WHILE idx of str2 <= max of str2 DO
      vm.IntLoad(loc++, this.str1); // index of str2
      vm.Deref(loc++);
      vm.IntLoad(loc++, this.str2); // MAXLEN of str2
      vm.Deref(loc++);
      vm.Deref(loc++);
      vm.IntLE(loc++);

      final int jmpStr2Max = loc++; // MAXLEN reached -> str2 is finished

      vm.IntLoad(loc++, this.str2);
      vm.Deref(loc++); // address of str2 (idx=0)
      vm.IntLoad(loc++, this.str1); // index
      vm.Deref(loc++);
      vm.IntAdd(loc++); // Add index to address
      vm.Deref(loc++); // load character

      // Stop if character is 0:
      final int jmpStr2Char0 = loc++; // character is 0 -> str2 is finished

      // This would be a lot simpler if we could just increase the SP by one:
      vm.IntLoad(loc++, this.str2);
      vm.Deref(loc++); // address of str2 (idx=0)
      vm.IntLoad(loc++, this.str1); // index of str2
      vm.Deref(loc++);
      vm.IntAdd(loc++); // Add index to address
      vm.Deref(loc++); // load character

      // Now get address where the char is copied to:
      vm.IntLoad(loc++, this.str3);
      vm.Deref(loc++); // address of str3 (idx=0)
      vm.IntLoad(loc++, this.idx); // index of str3
      vm.Deref(loc++);
      vm.IntAdd(loc++); // Add index to address

      // Copy character:
      vm.Store(loc++);

      // Increment the index of str3:
      vm.IntLoad(loc++, this.idx); // index
      vm.Deref(loc++);
      vm.IntLoad(loc++, 1); // plus 1
      vm.IntAdd(loc++);
      vm.IntLoad(loc++, this.idx);
      vm.Store(loc++);

      // Increment the index of str2:
      vm.IntLoad(loc++, this.str1); // str1 holds index of str2!
      vm.Deref(loc++);
      vm.IntLoad(loc++, 1); // plus 1
      vm.IntAdd(loc++);
      vm.IntLoad(loc++, this.str1);
      vm.Store(loc++);

      vm.UncondJump(loc++, locLoop);

      // Add cond jumps above:
      vm.CondJump(jmpStr2Max, loc);
      vm.CondJump(jmpStr2Char0, loc);

    }
    // Now load the address of str3:
    vm.IntLoad(loc++, this.str3);
    vm.Deref(loc++);

    vm.DebugInfo(loc++, "Concatenation finished.", null);

    return loc;
  }

  /**
   * This is the address of a cell on the heap which is used to store an address
   * of a string which is also on the heap.
   */
  private int str1 = -1, str2 = -1, str3 = -1;
  /** The maximal amount of characters some string can hold. */
  private int max = -1;
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
    if (this.str1 > -1) return;
    final IVirtualMachine vm = Compiler.COMPILER.getVM();
    this.str1 = vm.IntInitHeapCell();
    this.str2 = vm.IntInitHeapCell();
    this.str3 = vm.IntInitHeapCell();
    this.max = vm.IntInitHeapCell();
    this.idx = vm.IntInitHeapCell();
  }

  private int codeStrEq(final int location, final boolean not)
      throws HeapTooSmallError, CodeTooSmallError {
    final IVirtualMachine vm = Compiler.COMPILER.getVM();
    int loc = location;

    this.initHeapCells();

    // See where the strings are:
    // We have two addresses to strings on the stack.
    // --> Copy both so we don't lose them.
    vm.IntLoad(loc++, this.str1);
    vm.Store(loc++);
    vm.IntLoad(loc++, this.str2);
    vm.Store(loc++);

    // Get them back and load both MAXLENs:
    vm.IntLoad(loc++, this.str1);
    vm.Deref(loc++); // load address of String
    vm.Deref(loc++); // load MAXLEN of String
    vm.IntLoad(loc++, this.str2);
    vm.Deref(loc++); // load address of String
    vm.Deref(loc++); // load MAXLEN of String

    vm.IntLT(loc++); // compare MAXLENs

    vm.CondJump(loc++, loc + 2); // jump two
    // then: If the first one is shorter:
    vm.IntLoad(loc++, this.str1);
    vm.UncondJump(loc++, loc + 1);
    // else: If the second one is shorter:
    vm.IntLoad(loc++, this.str2);

    vm.Deref(loc++); // load address of shortest String
    vm.Deref(loc++); // load minimal MAXLEN

    // Now we have the MAXLEN we use to prevent an endless loop.
    // --> put on heap:
    vm.IntLoad(loc++, this.max);
    vm.Store(loc++);

    // index on heap too:
    vm.IntLoad(loc++, 1); // = index
    vm.IntLoad(loc++, this.idx);
    vm.Store(loc++);

    // WHILE INDEX <= MAXLEN DO...:
    final int locLoop = loc;
    loc++;
    vm.IntLoad(loc++, this.idx);
    vm.Deref(loc++);
    vm.IntLoad(loc++, this.max);
    vm.Deref(loc++);

    vm.IntLE(loc++);

    final int jmpTrue = loc++;// -> MAXLEN reached. result is (not) true.

    // load two characters:
    vm.IntLoad(loc++, this.str1);
    vm.IntLoad(loc++, this.idx);
    vm.Deref(loc++);
    vm.IntAdd(loc++);
    vm.Deref(loc++);

    vm.IntLoad(loc++, this.str2);
    vm.IntLoad(loc++, this.idx);
    vm.Deref(loc++);
    vm.IntAdd(loc++);
    vm.Deref(loc++);

    // Increment idx:
    vm.IntLoad(loc++, this.idx);
    vm.Deref(loc++);
    vm.IntLoad(loc++, 1);
    vm.IntAdd(loc++);
    vm.IntLoad(loc++, this.idx);
    vm.Store(loc++);

    // compare the two characters:
    vm.IntEQ(loc++);
    final int jmpFalse = loc++; // --> return (not) false!

    vm.UncondJump(loc++, locLoop);

    // set result to (not) true:
    final int locTrue = loc;
    vm.IntLoad(loc++, not ? 0 : 1);
    vm.UncondJump(loc++, loc + 1);
    // set result to (not) false:
    final int locFalse = loc;
    vm.IntLoad(loc++, not ? 1 : 0);

    // Now set the jumps:
    vm.CondJump(jmpTrue, locTrue);
    vm.CondJump(jmpFalse, locFalse);

    return loc;
  }
}
