package ch.fhnw.cpib.iml.parser.concsyntaxtree;

import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IExpr;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IRepBoolOprTerm1;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.ITerm1;

public class RepBoolOprTerm1Epsilon implements IRepBoolOprTerm1 {

  @Override
  public void appendAsString(final StringBuilder sb, final String indent) {

  }

  @Override
  public IExpr toAbsSyn(final ITerm1 term1) {
    return term1.toAbsSyn();
  }

}
