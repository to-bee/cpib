package ch.fhnw.cpib.iml.parser.concsyntaxtree;

import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IExpr;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IOptRelOprTerm2;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.ITerm1;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.ITerm2;

public class Term1 implements ITerm1 {

  private final ITerm2          term2;
  private final IOptRelOprTerm2 optRelOprTerm2;

  public Term1(final ITerm2 term2, final IOptRelOprTerm2 optRelOprTerm2) {
    this.term2 = term2;
    this.optRelOprTerm2 = optRelOprTerm2;
  }

  @Override
  public void appendAsString(final StringBuilder sb, final String indent) {
    this.term2.appendAsString(sb, indent);
    this.optRelOprTerm2.appendAsString(sb, indent);
  }

  @Override
  public IExpr toAbsSyn() {
    return this.optRelOprTerm2.toAbsSyn(this.term2);
  }

}
