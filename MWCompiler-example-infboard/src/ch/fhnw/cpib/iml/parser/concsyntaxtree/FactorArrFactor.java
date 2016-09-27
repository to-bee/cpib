package ch.fhnw.cpib.iml.parser.concsyntaxtree;

import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IExpr;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IArrFactor;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IFactor;

public class FactorArrFactor implements IFactor {

  private final IArrFactor arrFactor;

  public FactorArrFactor(final IArrFactor arrFactor) {
    this.arrFactor = arrFactor;
  }

  @Override
  public void appendAsString(final StringBuilder sb, final String indent) {
    this.arrFactor.appendAsString(sb, indent + " ");
  }

  @Override
  public IExpr toAbsSyn() {
    return this.arrFactor.toAbsSyn();
  }

}
