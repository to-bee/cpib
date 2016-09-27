package ch.fhnw.cpib.iml.parser.concsyntaxtree;

import ch.fhnw.cpib.iml.lib.enums.AddOpr;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.DyadicExpr;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IExpr;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IRepAddOprTerm3;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.ITerm3;
import ch.fhnw.cpib.iml.scanner.Token;

public class RepAddOprTerm3 implements IRepAddOprTerm3 {

  private final Token<AddOpr>   addOpr;
  private final ITerm3                    term3;
  private final IRepAddOprTerm3           repAddOprTerm3;

  public RepAddOprTerm3(final Token<AddOpr> addOpr,
      final ITerm3 term3, final IRepAddOprTerm3 repAddOprTerm3) {
    this.addOpr = addOpr;
    this.term3 = term3;
    this.repAddOprTerm3 = repAddOprTerm3;
  }

  @Override
  public void appendAsString(final StringBuilder sb, final String indent) {
    sb.append(indent);
    sb.append(this.addOpr.getAttribute());
    sb.append("\n");
    this.term3.appendAsString(sb, indent);
    this.repAddOprTerm3.appendAsString(sb, indent);
  }

  @Override
  public IExpr toAbsSyn(@SuppressWarnings("hiding") final IExpr dyadicExpr) {
    return this.repAddOprTerm3.toAbsSyn(new DyadicExpr(this.addOpr, dyadicExpr,
        this.term3.toAbsSyn()));
  }

}
