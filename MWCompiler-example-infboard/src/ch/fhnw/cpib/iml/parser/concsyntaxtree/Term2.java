package ch.fhnw.cpib.iml.parser.concsyntaxtree;

import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IExpr;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IRepAddOprTerm3;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.ITerm2;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.ITerm3;

public class Term2 implements ITerm2 {

  private final ITerm3          term3;
  private final IRepAddOprTerm3 repAddOprTerm3;

  public Term2(final ITerm3 term3, final IRepAddOprTerm3 repAddOprTerm3) {
    this.term3 = term3;
    this.repAddOprTerm3 = repAddOprTerm3;
  }

  @Override
  public void appendAsString(final StringBuilder sb, final String indent) {
    this.term3.appendAsString(sb, indent);
    this.repAddOprTerm3.appendAsString(sb, indent);
  }

  @Override
  public IExpr toAbsSyn() {
    return this.repAddOprTerm3.toAbsSyn(this.term3.toAbsSyn());
  }

}
