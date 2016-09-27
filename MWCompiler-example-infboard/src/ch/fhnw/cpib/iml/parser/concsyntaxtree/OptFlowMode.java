package ch.fhnw.cpib.iml.parser.concsyntaxtree;

import ch.fhnw.cpib.iml.lib.enums.FlowMode;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IOptFlowMode;
import ch.fhnw.cpib.iml.scanner.Token;
import ch.fhnw.cpib.iml.scanner.attr.Attribute;

public class OptFlowMode implements IOptFlowMode {

  private final Token<FlowMode> flowMode;

  public OptFlowMode(final Token<FlowMode> flowMode) {
    this.flowMode = flowMode;
  }

  @Override
  public void appendAsString(final StringBuilder sb, final String indent) {
    sb.append(indent);
    sb.append(this.flowMode.getTerminal());
    sb.append("\n");
  }

  @Override
  public Attribute<FlowMode> toAbsSyn() {
    return this.flowMode.getAttribute();
  }

}
