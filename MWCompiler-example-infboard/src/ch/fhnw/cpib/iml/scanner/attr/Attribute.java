package ch.fhnw.cpib.iml.scanner.attr;

import java.util.HashMap;

import ch.fhnw.cpib.iml.lib.enums.ChangeMode;
import ch.fhnw.cpib.iml.lib.enums.FlowMode;
import ch.fhnw.cpib.iml.lib.enums.IAttrEnum;
import ch.fhnw.cpib.iml.lib.enums.MultOpr;
import ch.fhnw.cpib.iml.scanner.IToken;
import ch.fhnw.cpib.iml.scanner.Terminal;

/**
 * Attribute for a {@link IToken (Distinct)Token}.
 * <p>
 * Use {@link #toString()} to get the String representation, {@link #getLexem()}
 * to get the Lexem, and {@link #getValue()} to get the value of generic type
 * {@link V}.
 *
 * <p>
 * The generic Type is:
 * <dl>
 * <dt>?:</dt>
 * <dd>No Attribute (null) or unknown Type</dd>
 * <dt>String:</dt>
 * <dd>Name of an Identifier ({@link AttrIdent}).</dd>
 * <dt>{@link IAttrEnum}:</dt>
 * <dd>One of an Enumeration ({@link FlowMode}, {@link MultOpr}...)</dd>
 * <dt>int[]:</dt>
 * <dd>The data of a Variable ({@link AttrLiteral}: int32, bool, string)</dd>
 * </dl>
 * <p>
 *
 * @param V
 *          Generic Type of the attribute.
 */
public abstract class Attribute<V> {

  protected final String lexem;

  protected final V      value;

  protected Attribute(final String lexem, final V value) {
    assert lexem != null;
    this.lexem = lexem;
    this.value = value;
    assert this instanceof AttrStrVal || !pool.containsKey(lexem);
    pool.put(lexem, this);
  }

  @Override
  abstract public String toString();

  /**
   * Lexem mapped to Attribute.
   */
  private static HashMap<String, Attribute<?>> pool = new HashMap<>(150);

  static public Attribute<?> getAttribute(final String lexem) {
    return pool.get(lexem);
  }

  /**
   * Attribute of any Enum of <tt>ch.fhnw.cpib.iml.lib.enums</tt>.
   *
   * @param e
   *          Enum-Object (e.g. {@link ChangeMode#CONST } )
   */
  @SuppressWarnings("unchecked")
  static public <E extends IAttrEnum> Attribute<E> getAttribute(final E e) {
    assert e != null;
    final Attribute<?> attribute = pool.get(e.getLexem());
    assert (attribute != null) : "No attribute known for " + e;
    assert (attribute.value == e) : "Attribute does not have the expected value: "
        + "enum = " + e + "; attr = " + attribute;
    return (Attribute<E>) attribute;
  }

  public final String getLexem() {
    return this.lexem;
  }

  static public boolean isAttribute(final String lexem) {
    return pool.containsKey(lexem);
  }

  public abstract Terminal getTerminal();

  public V getValue() {
    return this.value;
  }
}
