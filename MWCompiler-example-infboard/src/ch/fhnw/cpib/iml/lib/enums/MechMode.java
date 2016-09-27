package ch.fhnw.cpib.iml.lib.enums;

import ch.fhnw.cpib.iml.scanner.attr.AttrMechMode;
import ch.fhnw.cpib.iml.scanner.attr.Attribute;

/**
 * COPY, REF.
 */
public enum MechMode implements IAttrEnum {
  COPY, REF;

  @Override
  public String getLexem() {
    return this.name();
  }

  /**
   * MechMode out of Attribute
   */
  public static MechMode ofAttribute(final Attribute<?> a) {
    if (a == null) return null;
    if (a instanceof AttrMechMode) return ((AttrMechMode) a).getValue();
    return null;
  }
}
