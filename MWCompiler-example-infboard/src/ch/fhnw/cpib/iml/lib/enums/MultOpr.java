package ch.fhnw.cpib.iml.lib.enums;

/** TIMES, DIV, MOD. */
public enum MultOpr implements IAttrEnum {
  TIMES("*"), DIV, MOD;

  public final String lexem;

  MultOpr(final String lexem) {
    this.lexem = lexem;
  }

  MultOpr() {
    this.lexem = this.name();
  }

  @Override
  public String getLexem() {
    return this.lexem;
  }

}
