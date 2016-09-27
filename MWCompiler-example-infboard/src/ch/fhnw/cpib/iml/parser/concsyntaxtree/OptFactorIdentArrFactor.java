package ch.fhnw.cpib.iml.parser.concsyntaxtree;

import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IExpr;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.StoreExpr;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IArrFactor;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IOptFactorIdent;
import ch.fhnw.cpib.iml.scanner.Token;

public class OptFactorIdentArrFactor implements IOptFactorIdent {

  private final IArrFactor arrFactor;

  public OptFactorIdentArrFactor(final IArrFactor arrFactor) {
    this.arrFactor = arrFactor;
  }

  @Override
  public void appendAsString(final StringBuilder sb, final String indent) {
    this.arrFactor.appendAsString(sb, indent + " ");
  }

  @Override
  public IExpr toAbsSyn(final Token<String> ident) {
    return new StoreExpr(ident, false, this.arrFactor.toAbsSyn());
  }

}
