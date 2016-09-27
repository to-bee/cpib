package ch.fhnw.cpib.iml.parser.concsyntaxtree;

import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IExpr;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IFactor;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IOptFactorIdent;
import ch.fhnw.cpib.iml.scanner.Token;

public class FactorIdent implements IFactor {

  private final Token<String> ident;
  private final IOptFactorIdent           optFactorIdent;

  public FactorIdent(final Token<String> ident,
      final IOptFactorIdent optFactorIdent) {
    this.ident = ident;
    this.optFactorIdent = optFactorIdent;
  }

  @Override
  public void appendAsString(final StringBuilder sb, final String indent) {
    sb.append(indent);
    sb.append(this.ident.getAttribute());
    sb.append("\n");
    this.optFactorIdent.appendAsString(sb, indent + " ");
  }

  @Override
  public IExpr toAbsSyn() {
    return this.optFactorIdent.toAbsSyn(this.ident);
  }

}
