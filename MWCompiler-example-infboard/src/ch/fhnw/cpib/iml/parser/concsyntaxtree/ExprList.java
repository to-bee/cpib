package ch.fhnw.cpib.iml.parser.concsyntaxtree;

import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IExpr;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IList;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IExprList;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IOptExpr;
import ch.fhnw.cpib.iml.scanner.Token;

public class ExprList implements IExprList {

  private final Token<?> lparen;
  private final IOptExpr             optExpr;
  private final Token<?> rparen;

  public ExprList(final Token<?> lparen, final IOptExpr optExpr,
      final Token<?> rparen) {
    this.lparen = lparen;
    this.optExpr = optExpr;
    this.rparen = rparen;
  }

  @Override
  public void appendAsString(final StringBuilder sb, final String indent) {
    sb.append(indent);
    sb.append(this.lparen.getTerminal());
    sb.append("\n");
    this.optExpr.appendAsString(sb, indent + " ");
    sb.append(indent);
    sb.append(this.rparen.getTerminal());
    sb.append("\n");
  }

  @Override
  public IList<IExpr> toAbsSyn() {
    return this.optExpr.toAbsSyn();
  }
}
