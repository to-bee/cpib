package ch.fhnw.cpib.iml.scanner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ch.fhnw.cpib.iml.lib.Chr;
import ch.fhnw.cpib.iml.lib.Keyword;
import ch.fhnw.cpib.iml.scanner.attr.AttrIdent;
import ch.fhnw.cpib.iml.scanner.attr.AttrIntVal;
import ch.fhnw.cpib.iml.scanner.attr.AttrStrVal;
import ch.fhnw.cpib.iml.scanner.attr.Attribute;
import ch.fhnw.cpib.iml.scanner.exception.SyntaxException;

/**
 * A Token, created by the scanner, but distinct for all terminals and
 * attributes.
 *
 * @param <A>
 *          The Type of the Attribute (&lt;?&gt; if it has no attribute or the
 *          type is unknown);
 */
public final class DistinctToken<A> implements IToken<A> {
  private static Map<String, DistinctToken<?>> pool = new HashMap<>(150);

  private final Terminal                       terminal;
  private final Attribute<A>                   attribute;                // can
                                                                          // be
                                                                          // null
  private final int                            hash;
  private final String                         string;

  public static DistinctToken<?> getToken(final List<Chr> lexem) {
    return getToken(Chr.toString(lexem));
  }

  @SuppressWarnings("rawtypes")
  public static DistinctToken<?> getToken(final String lexem) {
    assert lexem != null && !lexem.isEmpty();
    if (lexem.charAt(0) == AttrStrVal.DELIMITER)
      return new DistinctToken(lexem);
    if (Alias.isAlias(lexem)) return Alias.getAlias(lexem);
    final String upper = lexem.toUpperCase();
    if (pool.containsKey(upper)) return pool.get(upper);
    final DistinctToken<?> token = new DistinctToken<Object>(lexem, upper);
    pool.put(upper, token);
    return token;
  }

  /**
   * Constructor for case-sensitive lexems, i.e. string literals.
   */
  @SuppressWarnings("unchecked")
  private DistinctToken(final String lexem) {
    super();
    assert lexem != null;
    this.terminal = Terminal.LITERAL;
    this.attribute = (Attribute<A>) new AttrStrVal(lexem);
    this.string = String.format("(%s,%s)", this.terminal.toString(),
        this.attribute.toString());
    this.hash = this.terminal.hashCode() + 31 * lexem.hashCode();
  }

  /**
   * Constructor for case-insensitive lexems.
   */
  @SuppressWarnings("unchecked")
  private DistinctToken(final String lexem, final String upper) {
    super();
    try {
      assert (lexem == null) == (upper == null);
      if (lexem == null) {
        this.terminal = Terminal.SENTINEL;
        this.attribute = null;
        return;
      }
      assert lexem.toUpperCase().equals(upper);
      assert lexem.length() > 0 : "Token: Empty Lexem.";
      assert lexem.equals(lexem.trim()) : "Token: Bad Lexem '" + lexem + "'";

      // Is it a Terminal without Attribute (Keyword)?
      if (Terminal.getTerminalsWithoutAttribute().containsKey(upper)) {
        this.terminal = Terminal.getTerminalsWithoutAttribute().get(upper);
        this.attribute = null;
        return;
      }

      if (Symbol.isSymbol(upper)) {
        this.terminal = Symbol.getSymbol(upper).getTerminal();
        this.attribute = null;
        return;
      }

      // Is it a known Attribute?
      if (Attribute.isAttribute(upper)) {
        final Attribute<A> a = (Attribute<A>) Attribute.getAttribute(upper);
        this.terminal = a.getTerminal();
        this.attribute = a;
        return;
      }

      // Is it an Integer Literal?
      final char first = lexem.charAt(0);
      if (first >= '0' && first <= '9') {
        // It's a LITERAL!
        assert lexem.matches("^[0-9]+$") : "Literal is not /[0-9]+/: " + lexem;
        this.terminal = Terminal.LITERAL;
        this.attribute = (Attribute<A>) new AttrIntVal(lexem);
        return;
      }

      if ((first >= 'a' && first <= 'z') || (first >= 'A' && first <= 'Z')) {
        // Didn't match anything yet -> Must be Ident!
        assert !Keyword.isKeyword(upper) : "Keyword " + upper
            + " is not handled correctly!";
        assert lexem.matches("^[a-zA-Z][a-zA-Z0-9]*$");
        this.terminal = Terminal.IDENT;
        this.attribute = (Attribute<A>) new AttrIdent(lexem);
        return;
      }

      throw new SyntaxException("Bad syntax on lexem: '" + lexem + "'");

    } finally {
      try {
        assert this.terminal != null;
        assert (this.attribute != null) == this.terminal.hasAttribute();
        assert this.attribute == null
            || this.attribute.getTerminal() == this.terminal;
      } catch (final AssertionError e) {
        throw new AssertionError("Token: Bad Lexem '" + lexem + "'", e);
      }
      this.hash = this.terminal.hashCode() + 31
          * (this.attribute == null ? 0 : this.attribute.hashCode());
      this.string = (this.attribute == null) ? this.terminal.toString()
          : String.format("(%s,%s)", this.terminal.toString(), this.attribute
              .toString());
    }
  }

  /**
   * The SENTINEL-Token that is at the End Of File.
   */
  public static DistinctToken<?> getEOF() {
    return new DistinctToken<Object>(null, null);
  }

  @Override
  public Terminal getTerminal() {
    return this.terminal;
  }

  @Override
  public Attribute<A> getAttribute() {
    return this.attribute;
  }

  @Override
  public String toString() {
    return this.string;
  }

  @Override
  public int hashCode() {
    return this.hash;
  }

  @Override
  public boolean equals(final Object obj) {
    return this == obj;
  }

}
