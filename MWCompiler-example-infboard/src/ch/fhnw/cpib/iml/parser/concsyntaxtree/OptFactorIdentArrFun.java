package ch.fhnw.cpib.iml.parser.concsyntaxtree;

import ch.fhnw.cpib.iml.lib.enums.ArrFun;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IExpr;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.StrFunExpr;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IOptFactorIdent;
import ch.fhnw.cpib.iml.scanner.Token;

public class OptFactorIdentArrFun implements IOptFactorIdent {

  /** Either STRLEN or MAXLEN. */
  private final Token<ArrFun> arrFun;

  public OptFactorIdentArrFun(final Token<ArrFun> arrFun) {
    this.arrFun = arrFun;
  }

  @Override
  public void appendAsString(final StringBuilder sb, final String indent) {
    sb.append(indent);
    sb.append(this.arrFun.getTerminal());
    sb.append("\n");
  }

  @Override
  public IExpr toAbsSyn(final Token<String> ident) {
    return new StrFunExpr(ident, this.arrFun);
  }

}
