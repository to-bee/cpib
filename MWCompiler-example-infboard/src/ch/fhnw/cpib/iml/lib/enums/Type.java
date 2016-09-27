package ch.fhnw.cpib.iml.lib.enums;

import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn;
import ch.fhnw.cpib.iml.scanner.IToken;
import ch.fhnw.cpib.iml.scanner.Terminal;
import ch.fhnw.cpib.iml.scanner.attr.AttrBoolVal;
import ch.fhnw.cpib.iml.scanner.attr.AttrIntVal;
import ch.fhnw.cpib.iml.scanner.attr.AttrLiteral;
import ch.fhnw.cpib.iml.scanner.attr.AttrStrVal;
import ch.fhnw.cpib.iml.scanner.attr.AttrType;
import ch.fhnw.cpib.iml.scanner.attr.Attribute;

/**
 * All available Types.
 *
 * @see IAbsSyn.ITyped
 */
public enum Type implements IAttrEnum {
  INT32, BOOL, STRING;

  @Override
  public String getLexem() {
    return this.name();
  }

  /**
   * Type of a Literal Token ({@link Terminal}={@link Terminal#LITERAL
   * LITERAL}).
   */
  public static Type ofLiteral(final IToken<int[]> t) {
    if (t == null) return null;
    final Attribute<int[]> attr = t.getAttribute();
    if (attr instanceof AttrLiteral)
      return ofAttrLiteral((AttrLiteral) t.getAttribute());
    return null;
  }

  private static Type ofAttrLiteral(final AttrLiteral a) {
    @SuppressWarnings("rawtypes")
    final Class<? extends Attribute> cls = a.getClass();
    if (cls == AttrIntVal.class) return INT32;
    if (cls == AttrBoolVal.class) return BOOL;
    if (cls == AttrStrVal.class) return STRING;
    return null;
  }

  /**
   * The Type of the Attribute (Literal or Type Declaration).
   */
  public static Type ofAttribute(final Attribute<?> a) {
    if (a == null) return null;
    if (a instanceof AttrType) return ((AttrType) a).getValue();
    if (a instanceof AttrLiteral) return ofAttrLiteral((AttrLiteral) a);
    return null;
  }
}
