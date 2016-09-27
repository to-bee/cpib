package ch.fhnw.cpib.iml.parser.exception;

import ch.fhnw.cpib.iml.scanner.Terminal;
import ch.fhnw.cpib.iml.scanner.Token;

public class GrammarException extends RuntimeException {

  private static final long serialVersionUID = -540928960714977762L;
  private final String      message;

  public GrammarException(final String message) {
    super();
    this.message = message;
  }

  public GrammarException(final Token<?> actualToken,
      final Terminal... terminals) {
    super();
    final StringBuilder sb = new StringBuilder();
    if (terminals.length == 1) {
      sb.append("Expected: ");
    } else {
      sb.append("Expected one of the following: ");
    }

    for (int i = 0; i < terminals.length; i++) {
      if (i > 0) sb.append(", ");
      sb.append(terminals[i]);
    }

    sb.append(". But was ");
    if (actualToken != null) {
      sb.append(actualToken.getTerminal());
      sb.append(". On line ");
      sb.append(actualToken.getLineNumber());
    } else sb.append("null.");
    this.message = sb.toString();
  }

  @Override
  public String getMessage() {
    return this.message;
  }

}
