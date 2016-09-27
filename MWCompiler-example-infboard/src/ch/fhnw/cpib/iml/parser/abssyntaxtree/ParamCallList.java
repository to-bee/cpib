package ch.fhnw.cpib.iml.parser.abssyntaxtree;

import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IExpr;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IList;
import ch.fhnw.cpib.iml.parser.exception.ContextException;
import ch.fhnw.cpib.iml.scanner.Token;
import ch.fhnw.cpib.iml.vm.IVirtualMachine.CodeTooSmallError;
import ch.fhnw.cpib.iml.vm.IVirtualMachine.HeapTooSmallError;

public class ParamCallList implements IList<IExpr> {

  private final IExpr        expr;
  private final IList<IExpr> paramCallList;

  public ParamCallList(final IExpr expr, final IList<IExpr> paramCallList) {
    this.expr = expr;
    this.paramCallList = paramCallList;
  }

  @Override
  public void check() throws ContextException {
    this.expr.check();
    if (!(this.expr instanceof StoreExpr))
      throw new ContextException("Invalid Parameter", this.expr.getToken());
    this.paramCallList.check();
  }

  @Override
  public Token<?> getToken() {
    return this.expr.getToken();
  }

  @Override
  public IExpr getItem() {
    return this.expr;
  }

  @Override
  public IList<IExpr> next() {
    return this.paramCallList;
  }

  @Override
  public int code(final int location) throws CodeTooSmallError, HeapTooSmallError {
    // Nothing to do here
    return location;
  }
}
