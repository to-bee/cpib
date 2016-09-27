package ch.fhnw.cpib.iml.parser.concsyntaxtree;

import ch.fhnw.cpib.iml.parser.abssyntaxtree.Empty;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IExpr;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.StoreExpr;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IOptFactorIdent;
import ch.fhnw.cpib.iml.scanner.Token;

public class OptFactorIdentEpsilon implements IOptFactorIdent {

  @Override
  public void appendAsString(final StringBuilder sb, final String indent) {

  }

  @Override
  public IExpr toAbsSyn(final Token<String> ident) {
    return new StoreExpr(ident, false, new Empty());
  }

}
