package ch.fhnw.cpib.iml.scanner.state;

import ch.fhnw.cpib.iml.lib.Chr;
import ch.fhnw.cpib.iml.scanner.Scanner;
import ch.fhnw.cpib.iml.scanner.attr.AttrStrVal;
import ch.fhnw.cpib.iml.scanner.exception.IllegalChrException;
import ch.fhnw.cpib.iml.scanner.exception.SyntaxException;

/**
 * Reads something that matches <code>/"([^"]|\\")*"/</code>.
 * This is always a string.
 */
public final class State3String extends ScannerState {
  State3String() {
    super();
  }

  @Override
  public ScannerState next(final Chr chr) throws IllegalChrException {
    if (chr.isEOF()) throw new SyntaxException("String not closed.");
    final int c = chr.getValue();
    if (c == 10 || c == 13 || c == 0)
      throw new SyntaxException(
          "Line break not allowed inside of String, use \\r, \\n or \\R instead: ");

    if (c == AttrStrVal.ESCAPE_CHARACTER) return STATE_STR_ESC;
    if (c != AttrStrVal.DELIMITER) return this;
    Scanner.SCANNER.accept();
    return ScannerState.STATE_START;
  }
}