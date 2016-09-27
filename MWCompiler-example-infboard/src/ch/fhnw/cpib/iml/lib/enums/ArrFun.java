package ch.fhnw.cpib.iml.lib.enums;

/**
 * Functions of Arrays.
 * <p>
 * Usage: <code>
 * VAR foo : String;
 * foo init := "foo";
 * foo.MAXLEN;
 * foo.STRLEN;
 * </code>
 *
 */
public enum ArrFun implements IAttrEnum {
  MAXLEN, STRLEN;

  @Override
  public String getLexem() {
    return this.name();
  }
}
