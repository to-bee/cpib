package ch.fhnw.cpib.iml.parser.concsyntaxtree;

import ch.fhnw.cpib.iml.lib.enums.BoolOpr;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.DyadicExpr;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IExpr;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IRepBoolOprTerm1;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.ITerm1;
import ch.fhnw.cpib.iml.scanner.Token;

public class RepBoolOprTerm1 implements IRepBoolOprTerm1 {

  private final Token<BoolOpr> boolOpr;
  private final ITerm1                     term1;
  private final IRepBoolOprTerm1           repBoolOprTerm1;

  public RepBoolOprTerm1(final Token<BoolOpr> boolOpr,
      final ITerm1 term1, final IRepBoolOprTerm1 repBoolOprTerm1) {
    this.boolOpr = boolOpr;
    this.term1 = term1;
    this.repBoolOprTerm1 = repBoolOprTerm1;
  }

  @Override
  public void appendAsString(final StringBuilder sb, final String indent) {
    sb.append(indent);
    sb.append(this.boolOpr.getAttribute());
    sb.append("\n");
    this.term1.appendAsString(sb, indent);
    this.repBoolOprTerm1.appendAsString(sb, indent);
  }

  @Override
  public IExpr toAbsSyn(@SuppressWarnings("hiding") final ITerm1 term1) {
    return new DyadicExpr(this.boolOpr, term1.toAbsSyn(), this.repBoolOprTerm1
        .toAbsSyn(this.term1));
  }

}
