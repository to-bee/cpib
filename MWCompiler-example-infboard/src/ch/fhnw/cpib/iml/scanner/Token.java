package ch.fhnw.cpib.iml.scanner;

import ch.fhnw.cpib.iml.scanner.attr.Attribute;

/**
 * A Token with information about the occurrence in the source code.
 * Each Token references a {@link DistinctToken} that holds the terminal and
 * attribute.
 * All calls to methods of the IToken-interface are redirected to that
 * DistinctToken. This includes {@link #hashCode()} and {@link #equals(Object)}.
 */
public class Token<A> implements IToken<A>, IPosition {

  private final DistinctToken<A> token;
  private final int              lineNumber;
  private final int              column;

  public Token(final DistinctToken<A> token, final int lineNumber,
      final int column) {
    super();
    assert token != null;
    assert lineNumber >= 1;
    assert column >= 1;
    this.token = token;
    this.lineNumber = lineNumber;
    this.column = column;
  }

  @Override
  public Terminal getTerminal() {
    return this.token.getTerminal();
  }

  @Override
  public Attribute<A> getAttribute() {
    return this.token.getAttribute();
  }

  @Override
  public String toString() {
    return this.token.toString();
  }

  @Override
  public int hashCode() {
    return this.token.hashCode();
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) return true;
    if (null == obj) return false;
    if (obj instanceof Token) return this.token.equals(((Token<?>) obj).token);
    return this.token.equals(obj);
  }

  @Override
  public int getLineNumber() {
    return this.lineNumber;
  }

  @Override
  public int getColumn() {
    return this.column;
  }

}
