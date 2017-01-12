package ch.fhnw.cpib.compiler.scanner.tokens;

import ch.fhnw.cpib.compiler.scanner.IToken;

public enum PseudoToken implements IToken {
	  /**
	   * Variable used to store the value of the program counter.
	   * The program counter is the index of the code (array of IInstruction).
	   */
	  PROGRAM_COUNTER_OLD,
	  /** Not actually used in IML. */
	  EXTREME_POINTER,
	  /**
	   * provides a reference for each routine incarnation.
	   */
	  FRAME_POINTER_OLD;
}