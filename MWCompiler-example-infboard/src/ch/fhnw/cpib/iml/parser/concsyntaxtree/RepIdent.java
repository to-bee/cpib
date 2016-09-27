package ch.fhnw.cpib.iml.parser.concsyntaxtree;

import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IIdent;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IList;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.Ident;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.IdentList;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IOptArrFactor;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IRepIdent;
import ch.fhnw.cpib.iml.scanner.Token;

public class RepIdent implements IRepIdent {

  private final Token<?>      comma;
  private final Token<String> ident;
  private final IOptArrFactor optArrFactor;
  private final IRepIdent                 repIdent;

  public RepIdent(final Token<?> comma,
 final Token<String> ident,
      final IOptArrFactor optArrFactor, final IRepIdent repIdent) {
    this.comma = comma;
    this.ident = ident;
    this.optArrFactor = optArrFactor;
    this.repIdent = repIdent;
  }

  @Override
  public void appendAsString(final StringBuilder sb, final String indent) {
    sb.append(indent);
    sb.append(this.comma.getTerminal());
    sb.append("\n");
    sb.append(indent);
    sb.append(this.ident.getAttribute());
    sb.append("\n");
    this.optArrFactor.appendAsString(sb, indent + " ");
    this.repIdent.appendAsString(sb, indent + " ");
  }

  @Override
  public IList<IIdent> toAbsSyn() {
    return new IdentList(new Ident(this.ident, this.optArrFactor.toAbsSyn()),
        this.repIdent.toAbsSyn());
  }

}
