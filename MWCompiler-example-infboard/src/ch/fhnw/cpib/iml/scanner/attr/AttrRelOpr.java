package ch.fhnw.cpib.iml.scanner.attr;

import ch.fhnw.cpib.iml.lib.enums.RelOpr;
import ch.fhnw.cpib.iml.scanner.Terminal;

public final class AttrRelOpr extends Attribute<RelOpr> {

  static {
    // Initialize on Class Load:
    for (final RelOpr opr : RelOpr.values()) {
      @SuppressWarnings("unused")
      final Object o = new AttrRelOpr(opr);
    }
  }


  AttrRelOpr(final RelOpr opr) {
    super(opr.lexem, opr);
  }

  @Override
  public String toString() {
    return this.value.name();
  }

  @Override
  public Terminal getTerminal() {
    return Terminal.RELOPR;
  }

}
