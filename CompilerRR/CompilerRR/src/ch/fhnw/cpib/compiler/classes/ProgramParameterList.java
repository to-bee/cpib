package ch.fhnw.cpib.compiler.classes;

import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn.IOptionalProgramParameters;
import ch.fhnw.cpib.compiler.scanner.Token;

public class ProgramParameterList implements IConcSyn.IProgamParameterList{

	Token lpar;
	IConcSyn.IOptionalProgramParameters optProPar;
	Token rpar;
	public ProgramParameterList(Token lpar, IOptionalProgramParameters optProPar, Token rpar) {
		super();
		this.lpar = lpar;
		this.optProPar = optProPar;
		this.rpar = rpar;
	}
	
	
	
}
