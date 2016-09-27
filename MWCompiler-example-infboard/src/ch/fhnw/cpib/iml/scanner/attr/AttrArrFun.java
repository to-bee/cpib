package ch.fhnw.cpib.iml.scanner.attr;

import ch.fhnw.cpib.iml.lib.enums.ArrFun;
import ch.fhnw.cpib.iml.scanner.Terminal;

@SuppressWarnings("unused")
public final class AttrArrFun extends Attribute<ArrFun> {
  static {
    new AttrArrFun(ArrFun.MAXLEN);
    new AttrArrFun(ArrFun.STRLEN);
  }

  private AttrArrFun(final ArrFun fun) {
    super("." + fun.name(), fun);
  }

  @Override
  public String toString() {
    return this.value.name();
  }

  @Override
  public Terminal getTerminal() {
    return Terminal.ARRFUN;
  }
}
