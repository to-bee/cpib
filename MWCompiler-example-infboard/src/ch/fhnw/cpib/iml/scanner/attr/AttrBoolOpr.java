package ch.fhnw.cpib.iml.scanner.attr;

import ch.fhnw.cpib.iml.lib.enums.BoolOpr;
import ch.fhnw.cpib.iml.scanner.Terminal;

@SuppressWarnings("unused")
public final class AttrBoolOpr extends Attribute<BoolOpr> {

  static {
    new AttrBoolOpr(BoolOpr.CAND);
    new AttrBoolOpr(BoolOpr.COR);
  }

  private AttrBoolOpr(final BoolOpr opr) {
    super(opr.name(), opr);
  }

  @Override
  public String toString() {
    return this.lexem;
  }

  @Override
  public Terminal getTerminal() {
    return Terminal.BOOLOPR;
  }
}
