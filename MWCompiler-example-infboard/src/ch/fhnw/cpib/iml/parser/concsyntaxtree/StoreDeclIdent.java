package ch.fhnw.cpib.iml.parser.concsyntaxtree;

import ch.fhnw.cpib.iml.lib.enums.ChangeMode;
import ch.fhnw.cpib.iml.lib.enums.Type;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.StoreDecl;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IStoreDecl;
import ch.fhnw.cpib.iml.scanner.Token;
import ch.fhnw.cpib.iml.scanner.attr.Attribute;

public class StoreDeclIdent implements IStoreDecl {

  private final Token<String> ident;
  private final Token<?>      colon; // no Attribute!
  private final Token<Type>   type;

  public StoreDeclIdent(final Token<String> ident,
      final Token<?> colon, final Token<Type> type) {
    this.ident = ident;
    this.colon = colon;
    this.type = type;
  }

  @Override
  public void appendAsString(final StringBuilder sb, final String indent) {
    sb.append(indent);
    sb.append(this.ident.getAttribute());
    sb.append("\n");
    sb.append(indent);
    sb.append(this.colon.getTerminal());
    sb.append("\n");
    sb.append(indent);
    sb.append(this.type.getAttribute());
    sb.append("\n");
  }

  @Override
  public StoreDecl toAbsSyn() {
    return new StoreDecl(Attribute.getAttribute(ChangeMode.VAR), this.ident,
        this.type.getAttribute());
  }

}
