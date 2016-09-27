package ch.fhnw.cpib.iml.parser.exception;

import ch.fhnw.cpib.iml.scanner.IPosition;
import ch.fhnw.cpib.iml.scanner.Token;

public class ContextException extends RuntimeException {

  private static final long serialVersionUID = 6970979547997893259L;

  private final IPosition   position;

  public ContextException(final String message, final IPosition position) {
    super(msg(message, position));
    this.position = position;
  }

  public IPosition getPosition() {
    return this.position;
  }

  private static String msg(final String message, final IPosition position) {
    if(position != null) {
      final StringBuilder sb = new StringBuilder(message);
      sb.append(" ");
      if(position instanceof Token<?>)
        sb.append(((Token<?>)position).toString());
      sb.append("@");
      sb.append(position.getLineNumber());
      sb.append(":");
      sb.append(position.getColumn());
      return sb.toString();
    }
    return message;
  }
}
