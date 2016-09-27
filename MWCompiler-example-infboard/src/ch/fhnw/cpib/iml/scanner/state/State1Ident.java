package ch.fhnw.cpib.iml.scanner.state;

import ch.fhnw.cpib.iml.lib.Chr;
import ch.fhnw.cpib.iml.scanner.Scanner;
import ch.fhnw.cpib.iml.scanner.exception.IllegalChrException;

/**
 * Reads something that matches <code>/[a-Z][a-Z0-9]&#42;/</code>.
 * 
 */
public class State1Ident extends ScannerState {
  State1Ident() {
    super();
  }

  @Override
  public ScannerState next(final Chr chr) throws IllegalChrException {
    switch (chr.getCategory()) {
    case ALPHA:
    case NUMERIC:

      return this;

    default:// other
      Scanner.SCANNER.movePrevChr();
      Scanner.SCANNER.accept();
      return ScannerState.STATE_START;
    }
  }
}