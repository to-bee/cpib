package ch.fhnw.cpib.iml.scanner.attr;

import ch.fhnw.cpib.iml.scanner.Terminal;

/**
 * A AttrLiteral is the Attribute of a Terminal known as LITERAL.
 * Or the other way around:
 * LITERAL is a type of Terminal. LITERAL has an Attribute that is a
 * AttrLiteral.
 * <p>
 * Literals match /([0-9]+|TRUE|FALSE)/ and are numerical constants.
 */
public abstract class AttrLiteral extends Attribute<int[]> {

  static {
    try {
      Class.forName(AttrBoolVal.class.getName());
      Class.forName(AttrIntVal.class.getName());
      Class.forName(AttrStrVal.class.getName());
    } catch (final Exception e) {
    }
  }

  public AttrLiteral(final String lexem, final int[] value) {
    super(lexem, value);
  }

  @Override
  public final Terminal getTerminal() {
    return Terminal.LITERAL;
  }
}