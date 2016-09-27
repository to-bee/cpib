package ch.fhnw.cpib.iml.parser.concsyntaxtree;

import ch.fhnw.cpib.iml.parser.abssyntaxtree.StoreExpr;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IOptArrFactor;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IOptFactorIdent;
import ch.fhnw.cpib.iml.scanner.Token;

public class OptFactorIdentInit implements IOptFactorIdent {

  private final Token<?> init;
  private final IOptArrFactor        optArrFactor;

  public OptFactorIdentInit(final Token<?> init,
      final IOptArrFactor optArrFactor) {
    this.init = init;
    this.optArrFactor = optArrFactor;
  }

  @Override
  public void appendAsString(final StringBuilder sb, final String indent) {
    sb.append(indent);
    sb.append(this.init.getTerminal());
    sb.append("\n");
    this.optArrFactor.appendAsString(sb, indent);
  }

  @Override
  public StoreExpr toAbsSyn(final Token<String> ident) {
    return new StoreExpr(ident, true, this.optArrFactor.toAbsSyn());
  }

}
