package ch.fhnw.cpib.iml.scanner.exception;

import ch.fhnw.cpib.iml.scanner.IPosition;
import ch.fhnw.cpib.iml.scanner.Scanner;

public abstract class ScannerException extends RuntimeException implements
    IPosition {
  private static final long serialVersionUID = 8367795894609838564L;
  public final int          pos;
  public final int          lineNumber;
  public final int          column;

  public ScannerException(final String message) {
    super(message + " @" + Scanner.SCANNER.getLineNumber() + ":"
        + Scanner.SCANNER.getColumn());
    this.pos = Scanner.SCANNER.getSourceCodePosition();
    this.lineNumber = Scanner.SCANNER.getLineNumber();
    this.column = Scanner.SCANNER.getColumn();
  }

  @Override
  public final int getLineNumber() {
    return this.lineNumber;
  }

  @Override
  public final int getColumn() {
    return this.column;
  }
}
