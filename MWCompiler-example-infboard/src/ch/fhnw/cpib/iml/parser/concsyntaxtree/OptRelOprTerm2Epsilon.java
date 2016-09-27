package ch.fhnw.cpib.iml.parser.concsyntaxtree;

import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IExpr;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IOptRelOprTerm2;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.ITerm2;

public class OptRelOprTerm2Epsilon implements IOptRelOprTerm2 {

  @Override
  public void appendAsString(final StringBuilder sb, final String indent) {

  }

  @Override
  public IExpr toAbsSyn(final ITerm2 term2) {
    return term2.toAbsSyn();
  }

}
