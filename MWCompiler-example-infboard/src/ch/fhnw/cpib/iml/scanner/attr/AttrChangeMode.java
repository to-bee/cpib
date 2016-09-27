package ch.fhnw.cpib.iml.scanner.attr;

import ch.fhnw.cpib.iml.lib.enums.ChangeMode;
import ch.fhnw.cpib.iml.scanner.Terminal;

@SuppressWarnings("unused")
public final class AttrChangeMode extends Attribute<ChangeMode> {
  static {
    new AttrChangeMode(ChangeMode.CONST);
    new AttrChangeMode(ChangeMode.VAR);
  }

  private AttrChangeMode(final ChangeMode mode) {
    super(mode.name(), mode);
  }

  @Override
  public String toString() {
    return this.lexem;
  }

  @Override
  public Terminal getTerminal() {
    return Terminal.CHANGEMODE;
  }
}
