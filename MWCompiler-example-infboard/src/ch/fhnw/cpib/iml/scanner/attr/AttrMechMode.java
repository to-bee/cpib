package ch.fhnw.cpib.iml.scanner.attr;

import ch.fhnw.cpib.iml.lib.enums.MechMode;
import ch.fhnw.cpib.iml.scanner.Terminal;

@SuppressWarnings("unused")
public final class AttrMechMode extends Attribute<MechMode> {

  static {
    new AttrMechMode(MechMode.COPY);
    new AttrMechMode(MechMode.REF);
  }

  private AttrMechMode(final MechMode mode) {
    super(mode.name(), mode);
  }

  @Override
  public String toString() {
    return this.lexem;
  }

  @Override
  public Terminal getTerminal() {
    return Terminal.MECHMODE;
  }

}
