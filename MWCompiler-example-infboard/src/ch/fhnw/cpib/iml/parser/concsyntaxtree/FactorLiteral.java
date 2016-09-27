package ch.fhnw.cpib.iml.parser.concsyntaxtree;

import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.LiteralExpr;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IFactor;
import ch.fhnw.cpib.iml.scanner.Token;

public class FactorLiteral implements IFactor {

  private final Token<int[]> literal;

  public FactorLiteral(final Token<int[]> literal) {
    this.literal = literal;
  }

  @Override
  public void appendAsString(final StringBuilder sb, final String indent) {
    sb.append(indent);
    sb.append(this.literal.getAttribute().getLexem());
    sb.append("\n");
  }

  @Override
  public IAbsSyn.IExpr toAbsSyn() {
    return new LiteralExpr(this.literal);
  }

}
