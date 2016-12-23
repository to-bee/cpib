package ch.fhnw.cpib.compiler.classes;

import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.scanner.Token;

public class CmdSkip implements IConcSyn.ICmd{

	Token skipToken;
	
	public CmdSkip(Token t) {
		skipToken = t;
	}
	

}
