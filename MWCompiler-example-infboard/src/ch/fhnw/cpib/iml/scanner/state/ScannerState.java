package ch.fhnw.cpib.iml.scanner.state;

import ch.fhnw.cpib.iml.lib.Chr;
import ch.fhnw.cpib.iml.scanner.exception.IllegalChrException;

/**
 * One state of the state machine.
 *
 * <p>
 * Remember: A state does <b>not</b> have a state! The machine has a state
 * (that's why it's called a state machine). Thus, a state instance should have
 * only final fields.
 * </p>
 */
public abstract class ScannerState {

  final static ScannerState STATE_START     = new State0Start();
  final static ScannerState STATE_IDENT     = new State1Ident();
  final static ScannerState STATE_LITERAL   = new State2Literal();
  final static ScannerState STATE_RCHEVRON  = new StateOneOrTwo('>', '=');
  final static ScannerState STATE_LCHEVRON  = new StateOneOrTwo('<', '=');
  final static ScannerState STATE_COLON     = new StateOneOrTwo(':', '=');
  final static ScannerState STATE_SLASH     = new StateSlash();
  final static ScannerState STATE_STRING    = new State3String();
  final static ScannerState STATE_STR_ESC   = new State3StringEscSeq();
  final static ScannerState STATE_CR        = new StateCarriageReturn();
  final static ScannerState STATE_STRMAXLEN = new StateStrMaxLen();

  public static ScannerState getStartState() {
    return STATE_START;
  }

  public abstract ScannerState next(Chr chr) throws IllegalChrException;
}
