package ch.fhnw.cpib.iml.lib.enums;

import ch.fhnw.cpib.iml.scanner.attr.AttrChangeMode;
import ch.fhnw.cpib.iml.scanner.attr.Attribute;

public enum ChangeMode implements IAttrEnum {
  VAR, CONST;

  @Override
  public String getLexem() {
    return this.name();
  }

  /**
   * ChangeMode out of Attribute
   */
  public static ChangeMode ofAttribute(final Attribute<?> a) {
    if (a == null) return null;
    if (a instanceof AttrChangeMode) return ((AttrChangeMode) a).getValue();
    return null;
  }
}
