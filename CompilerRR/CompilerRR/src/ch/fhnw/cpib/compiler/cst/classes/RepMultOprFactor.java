package ch.fhnw.cpib.compiler.cst.classes;

import java.util.LinkedList;
import java.util.List;

import ch.fhnw.cpib.compiler.ast.classes.MultOprFactor;
import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn;
import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IMULTOPRfactor;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.scanner.Token;

public class RepMultOprFactor implements IConcSyn.IRepMULTOPRfactor{

	Token multOpr;
	IConcSyn.IFactor factor;
	IConcSyn.IRepMULTOPRfactor repMulOprFactor;
	public RepMultOprFactor(Token multOpr, IFactor factor, IRepMULTOPRfactor repMulOprFactor) {
		super();
		this.multOpr = multOpr;
		this.factor = factor;
		this.repMulOprFactor = repMulOprFactor;
	}
	
	@Override
	public List<IMULTOPRfactor> toAbs() {
		List<IMULTOPRfactor> list = new LinkedList<IAbsSyn.IMULTOPRfactor>();
		list.add(new MultOprFactor(multOpr, factor.toAbs()));
		list.addAll(repMulOprFactor.toAbs());
		return list;
	}
	
	
	
}
