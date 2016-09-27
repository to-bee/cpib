package ch.fhnw.cpib.iml.parser.concsyntaxtree;

import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IExpr;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IRepAddOprTerm3;

public class RepAddOprTerm3Epsilon implements IRepAddOprTerm3 {

  @Override
  public void appendAsString(final StringBuilder sb, final String indent) {

  }

  @Override
  public IExpr toAbsSyn(final IExpr dyadicExpr) {
    return dyadicExpr;
  }

}
