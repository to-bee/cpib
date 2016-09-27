package ch.fhnw.cpib.iml.scanner.attr;

import ch.fhnw.cpib.iml.lib.enums.Type;
import ch.fhnw.cpib.iml.scanner.Terminal;

@SuppressWarnings("unused")
public final class AttrType extends Attribute<Type> {
  static {
    new AttrType(Type.INT32);
    new AttrType(Type.BOOL);
    new AttrType(Type.STRING);
  }

  private AttrType(final Type type) {
    super(type.name(), type);
  }

  @Override
  public String toString() {
    return this.lexem;
  }

  @Override
  public Terminal getTerminal() {
    return Terminal.TYPE;
  }
}
