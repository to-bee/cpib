package ch.fhnw.cpib.iml.scanner.state;

import ch.fhnw.cpib.iml.lib.Chr;
import ch.fhnw.cpib.iml.scanner.Scanner;
import ch.fhnw.cpib.iml.scanner.exception.IllegalChrException;

/**
 * Reads something that matches <code>[0-9]+</code>.
 * This is always a numerical constant.
 */
public class State2Literal extends ScannerState {
  State2Literal() {
    super();
  }

  @Override
  public ScannerState next(final Chr chr) throws IllegalChrException {
    switch (chr.getCategory()) {
    case NUMERIC:
      return this;
    default:// other
      Scanner.SCANNER.movePrevChr();
      Scanner.SCANNER.accept();
      return ScannerState.STATE_START;
    }
  }
}