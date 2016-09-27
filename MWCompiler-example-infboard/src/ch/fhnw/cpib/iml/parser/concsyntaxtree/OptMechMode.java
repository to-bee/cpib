package ch.fhnw.cpib.iml.parser.concsyntaxtree;

import ch.fhnw.cpib.iml.lib.enums.MechMode;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IOptMechMode;
import ch.fhnw.cpib.iml.scanner.Token;
import ch.fhnw.cpib.iml.scanner.attr.Attribute;

public class OptMechMode implements IOptMechMode {

  private final Token<MechMode> mechMode;

  public OptMechMode(final Token<MechMode> mechMode) {
    this.mechMode = mechMode;
  }

  @Override
  public void appendAsString(final StringBuilder sb, final String indent) {
    sb.append(indent);
    sb.append(this.mechMode.getTerminal());
    sb.append("\n");
  }

  @Override
  public Attribute<MechMode> toAbsSyn() {
    return this.mechMode.getAttribute();
  }

}
