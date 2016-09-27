package ch.fhnw.cpib.iml.lib.enums;

/** EQ("="), NE("/="), GT("&gt;"), LT("&lt;"), GE("&gt;="), LE("&lt;="). */
public enum RelOpr implements IAttrEnum {
  EQ("="), NE("/="), GT(">"), LT("<"), GE(">="), LE("<=");

  public final String lexem;

  private RelOpr(final String lexem) {
    this.lexem = lexem;
  }

  @Override
  public String getLexem() {
    return this.name();
  }
}
