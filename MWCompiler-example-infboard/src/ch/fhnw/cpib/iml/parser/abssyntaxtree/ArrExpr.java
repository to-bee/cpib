package ch.fhnw.cpib.iml.parser.abssyntaxtree;

import static ch.fhnw.cpib.iml.compiler.Compiler.COMPILER;
import ch.fhnw.cpib.iml.lib.enums.Type;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IExpr;
import ch.fhnw.cpib.iml.parser.exception.ContextException;
import ch.fhnw.cpib.iml.scanner.Token;
import ch.fhnw.cpib.iml.scanner.attr.AttrIntVal;
import ch.fhnw.cpib.iml.scanner.attr.Attribute;
import ch.fhnw.cpib.iml.vm.IVirtualMachine;
import ch.fhnw.cpib.iml.vm.IVirtualMachine.CodeTooSmallError;
import ch.fhnw.cpib.iml.vm.IVirtualMachine.HeapTooSmallError;

public class ArrExpr implements IExpr {

  private final IExpr sizeExpr;

  public ArrExpr(final IExpr sizeExpr) {
    this.sizeExpr = sizeExpr;
  }

  @Override
  public void check() throws ContextException {
    this.sizeExpr.check();
    if (this.sizeExpr instanceof LiteralExpr) {
      final LiteralExpr literal = (LiteralExpr) this.sizeExpr;
      final Attribute<int[]> attribute = literal.getToken().getAttribute();
      if (!(attribute instanceof AttrIntVal))
        throw new ContextException("Index must be INT32. "
            + attribute.getLexem() + " given.", literal.getToken());
      final int i = attribute.getValue()[0];
      if (i < 0)
        throw new ContextException("Index must be greater than 0.", literal
            .getToken());
    }
  }

  @Override
  public Token<?> getToken() {
    return this.sizeExpr.getToken();
  }

  @Override
  public Type getType() {
    // Note: Both the index and the value at the index are INT32.
    return Type.INT32;
  }

  @Override
  public int code(final int location) throws CodeTooSmallError, HeapTooSmallError {
    final IVirtualMachine vm = COMPILER.getVM();
    int loc = location;
    vm.DebugInfo(loc++, this.getClass().getSimpleName(), this.getToken());
    loc = this.sizeExpr.code(location + 1);
    // Parent must call IntAdd! This simply leves the int32-value on the stack.
    return loc;
  }

}
