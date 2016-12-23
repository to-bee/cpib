package ch.fhnw.cpib.compiler.cst.classes;

import java.util.LinkedList;
import java.util.List;

import ch.fhnw.cpib.compiler.ast.classes.MultOprFactor;
import ch.fhnw.cpib.compiler.ast.classes.RelOprTerm2;
import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn;
import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IMULTOPRfactor;
import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IRELOPRterm2;
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
	public List<IRELOPRterm2> toAbs() {
		List<IRELOPRterm2> list = new LinkedList<IAbsSyn.IRELOPRterm2>();
		list.add(new RelOprTerm2(relOpr, t2.toAbs()));
		list.addAll(repRelOprT2.toAbs());
		return list;
	}
	
}
