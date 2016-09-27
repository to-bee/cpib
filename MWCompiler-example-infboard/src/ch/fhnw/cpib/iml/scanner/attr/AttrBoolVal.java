package ch.fhnw.cpib.iml.scanner.attr;

import ch.fhnw.cpib.iml.lib.enums.BoolVal;

@SuppressWarnings("unused")
public final class AttrBoolVal extends AttrLiteral {
  static {
    new AttrBoolVal(BoolVal.TRUE);
    new AttrBoolVal(BoolVal.FALSE);
  }

  private AttrBoolVal(final BoolVal val) {
    super(val.name(), getInts(val));
  }

  @Override
  public String toString() {
    return "BoolVal " + this.lexem;
  }

  static private int[] getInts(final BoolVal val) {
    return new int[] { (val.bool ? 1 : 0) };
  }

}
