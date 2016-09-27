package ch.fhnw.cpib.iml.scanner;

import java.util.HashMap;
import java.util.Map;

import ch.fhnw.cpib.iml.lib.Chr;

/**
 * Single character aliases for Terminals. This allows the programmer to use
 * Unicode-symbols as synonyms for standard-IML operators and keywords.
 * <p>
 * Examples:
 * <ul>
 * <li><code>≥</code> is the same as <code>&gt;=</code></li>
 * <li><code>÷</code> is the same as <code>/</code></li>
 * </ul>
 *
 * <p>
 * The name of the Enum-Constant is always part of the string representation of
 * the token. <br/>
 * Example: <code>Alias.<b>TIMES</b> &rarr; (MULTOPR, <b>TIMES</b>)</code>
 * <p>
 * Note: So far all symbols fit into one UTF-16 character. It a new symbol needs
 * a surrogate pair this code has to be changed!
 *
 *
 */
public enum Alias {
  CAND('∧', "CAND"),
  COR('∨', "COR"),
  NOT('¬', "NOT"),
  BECOMES('≔', ":="),
  GE('≥', ">="),
  LE('≤', "<="),
  NE('≠', "/="),
  FUN('ƒ', "FUN"),
  DIV('÷', "DIV"),
  TIMES('×', "*"),
  QUESTMARK('→', "?"),
  EXCLAMARK('←', "!");

  /** The unicode symbol that is the alias. */
  private final char   unicode;
  /** Standard IML lexem. */
  private final String iml;
  /**
   * The actual Token.
   *
   * @see Alias#getAlias(String)
   */
  private DistinctToken<?>     token = null;

  private Alias(final char unicode, final String iml) {
    this.unicode = unicode;
    this.iml = iml;
    assert Chr.get(unicode).getCategory() == Chr.Category.SYMBOL : "Chr does not know "
        + unicode;
  }

  public static boolean isAlias(final String lexem) {
    return lexem.length() == 1 && pool.containsKey(lexem.charAt(0));
  }

  public static DistinctToken<?> getAlias(final String lexem) {
    assert lexem.length() == 1;
    final Alias alias = pool.get(lexem.charAt(0));
    if (alias == null) return null;
    if (alias.token == null) alias.token = DistinctToken.getToken(alias.iml);
    return alias.token;
  }

  /**
   * The unicode representation of this alias.
   */
  public Character getUnicode() {
    return this.unicode;
  }

  private final static Map<Character, Alias> pool;
  static {
    pool = new HashMap<>(values().length * 3);
    if (pool.isEmpty()) for (final Alias alias : values())
      pool.put(alias.getUnicode(), alias);
  }
}
