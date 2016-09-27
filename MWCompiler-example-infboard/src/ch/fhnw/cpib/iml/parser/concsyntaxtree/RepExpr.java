package ch.fhnw.cpib.iml.parser.concsyntaxtree;

import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IList;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.ParamCallList;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IExpr;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IRepExpr;
import ch.fhnw.cpib.iml.scanner.Token;

public class RepExpr implements IRepExpr {

  private final Token<?> comma;
  private final IExpr                expr;
  private final IRepExpr             repExpr;

  public RepExpr(final Token<?> comma, final IExpr expr,
      final IRepExpr repExpr) {
    this.comma = comma;
    this.expr = expr;
    this.repExpr = repExpr;
  }

  @Override
  public void appendAsString(final StringBuilder sb, final String indent) {
    sb.append(indent);
    sb.append(this.comma.getTerminal());
    sb.append("\n");
    this.expr.appendAsString(sb, indent + " ");
    this.repExpr.appendAsString(sb, indent + " ");
  }

  @Override
  public IList<ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IExpr> toAbsSyn() {
    return new ParamCallList(this.expr.toAbsSyn(), this.repExpr.toAbsSyn());
  }

}
