package ch.fhnw.cpib.iml.parser.concsyntaxtree;

import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IExpr;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IFactor;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IRepMultOprFactor;

public class RepMultOprFactorEpsilon implements IRepMultOprFactor {

  @Override
  public void appendAsString(final StringBuilder sb, final String indent) {

  }

  @Override
  public IExpr toAbsSyn(final IFactor factor) {
    return factor.toAbsSyn();
  }

}
