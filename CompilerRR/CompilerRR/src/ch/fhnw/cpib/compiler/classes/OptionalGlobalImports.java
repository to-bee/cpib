package ch.fhnw.cpib.compiler.classes;

import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn.IGlobalImport;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn.IRepeatingOptionalGlobalImports;
import ch.fhnw.cpib.compiler.scanner.Token;

public class OptionalGlobalImports implements IConcSyn.IOptionalGlobalImports {

	Token global;
	IConcSyn.IGlobalImport globImp;
	IConcSyn.IRepeatingOptionalGlobalImports repOptGlobImp;
	public OptionalGlobalImports(Token global, IGlobalImport globImp, IRepeatingOptionalGlobalImports repOptGlobImp) {
		super();
		this.global = global;
		this.globImp = globImp;
		this.repOptGlobImp = repOptGlobImp;
	}
	
	
}
