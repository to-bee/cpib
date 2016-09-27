package ch.fhnw.cpib.iml.parser.abssyntaxtree;

import ch.fhnw.cpib.iml.compiler.Compiler;
import ch.fhnw.cpib.iml.lib.enums.Type;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IExpr;
import ch.fhnw.cpib.iml.parser.exception.ContextException;
import ch.fhnw.cpib.iml.scanner.Token;
import ch.fhnw.cpib.iml.vm.IVirtualMachine;
import ch.fhnw.cpib.iml.vm.IVirtualMachine.CodeTooSmallError;
import ch.fhnw.cpib.iml.vm.IVirtualMachine.HeapTooSmallError;

public class LiteralExpr implements IExpr {

  /**
   * Either BoolVal, IntVal or String
   */
  private final Token<int[]> token;

  public LiteralExpr(final Token<int[]> token) {
    this.token = token;
  }

  @Override
  public Token<int[]> getToken() {
    return this.token;
  }

  private Type type = null;

  @Override
  public void check() throws ContextException {
    // nothing to be checked.
    this.type = Type.ofLiteral(this.token);
  }

  @Override
  public Type getType() {
    return this.type;
  }


  @Override
  public int code(final int location) throws CodeTooSmallError, HeapTooSmallError {
    final IVirtualMachine vm = Compiler.COMPILER.getVM();
    int loc = location;
    vm.DebugInfo(loc++, this.getClass().getSimpleName() + "=" + this.token,
        this.token);

    switch (this.type) {
    case BOOL:
    case INT32:
      vm.IntLoad(location, this.token.getAttribute().getValue()[0]);
      return location + 1;
    case STRING:
      final int[] value = this.token.getAttribute().getValue();
      // value[0] stores the number of characters following:
      assert value[0] == value.length - 1; // = maxlen

      for (int i = 0; i < value.length; i++)
        vm.IntLoad(loc++, value[i]);
      vm.IntLoad(loc++, value.length); // this is for StoreIntArray
      vm.IntLoad(loc++, value.length); // this is for IntArrayInitHeap
      vm.IntArrayInitHeap(loc++); // This leaves the address
      vm.StoreIntArray(loc++);// This also leaves the address
      return loc; // we return with the address on the stack.
    default:
      break;
    }
    throw new RuntimeException("Type of literal unkown: " + this.token);
  }

}
