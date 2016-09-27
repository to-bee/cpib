package ch.fhnw.cpib.iml.scanner.attr;

import java.util.List;

import ch.fhnw.cpib.iml.lib.Chr;
import ch.fhnw.cpib.iml.scanner.Terminal;

/**
 * An AttrIdent is the Attribute of a Terminal known as IDENT.
 * Or the other way around:
 * IDENT is a type of Terminal. IDENT has an Attribute that is an AttrIdent.
 *
 * <p>
 * The Ident matches <code>/[a-Z][a-Z0-9]&#42;/</code>
 * <p>
 * The constructor takes just the value of the Ident, but #toString() returns
 * the string in quotation marks. The original String can be received by
 * {@link #getLexem()}.
 */
public final class AttrIdent extends Attribute<String> {
  private final String string;

  public AttrIdent(final String ident) {
    super(ident, ident);
    assert ident != null;
    this.string = "\"" + ident + "\"";
  }

  public AttrIdent(final List<Chr> string) {
    this(Chr.toString(string));
  }

  /** With quotation marks. */
  @Override
  public String toString() {
    return this.string;
  }

  @Override
  public Terminal getTerminal() {
    return Terminal.IDENT;
  }

}