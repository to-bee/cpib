package ch.fhnw.cpib.iml.lib.enums;

public enum AddOpr implements IAttrEnum {
  PLUS("+"), MINUS("-");
  public final String lexem;

  private AddOpr(final String lexem) {
    this.lexem = lexem;
  }

  @Override
  public String getLexem() {
    return this.lexem;
  }
}
