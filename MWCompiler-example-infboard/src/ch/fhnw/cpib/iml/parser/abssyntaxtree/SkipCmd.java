package ch.fhnw.cpib.iml.parser.abssyntaxtree;

import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.ICmd;
import ch.fhnw.cpib.iml.parser.exception.ContextException;
import ch.fhnw.cpib.iml.scanner.Token;
import ch.fhnw.cpib.iml.vm.IVirtualMachine.CodeTooSmallError;
import ch.fhnw.cpib.iml.vm.IVirtualMachine.HeapTooSmallError;

public class SkipCmd implements ICmd {

  private final Token<?> token;

  public SkipCmd(final Token<?> token) {
    this.token = token;
  }

  @Override
  public void check() throws ContextException {
    // Ahoi Skipper!
  }

  @Override
  public Token<?> getToken() {
    return this.token;
  }

  @Override
  public int code(final int location) throws CodeTooSmallError, HeapTooSmallError {
    // Should be handled on parent container
    return location;
  }
}
