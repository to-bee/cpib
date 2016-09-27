package ch.fhnw.cpib.iml.scanner.state;

import ch.fhnw.cpib.iml.lib.Chr;
import ch.fhnw.cpib.iml.scanner.exception.IllegalChrException;
import ch.fhnw.cpib.iml.scanner.exception.SyntaxException;

/**
 * Reads a character that is escaped.
 */
public final class State3StringEscSeq extends ScannerState {
  State3StringEscSeq() {
    super();
  }

  @Override
  public ScannerState next(final Chr chr) throws IllegalChrException {
    final int value = chr.getValue();
    if (value == 10 || value == 13 || value == 0)
      throw new SyntaxException(
          "Line break not allowed inside of String, use \\r, \\n or \\R instead: ");
    return STATE_STRING;
  }
}