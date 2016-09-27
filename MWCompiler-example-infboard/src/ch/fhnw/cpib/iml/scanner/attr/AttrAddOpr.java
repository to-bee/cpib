package ch.fhnw.cpib.iml.scanner.attr;

import ch.fhnw.cpib.iml.lib.enums.AddOpr;
import ch.fhnw.cpib.iml.scanner.Terminal;

@SuppressWarnings("unused")
public final class AttrAddOpr extends Attribute<AddOpr> {

  static {
    new AttrAddOpr(AddOpr.PLUS);
    new AttrAddOpr(AddOpr.MINUS);
  }

  private AttrAddOpr(final AddOpr opr) {
    super(opr.lexem, opr);
  }

  @Override
  public String toString() {
    return this.value.name();
  }

  @Override
  public Terminal getTerminal() {
    return Terminal.ADDOPR;
  }

}
