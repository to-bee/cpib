package ch.fhnw.cpib.iml.parser.concsyntaxtree;

import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IList;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IParameter;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.ParameterList;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IParam;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IRepParam;
import ch.fhnw.cpib.iml.scanner.Token;

public class RepParam implements IRepParam {

  private final Token<?> comma;
  private final IParam               param;
  private final IRepParam            repParam;

  public RepParam(final Token<?> comma, final IParam param,
      final IRepParam repParam) {
    this.comma = comma;
    this.param = param;
    this.repParam = repParam;
  }

  @Override
  public void appendAsString(final StringBuilder sb, final String indent) {
    sb.append(indent);
    sb.append(this.comma.getTerminal());
    sb.append("\n");
    this.param.appendAsString(sb, indent + " ");
    this.repParam.appendAsString(sb, indent + " ");
  }

  @Override
  public IList<IParameter> toAbsSyn() {
    return new ParameterList(this.param.toAbsSyn(), this.repParam.toAbsSyn());
  }

}
