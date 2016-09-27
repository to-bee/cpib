package ch.fhnw.cpib.iml.lib.enums;

public enum BoolVal implements IAttrEnum {
  TRUE(true), FALSE(false);

  public final boolean bool;

  private BoolVal(final boolean bool) {
    this.bool = bool;
  }

  @Override
  public String getLexem() {
    return this.name();
  }
}
