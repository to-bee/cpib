package ch.fhnw.cpib.iml.scanner.state;

import ch.fhnw.cpib.iml.lib.Chr;
import ch.fhnw.cpib.iml.scanner.Scanner;
import ch.fhnw.cpib.iml.scanner.DistinctToken;
import ch.fhnw.cpib.iml.scanner.exception.IllegalChrException;

/**
 * State where a certain character may or may not be followed by a certain other
 * character. Example: ':' may be followed by '='.
 * This tests whether the next will that one or anything else.
 */
public class StateOneOrTwo extends ScannerState {

  private final char second;
  private final DistinctToken<?> one, two;

  StateOneOrTwo(final char first, final char second) {
    super();
    this.second = second;
    this.two = DistinctToken.getToken("" + first + second);
    this.one = DistinctToken.getToken("" + first);
  }

  @Override
  public ScannerState next(final Chr chr) throws IllegalChrException {
    if (chr.getValue() == this.second) {
      Scanner.SCANNER.accept(this.two);
    } else { // other
      Scanner.SCANNER.movePrevChr();
      Scanner.SCANNER.accept(this.one);
    }
    return ScannerState.STATE_START;
  }
}