package ch.fhnw.cpib.iml.parser.concsyntaxtree;

import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IList;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IParameter;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IOptParam;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IParamList;
import ch.fhnw.cpib.iml.scanner.Token;

public class ParamList implements IParamList {

  private final Token<?> lparen;
  private final IOptParam            optParam;
  private final Token<?> rparen;

  public ParamList(final Token<?> lparen, final IOptParam optParam,
      final Token<?> rparen) {
    this.lparen = lparen;
    this.optParam = optParam;
    this.rparen = rparen;
  }

  @Override
  public void appendAsString(final StringBuilder sb, final String indent) {
    sb.append(indent);
    sb.append(this.lparen.getTerminal());
    sb.append("\n");
    this.optParam.appendAsString(sb, indent + " ");
    sb.append(indent);
    sb.append(this.rparen.getTerminal());
    sb.append("\n");
  }

  @Override
  public IList<IParameter> toAbsSyn() {
    return this.optParam.toAbsSyn();
  }

}
