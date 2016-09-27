package ch.fhnw.cpib.iml.parser.concsyntaxtree;

import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IExpr;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IFactor;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IRepMultOprFactor;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.ITerm3;

public class Term3 implements ITerm3 {

  private final IFactor           factor;
  private final IRepMultOprFactor repMultOprFactor;

  public Term3(final IFactor factor, final IRepMultOprFactor repMultOprFactor) {
    this.factor = factor;
    this.repMultOprFactor = repMultOprFactor;
  }

  @Override
  public void appendAsString(final StringBuilder sb, final String indent) {
    this.factor.appendAsString(sb, indent);
    this.repMultOprFactor.appendAsString(sb, indent);
  }

  @Override
  public IExpr toAbsSyn() {
    return this.repMultOprFactor.toAbsSyn(this.factor);
  }

}
