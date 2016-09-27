package ch.fhnw.cpib.iml.parser.concsyntaxtree;

import ch.fhnw.cpib.iml.lib.enums.FlowMode;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IOptFlowMode;
import ch.fhnw.cpib.iml.scanner.attr.Attribute;

public class OptFlowModeEpsilon implements IOptFlowMode {

  @Override
  public void appendAsString(final StringBuilder sb, final String indent) {

  }

  @SuppressWarnings("unchecked")
  @Override
  public Attribute<FlowMode> toAbsSyn() {
    return (Attribute<FlowMode>) Attribute.getAttribute(FlowMode.INOUT.name());
  }

}
