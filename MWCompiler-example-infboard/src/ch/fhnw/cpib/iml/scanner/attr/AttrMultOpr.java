package ch.fhnw.cpib.iml.scanner.attr;

import ch.fhnw.cpib.iml.lib.enums.MultOpr;
import ch.fhnw.cpib.iml.scanner.Terminal;

@SuppressWarnings("unused")
public final class AttrMultOpr extends Attribute<MultOpr> {
  static {
    new AttrMultOpr(MultOpr.TIMES);
    new AttrMultOpr(MultOpr.DIV);
    new AttrMultOpr(MultOpr.MOD);
  }

  private AttrMultOpr(final MultOpr opr) {
    super(opr.lexem, opr);
  }

  @Override
  public String toString() {
    return this.value.name();
  }

  @Override
  public Terminal getTerminal() {
    return Terminal.MULTOPR;
  }
}
