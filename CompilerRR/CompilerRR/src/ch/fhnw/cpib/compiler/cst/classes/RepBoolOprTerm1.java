package ch.fhnw.cpib.compiler.cst.classes;

import java.util.LinkedList;
import java.util.List;

import ch.fhnw.cpib.compiler.ast.classes.DyadicExpression;
import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn.IRepBOOLOPRterm1;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn.ITerm1;
import ch.fhnw.cpib.compiler.scanner.Token;

public class RepBoolOprTerm1 implements IConcSyn.IRepBOOLOPRterm1 {

	Token boolopr;
	IConcSyn.ITerm1 term1;
	IConcSyn.IRepBOOLOPRterm1 repBoolOprT1;
	public RepBoolOprTerm1(Token boolopr, ITerm1 term1, IRepBOOLOPRterm1 repBoolOprT1) {
		super();
		this.boolopr = boolopr;
		this.term1 = term1;
		this.repBoolOprT1 = repBoolOprT1;
	}
	
	@Override
	public ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IExpression toAbs(ITerm1 term1Sec) {
		return new DyadicExpression(this.boolopr, term1Sec.toAbs(), this.repBoolOprT1.toAbs(this.term1));
	}
	
	
}
