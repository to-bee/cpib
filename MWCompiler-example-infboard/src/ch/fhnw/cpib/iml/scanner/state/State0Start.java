package ch.fhnw.cpib.iml.scanner.state;

import ch.fhnw.cpib.iml.lib.Chr;
import ch.fhnw.cpib.iml.lib.Chr.Category;
import ch.fhnw.cpib.iml.scanner.Scanner;
import ch.fhnw.cpib.iml.scanner.DistinctToken;
import ch.fhnw.cpib.iml.scanner.attr.AttrStrVal;
import ch.fhnw.cpib.iml.scanner.exception.IllegalChrException;

/**
 * Start state.
 * This handles whitespace and goes to another state if anything non-whitespace
 * is read.
 *
 */
public class State0Start extends ScannerState {
  State0Start() {
    super();
  }

  @Override
  public ScannerState next(final Chr chr) throws IllegalChrException {
    final Category category = chr.getCategory();

    final int value = chr.getValue();
    switch (category) {
    case WHITESPACE:
      try {
        if (chr.getValue() == '\n') {
          Scanner.SCANNER.setLineBreak();
        } else if (chr.getValue() == '\r') {
          Scanner.SCANNER.setLineBreak();
          return ScannerState.STATE_CR;
        }
      } finally {
        Scanner.SCANNER.forget();
      }
      return this; // == STATE_START

    case ALPHA:
      Scanner.SCANNER.movePrevChr();
      return ScannerState.STATE_IDENT;

    case NUMERIC:
      Scanner.SCANNER.movePrevChr();
      return ScannerState.STATE_LITERAL;

    case BRACKET:
      Scanner.SCANNER.accept();
      return this;

    case STRING_CONTROL:
      if (value == AttrStrVal.DELIMITER)
        return ScannerState.STATE_STRING;
      throw new IllegalChrException(chr);

    case SYMBOL:
      switch (value) {
      case '.':
        return ScannerState.STATE_STRMAXLEN;
      case '>':
        return ScannerState.STATE_RCHEVRON;
      case '<':
        return ScannerState.STATE_LCHEVRON;
      case ':':
        return ScannerState.STATE_COLON;
      case '/':
        return ScannerState.STATE_SLASH;
      default:
        Scanner.SCANNER.accept();
      }
      return this;

    case EOF:
      Scanner.SCANNER.accept(DistinctToken.getEOF());
      return null;

    case ILLEGAL:
    default:
      throw new IllegalChrException(chr);
    }

  }
}