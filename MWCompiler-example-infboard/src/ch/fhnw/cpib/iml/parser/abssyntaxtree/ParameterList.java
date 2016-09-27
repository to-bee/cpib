package ch.fhnw.cpib.iml.parser.abssyntaxtree;

import static ch.fhnw.cpib.iml.compiler.Compiler.COMPILER;
import ch.fhnw.cpib.iml.parser.ListUtils;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IList;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IParameter;
import ch.fhnw.cpib.iml.parser.exception.ContextException;
import ch.fhnw.cpib.iml.scanner.Token;
import ch.fhnw.cpib.iml.vm.IVirtualMachine;
import ch.fhnw.cpib.iml.vm.IVirtualMachine.CodeTooSmallError;
import ch.fhnw.cpib.iml.vm.IVirtualMachine.HeapTooSmallError;

public class ParameterList implements IList<IParameter> {

  private final IParameter     param;
  private final IList<IParameter> paramList;

  public ParameterList(final IParameter param, final IList<IParameter> paramList) {
    this.param = param;
    this.paramList = paramList;
  }

  @Override
  public void check() throws ContextException {
    this.param.setLocationInParamList(ListUtils.length(this.paramList) + 1);
    this.param.check();
    this.paramList.check();
  }

  @Override
  public Token<?> getToken() {
    return this.param.getToken();
  }

  @Override
  public IParameter getItem() {
    return this.param;
  }

  @Override
  public IList<IParameter> next() {
    return this.paramList;
  }

  @Override
  public int code(final int location) throws CodeTooSmallError,
      HeapTooSmallError {
    final IVirtualMachine vm = COMPILER.getVM();
    int loc = location;
    vm.DebugInfo(loc++, this.getClass().getSimpleName(), this.getToken());
    loc = this.param.code(loc);
    loc = this.paramList.code(loc);
    return loc;
  }

  @Override
  public String toString() {
    return ListUtils.toString(this);
  }
}
