package ch.fhnw.cpib.iml.lib.enums;

public enum BoolOpr implements IAttrEnum {
  CAND, COR;

  @Override
  public String getLexem() {
    return this.name();
  }
}
