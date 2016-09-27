package ch.fhnw.cpib.iml.parser.concsyntaxtree;

import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IExpr;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IFactor;
import ch.fhnw.cpib.iml.scanner.Token;

public class FactorLparen implements IFactor {

  private final Token<?> lparen;
  private final IExpr             expr;
  private final Token<?> rparen;

  public FactorLparen(final Token<?> lparen, final IExpr expr,
      final Token<?> rparen) {
    this.lparen = lparen;
    this.expr = expr;
    this.rparen = rparen;
  }

  @Override
  public void appendAsString(final StringBuilder sb, final String indent) {
    sb.append(indent);
    sb.append(this.lparen.getTerminal());
    sb.append("\n");
    this.expr.appendAsString(sb, indent + " ");
    sb.append(indent);
    sb.append(this.rparen.getTerminal());
    sb.append("\n");
  }

  @Override
  public ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IExpr toAbsSyn() {
    return this.expr.toAbsSyn();
  }

}
