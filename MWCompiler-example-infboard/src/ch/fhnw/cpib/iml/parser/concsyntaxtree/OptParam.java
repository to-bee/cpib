package ch.fhnw.cpib.iml.parser.concsyntaxtree;

import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IList;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IParameter;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.ParameterList;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IOptParam;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IParam;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IRepParam;

public class OptParam implements IOptParam {

  private final IParam param;
  private final IRepParam repParam;

  public OptParam(final IParam param, final IRepParam repParam) {
    this.param = param;
    this.repParam = repParam;
  }

  @Override
  public void appendAsString(final StringBuilder sb, final String indent) {
    this.param.appendAsString(sb, indent + " ");
    this.repParam.appendAsString(sb, indent + " ");
  }

  @Override
  public IList<IParameter> toAbsSyn() {
    return new ParameterList(this.param.toAbsSyn(), this.repParam.toAbsSyn());
  }

}
