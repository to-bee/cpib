package ch.fhnw.cpib.iml.parser.concsyntaxtree;

import ch.fhnw.cpib.iml.parser.abssyntaxtree.ParamCallList;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IExpr;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IOptExpr;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IRepExpr;

public class OptExpr implements IOptExpr {

  private final IExpr    expr;
  private final IRepExpr repExpr;

  public OptExpr(final IExpr expr, final IRepExpr repExpr) {
    this.expr = expr;
    this.repExpr = repExpr;
  }

  @Override
  public void appendAsString(final StringBuilder sb, final String indent) {
    this.expr.appendAsString(sb, indent + " ");
    this.repExpr.appendAsString(sb, indent + " ");
  }

  @Override
  public ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IList<ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IExpr> toAbsSyn() {
    return new ParamCallList(this.expr.toAbsSyn(), this.repExpr.toAbsSyn());
  }

}
