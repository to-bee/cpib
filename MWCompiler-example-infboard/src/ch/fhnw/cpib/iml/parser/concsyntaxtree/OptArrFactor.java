package ch.fhnw.cpib.iml.parser.concsyntaxtree;

import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IExpr;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IArrFactor;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IOptArrFactor;

public class OptArrFactor implements IOptArrFactor {

  private final IArrFactor arrFactor;

  public OptArrFactor(final IArrFactor arrFactor) {
    this.arrFactor = arrFactor;
  }

  @Override
  public void appendAsString(final StringBuilder sb, final String indent) {
    this.arrFactor.appendAsString(sb, indent);
  }

  @Override
  public IExpr toAbsSyn() {
    return this.arrFactor.toAbsSyn();
  }

}
