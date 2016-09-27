package ch.fhnw.cpib.iml.scanner;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class TokenList {
  TokenList() {
    // Only package members may create a new TokenList.
  }

  private final List<Token<?>> list     = new LinkedList<>();
  private boolean                       locked   = false;
  private Iterator<Token<?>>   iterator = null;

  /** Prevent modification of this list. */
  public void lock() {
    if (this.locked) return;
    assert !this.list.isEmpty();
    assert this.list.get(this.list.size() - 1).getTerminal() == Terminal.SENTINEL;
    this.locked = true;
    this.reset();
  }

  /** Content of list as string. */
  @Override
  public String toString() {
    return Arrays.toString(this.list.toArray());
  }

  /**
   * This is immutable after {@link #lock()} is called. No need to clone!
   *
   * @see #toArray()
   */
  @Override
  protected TokenList clone() throws CloneNotSupportedException {
    throw new CloneNotSupportedException();
  }

  /** Amount of tokens in this list. */
  public int size() {
    return this.list.size();
  }

  /** <code>True</code>, if no tokens are in this list. */
  public boolean isEmpty() {
    return this.list.isEmpty();
  }

  /** Determine whether some object is in this list. */
  public boolean contains(final Object o) {
    return this.list.contains(o);
  }

  /** Content as array. */
  public IToken<?>[] toArray() {
    return this.list.toArray(new DistinctToken[this.list.size()]);
  }

  /** Add Token. Only allowed if #lock() was not called yet. */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  public boolean add(final DistinctToken<?> t, final int lineNumber, final int column) {
    if (this.locked) throw new UnsupportedOperationException();
    return this.list.add(new Token(t, lineNumber, column));
  }

  /** Reset iteration pointer to beginning of this list. */
  public void reset() {
    this.iterator = this.list.listIterator();
  }

  /** Move iteration pointer by one and return the next Token. */
  public Token<?> nextToken() {
    assert this.locked : "Iteration of TokenList is not allowed when not locked.";
    assert this.iterator != null : "List is locked, but no iterator exists.";
    assert this.iterator.hasNext() : "No more Tokens inTokenList.";
    return this.iterator.next();
  }

  @Override
  public boolean equals(final Object o) {
    if (o == this) return true;
    if (o instanceof TokenList)
      if (((TokenList) o).list.equals(this.list)) return true;
    return false;
  }

  @Override
  public int hashCode() {
    return this.list.hashCode();
  }

  /**
   * Return Token at <code>index</code>. Only for testing!
   *
   * @see TokenList#consume(Terminal)
   */
  public Token<?> get(final int index) {
    return this.list.get(index);
  }
}
