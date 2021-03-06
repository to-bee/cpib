package ch.fhnw.cpib.compiler.cst.classes;

import java.util.List;

import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn.IOptionalParameters;
import ch.fhnw.cpib.compiler.scanner.Token;

public class ParameterList implements IConcSyn.IParameterList{

	Token lpar;
	IConcSyn.IOptionalParameters optPar;
	Token rpar;
	public ParameterList(Token lpar, IOptionalParameters optPar, Token rpar) {
		super();
		this.lpar = lpar;
		this.optPar = optPar;
		this.rpar = rpar;
	}
	
	@Override
	public List<ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IParameter> toAbs() {
		return optPar.toAbs();
	}
	
	
}
