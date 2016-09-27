package ch.fhnw.cpib.iml.scanner;

import ch.fhnw.cpib.iml.scanner.state.StateCarriageReturn;

/**
 * Any Item (e.g. a {@link DistinctToken}) that has information about on which line
 * number and on what column it occurred in the original source code.
 * 
 * @see StateCarriageReturn
 */
public interface IPosition {
  /** Line number. Begins at line number 1. */
  public int getLineNumber();

  /** Character column on line. Begins at column number 1. */
  public int getColumn();
}
