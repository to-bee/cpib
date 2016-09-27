package ch.fhnw.cpib.iml.scanner.attr;

import ch.fhnw.cpib.iml.lib.enums.FlowMode;
import ch.fhnw.cpib.iml.scanner.Terminal;

@SuppressWarnings("unused")
public final class AttrFlowMode extends Attribute<FlowMode> {

  static {
    new AttrFlowMode(FlowMode.IN);
    new AttrFlowMode(FlowMode.INOUT);
    new AttrFlowMode(FlowMode.OUT);
  }

  private AttrFlowMode(final FlowMode mode) {
    super(mode.name(), mode);
  }

  @Override
  public String toString() {
    return this.lexem;
  }

  @Override
  public Terminal getTerminal() {
    return Terminal.FLOWMODE;
  }
}
