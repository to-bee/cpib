package ch.fhnw.cpib.iml.parser.concsyntaxtree;

import ch.fhnw.cpib.iml.lib.enums.ChangeMode;
import ch.fhnw.cpib.iml.lib.enums.Type;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.StoreDecl;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IStoreDecl;
import ch.fhnw.cpib.iml.scanner.Token;

public class StoreDeclChangeMode implements IStoreDecl {
  private final Token<ChangeMode> changeMode;
  private final Token<String>     ident;
  private final Token<?>          colon;
  private final Token<Type>       type;

  public StoreDeclChangeMode(final Token<ChangeMode> changeMode,
      final Token<String> ident, final Token<?> colon,
      final Token<Type> type) {
    this.changeMode = changeMode;
    this.ident = ident;
    this.colon = colon;
    this.type = type;
  }

  @Override
  public void appendAsString(final StringBuilder sb, final String indent) {
    sb.append(indent);
    sb.append(this.changeMode.getTerminal());
    sb.append("\n");
    sb.append(indent);
    sb.append(this.ident.getAttribute());
    sb.append("\n");
    sb.append(indent);
    sb.append(":\n");
    sb.append(indent);
    sb.append(this.type.getAttribute());
    sb.append("\n");
  }

  @Override
  public StoreDecl toAbsSyn() {
    return new StoreDecl(this.changeMode.getAttribute(), this.ident, this.type
        .getAttribute());
  }

}
