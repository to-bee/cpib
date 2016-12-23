package ch.fhnw.cpib.compiler.cst.classes;

import java.util.List;

import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IProgramParameter;
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
	
	@Override
	public List<IProgramParameter> toAbs() {
		return optProPar.toAbs();
	}
	
	
	
}
