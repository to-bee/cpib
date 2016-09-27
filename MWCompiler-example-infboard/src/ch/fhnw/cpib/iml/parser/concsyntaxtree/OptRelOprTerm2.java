package ch.fhnw.cpib.iml.parser.concsyntaxtree;

import ch.fhnw.cpib.iml.lib.enums.RelOpr;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.DyadicExpr;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IExpr;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IOptRelOprTerm2;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.ITerm2;
import ch.fhnw.cpib.iml.scanner.Token;

public class OptRelOprTerm2 implements IOptRelOprTerm2 {

  private final Token<RelOpr> relOpr;
  private final ITerm2            term2;

  public OptRelOprTerm2(final Token<RelOpr> relOpr,
      final ITerm2 term2) {
    this.relOpr = relOpr;
    this.term2 = term2;
  }

  @Override
  public void appendAsString(final StringBuilder sb, final String indent) {
    sb.append(indent);
    sb.append(this.relOpr.getAttribute());
    sb.append("\n");
    this.term2.appendAsString(sb, indent);
  }

  @Override
  public IExpr toAbsSyn(@SuppressWarnings("hiding") final ITerm2 term2) {
    return new DyadicExpr(this.relOpr, term2.toAbsSyn(), this.term2.toAbsSyn());
  }

}
