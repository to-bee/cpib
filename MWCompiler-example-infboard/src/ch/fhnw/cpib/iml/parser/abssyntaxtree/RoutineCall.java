package ch.fhnw.cpib.iml.parser.abssyntaxtree;

import static ch.fhnw.cpib.iml.compiler.Compiler.COMPILER;
import ch.fhnw.cpib.iml.lib.enums.MechMode;
import ch.fhnw.cpib.iml.parser.ListUtils;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IExpr;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IList;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IParameter;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IRoutineCall;
import ch.fhnw.cpib.iml.parser.exception.ContextException;
import ch.fhnw.cpib.iml.scanner.Token;
import ch.fhnw.cpib.iml.vm.IVirtualMachine;
import ch.fhnw.cpib.iml.vm.IVirtualMachine.CodeTooSmallError;
import ch.fhnw.cpib.iml.vm.IVirtualMachine.HeapTooSmallError;

public class RoutineCall implements IRoutineCall {

  private final Token<String> ident;
  private IList<IExpr>        paramCallList;
  private boolean             isFunc = false;

  private IList<IParameter>   paramList;

  public RoutineCall(final Token<String> ident, final IList<IExpr> paramCallList) {
    this.ident = ident;
    this.paramCallList = paramCallList;
  }

  @Override
  public void check() throws ContextException {
    if (this.paramCallList != null) this.paramCallList.check();

    if (this.isFunc) {
      this.paramList = COMPILER.getCurrentContext().getFunction(this.ident)
          .getParamList();
    } else {
      this.paramList = COMPILER.getCurrentContext().getProcedure(this.ident)
          .getParamList();
    }

    ListUtils.checkLength(this.paramList, this.paramCallList, this.ident);
    ListUtils.checkTypes(this.paramList, this.paramCallList, this.ident);
  }

  @Override
  public Token<String> getToken() {
    return this.ident;
  }

  @Override
  public int code(final int location) throws CodeTooSmallError, HeapTooSmallError {
    final IVirtualMachine vm = COMPILER.getVM();
    int loc = location;
    int routineLocation;

    vm.DebugInfo(loc++, this.getClass().getSimpleName(), this.getToken());

    if (this.isFunc) {
      routineLocation = COMPILER.getCurrentContext().getFunction(this.ident)
          .getLocation();
      vm.Alloc(loc++, 1); // Allocate Stack cell for return variable
    } else {
      routineLocation = COMPILER.getCurrentContext().getProcedure(this.ident)
          .getLocation();
    }

    while (this.paramCallList.getItem() != null) {
      final StoreExpr storeExpr = (StoreExpr) this.paramCallList.getItem();
      final IParameter parameter = this.paramList.getItem();

      if (parameter.getMechMode() == MechMode.REF) storeExpr.setWrite(true);

      loc = storeExpr.code(loc);

      this.paramCallList = this.paramCallList.next();
      this.paramList = this.paramList.next();
    }

    vm.Call(loc++, routineLocation);
    return loc;
  }

  @Override
  public IList<IExpr> getParamCallList() {
    return this.paramCallList;
  }

  @Override
  public void setFunc(final boolean isFunc) {
    this.isFunc = isFunc;
  }

}
