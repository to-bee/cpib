package ch.fhnw.cpib.iml.parser.concsyntaxtree;

import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IExpr;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IRepBoolOprTerm1;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.ITerm1;

public class Expr implements IExpr {

  private final ITerm1           term1;
  private final IRepBoolOprTerm1 repBoolOprTerm1;

  public Expr(final ITerm1 term1, final IRepBoolOprTerm1 repBoolOprTerm1) {
    this.term1 = term1;
    this.repBoolOprTerm1 = repBoolOprTerm1;
  }

  @Override
  public void appendAsString(final StringBuilder sb, final String indent) {
    this.term1.appendAsString(sb, indent);
    this.repBoolOprTerm1.appendAsString(sb, indent);
  }

  @Override
  public ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IExpr toAbsSyn() {
    return this.repBoolOprTerm1.toAbsSyn(this.term1);
  }

}
