package ch.fhnw.cpib.iml.scanner.state;


import ch.fhnw.cpib.iml.lib.Chr;
import ch.fhnw.cpib.iml.scanner.Scanner;
import ch.fhnw.cpib.iml.scanner.exception.IllegalChrException;

/**
 * Tokens <code>.MAXLEN</code> and <code>.STRLEN</code> are handled by this
 * state. It's like identifiers but it starts with a '.'.
 */
public class StateStrMaxLen extends ScannerState {

  @Override
  public ScannerState next(final Chr chr) throws IllegalChrException {
    if (chr.getCategory() == Chr.Category.ALPHA) return this;
    Scanner.SCANNER.movePrevChr();
    Scanner.SCANNER.accept();
    return ScannerState.STATE_START;
  }

}
