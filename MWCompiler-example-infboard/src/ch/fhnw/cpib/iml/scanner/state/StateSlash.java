package ch.fhnw.cpib.iml.scanner.state;

import ch.fhnw.cpib.iml.lib.Chr;
import ch.fhnw.cpib.iml.scanner.Scanner;
import ch.fhnw.cpib.iml.scanner.DistinctToken;
import ch.fhnw.cpib.iml.scanner.exception.IllegalChrException;
import ch.fhnw.cpib.iml.scanner.exception.SyntaxException;

/**
 * State where a certain character may or may not be followed by a certain other
 * character. Example: ':' may be followed by '='.
 * This tests whether the next will that one or anything else.
 */
public class StateSlash extends ScannerState {

  private final DistinctToken<? /* RelOpr */> neq;

  StateSlash() {
    super();
    this.neq = DistinctToken.getToken("/=");
  }

  @Override
  public ScannerState next(final Chr chr) throws IllegalChrException {
    if (chr.getValue() == '=') {
      Scanner.SCANNER.accept(this.neq);
    } else { // other
      Scanner.SCANNER.movePrevChr();
      throw new SyntaxException("'/' must be followed by '='.");
    }
    return ScannerState.STATE_START;
  }
}