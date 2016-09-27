package ch.fhnw.cpib.iml.parser.concsyntaxtree;

import ch.fhnw.cpib.iml.lib.enums.ChangeMode;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IOptChangeMode;
import ch.fhnw.cpib.iml.scanner.Token;
import ch.fhnw.cpib.iml.scanner.attr.Attribute;

public class OptChangeMode implements IOptChangeMode {

  private final Token<ChangeMode> changeMode;

  public OptChangeMode(final Token<ChangeMode> changeMode) {
    this.changeMode = changeMode;
  }

  @Override
  public void appendAsString(final StringBuilder sb, final String indent) {
    sb.append(indent);
    sb.append(this.changeMode.getTerminal());
    sb.append("\n");
  }

  @Override
  public Attribute<ChangeMode> toAbsSyn() {
    return this.changeMode.getAttribute();
  }

}
