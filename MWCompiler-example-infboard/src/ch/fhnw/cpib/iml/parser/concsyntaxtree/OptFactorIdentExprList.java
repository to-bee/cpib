package ch.fhnw.cpib.iml.parser.concsyntaxtree;

import ch.fhnw.cpib.iml.parser.abssyntaxtree.FunCallExpr;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IExpr;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.RoutineCall;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IExprList;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IOptFactorIdent;
import ch.fhnw.cpib.iml.scanner.Token;

public class OptFactorIdentExprList implements IOptFactorIdent {

  private final IExprList exprList;

  public OptFactorIdentExprList(final IExprList exprList) {
    this.exprList = exprList;
  }

  @Override
  public void appendAsString(final StringBuilder sb, final String indent) {
    this.exprList.appendAsString(sb, indent + " ");
  }

  @Override
  public IExpr toAbsSyn(final Token<String> ident) {
    return new FunCallExpr(new RoutineCall(ident, this.exprList.toAbsSyn()));
  }

}
