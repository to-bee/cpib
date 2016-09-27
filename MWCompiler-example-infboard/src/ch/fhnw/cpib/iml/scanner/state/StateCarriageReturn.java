package ch.fhnw.cpib.iml.scanner.state;

import ch.fhnw.cpib.iml.lib.Chr;
import ch.fhnw.cpib.iml.scanner.Scanner;
import ch.fhnw.cpib.iml.scanner.exception.IllegalChrException;

/**
 * This handles line breaks.
 * <dl>
 * <dt>\r = CR</dt>
 * <dd>1 line break (Mac OS 9)</dd>
 * <dt>\r\r = CR CR</dt>
 * <dd>2 line breaks (Mac OS 9)</dd>
 * <dt>\r\n = CR LF</dt>
 * <dd>1 line break (Windows)</dd>
 * <dt>\n\r = LF CR</dt>
 * <dd>2 line breaks (???)</dd>
 * <dt>\n = LF</dt>
 * <dd>1 line break (Unix)</dd>
 * <dt>\n\n = LF LF</dt>
 * <dd>2 line breaks (Unix)</dd>
 * </dl>
 *
 */
public class StateCarriageReturn extends ScannerState {
  StateCarriageReturn() {
    super();
  }

  @Override
  public ScannerState next(final Chr chr) throws IllegalChrException {
    // the last read CR was already reported to the scanner.
    // However, we can still read more CRs now.
    if (chr.getValue() == '\r') {
      Scanner.SCANNER.setLineBreak();
      Scanner.SCANNER.forget();
      return this;
    } else if (chr.getValue() == '\n') {
      // This LF is part of the already reported line break.
      Scanner.SCANNER.decrementColumn();
      Scanner.SCANNER.forget();
      return ScannerState.STATE_START;
    }
    // other:
    Scanner.SCANNER.movePrevChr();
    return ScannerState.STATE_START;
  }
}