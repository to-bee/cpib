package ch.fhnw.cpib.compiler.ast.classes;

import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IFactor;
import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IMULTOPRfactor;
import ch.fhnw.cpib.compiler.scanner.Token;

public class MultOprFactor implements IMULTOPRfactor{
	Token multOpr;
	IFactor factor;
	public MultOprFactor(Token multOpr, IFactor factor) {
		super();
		this.multOpr = multOpr;
		this.factor = factor;
	}
	
	
}
