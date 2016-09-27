package ch.fhnw.cpib.iml.scanner.attr;

public final class AttrIntVal extends AttrLiteral {

  public AttrIntVal(final String lexem) {
    super(lexem, getInts(lexem));
    assert lexem.toUpperCase().matches("^[0-9]+$");
  }

  @Override
  public String toString() {
    String i = this.lexem;
    // remove leading zeroes:
    while (i.length() > 1 && i.charAt(0) == '0')
      i = i.substring(1);
    return "IntVal " + i;
  }

  static private int[] getInts(final String lexem) {
    return new int[] { Integer.parseInt(lexem) };
  }
}
