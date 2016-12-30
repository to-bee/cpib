package ch.fhnw.cpib.compiler.cst.classes;


import ch.fhnw.cpib.compiler.ast.classes.DyadicExpression;
import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn;
import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IMULTOPRfactor;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn.IRepRELOPRterm2;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn.ITerm2;
import ch.fhnw.cpib.compiler.scanner.Token;

public class RepRelOprTerm2 implements IConcSyn.IRepRELOPRterm2 {

	Token relOpr;
	IConcSyn.ITerm2 t2;
	IConcSyn.IRepRELOPRterm2 repRelOprT2;
	
	public RepRelOprTerm2(Token relOpr, ITerm2 t2, IRepRELOPRterm2 repRelOprT2) {
		super();
		this.relOpr = relOpr;
		this.t2 = t2;
		this.repRelOprT2 = repRelOprT2;
	}

	@Override
	public ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IExpression toAbs(ITerm2 t2) {
		return new DyadicExpression(relOpr, t2.toAbs(), this.repRelOprT2.toAbs(this.t2));
	}
	

	
}
