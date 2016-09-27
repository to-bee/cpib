package ch.fhnw.cpib.iml.parser;

import ch.fhnw.cpib.iml.scanner.DistinctToken;
import ch.fhnw.cpib.iml.scanner.IToken;
import ch.fhnw.cpib.iml.scanner.Terminal;
import ch.fhnw.cpib.iml.scanner.attr.AttrIdent;
import ch.fhnw.cpib.iml.scanner.attr.Attribute;

/**
 * Token created during checking for special variables.
 * This behaves like a {@link DistinctToken}&lt;String&gt; with
 * {@link AttrIdent} but is not actually fount in the source code.
 * */
public enum PseudoToken implements IToken<String> {
  /**
   * Variable used to store the value of the program counter.
   * The program counter is the index of the code (array of IInstruction).
   */
  PROGRAM_COUNTER_OLD,
  /** Not actually used in IML. */
  EXTREME_POINTER,
  /**
   * provides a reference for each routine incarnation.
   */
  FRAME_POINTER_OLD;

  final Attribute<String> attr = new Attr(this.name());

  @Override
  public Terminal getTerminal() {
    return Terminal.IDENT;
  }

  @Override
  public Attribute<String> getAttribute() {
    return this.attr;
  }

  private class Attr extends Attribute<String> {

    public Attr(final String lexem) {
      super(lexem, lexem);
    }

    @Override
    public String toString() {
      return "\"" + PseudoToken.this.name() + "\"";
    }

    @Override
    public Terminal getTerminal() {
      return Terminal.IDENT;
    }
  }

}
