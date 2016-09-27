package ch.fhnw.cpib.iml.scanner;

import ch.fhnw.cpib.iml.scanner.attr.Attribute;

/**
 * A Token in a {@link TokenList}.
 * 
 * @param <A>
 *          The Class of the Attribute. Note that some Token do not have any
 *          attribute (#getAttribute() returns null).
 */
public interface IToken<A> {

  public abstract Terminal getTerminal();

  public abstract Attribute<A> getAttribute();

  @Override
  public abstract String toString();

  @Override
  public abstract int hashCode();

  @Override
  public abstract boolean equals(Object obj);

}