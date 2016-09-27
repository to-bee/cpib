package ch.fhnw.cpib.iml.parser.concsyntaxtree;

import ch.fhnw.cpib.iml.lib.enums.MultOpr;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.DyadicExpr;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IExpr;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IFactor;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IRepMultOprFactor;
import ch.fhnw.cpib.iml.scanner.Token;

public class RepMultOprFactor implements IRepMultOprFactor {

  private final Token<MultOpr> multOpr;
  private final IFactor                    factor;
  private final IRepMultOprFactor          repMultOprFactor;

  public RepMultOprFactor(final Token<MultOpr> multOpr,
      final IFactor factor, final IRepMultOprFactor repMultOprFactor) {
    this.multOpr = multOpr;
    this.factor = factor;
    this.repMultOprFactor = repMultOprFactor;
  }

  @Override
  public void appendAsString(final StringBuilder sb, final String indent) {
    sb.append(indent);
    sb.append(this.multOpr.getAttribute());
    sb.append("\n");
    this.factor.appendAsString(sb, indent + " ");
    this.repMultOprFactor.appendAsString(sb, indent + " ");
  }

  @Override
  public IExpr toAbsSyn(@SuppressWarnings("hiding") final IFactor factor) {
    return new DyadicExpr(this.multOpr, factor.toAbsSyn(),
        this.repMultOprFactor.toAbsSyn(this.factor));
  }

}
