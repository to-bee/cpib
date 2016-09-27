package ch.fhnw.cpib.iml.scanner.exception;

import java.io.IOException;

public class ScannerIOException extends ScannerException {
  public ScannerIOException(final String message, final IOException cause) {
    super(message);
    this.initCause(cause);
  }

  private static final long serialVersionUID = -1262056807952099037L;

}
