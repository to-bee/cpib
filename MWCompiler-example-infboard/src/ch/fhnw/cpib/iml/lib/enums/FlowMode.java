package ch.fhnw.cpib.iml.lib.enums;

import ch.fhnw.cpib.iml.scanner.attr.AttrFlowMode;
import ch.fhnw.cpib.iml.scanner.attr.Attribute;

/**
 * Either IN, OUT, INOUT or NONE for normal variables without any restrictions
 *
 */
public enum FlowMode implements IAttrEnum {
  IN, OUT, INOUT, NONE;

  @Override
  public String getLexem() {
    return this.name();
  }

  /**
   * FlowMode out of Attribute
   */
  public static FlowMode ofAttribute(final Attribute<?> a) {
    if (a == null) return FlowMode.NONE;
    if (a instanceof AttrFlowMode) return ((AttrFlowMode) a).getValue();
    return FlowMode.NONE;
  }
}
