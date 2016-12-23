package ch.fhnw.cpib.compiler.classes;

import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn.IGlobalImport;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn.IRepeatingOptionalGlobalImports;
import ch.fhnw.cpib.compiler.scanner.Token;

public class RepeatingOptionalGlobalImports implements IConcSyn.IRepeatingOptionalGlobalImports {

	Token comma;
	IConcSyn.IGlobalImport globImp;
	IConcSyn.IRepeatingOptionalGlobalImports repOptGlobImp;
	public RepeatingOptionalGlobalImports(Token comma, IGlobalImport globImp,
			IRepeatingOptionalGlobalImports repOptGlobImp) {
		super();
		this.comma = comma;
		this.globImp = globImp;
		this.repOptGlobImp = repOptGlobImp;
	}
	
	
}
