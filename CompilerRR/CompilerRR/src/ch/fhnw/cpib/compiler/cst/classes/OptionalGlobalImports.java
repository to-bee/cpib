package ch.fhnw.cpib.compiler.cst.classes;

import java.util.LinkedList;
import java.util.List;

import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
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
	@Override
	public List<ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IGlobalImport> toAbs() {
		List<ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IGlobalImport>
		imports = new LinkedList<IAbsSyn.IGlobalImport>();
		imports.add(globImp.toAbs());
		imports.addAll(repOptGlobImp.toAbs());
		return imports;
	}
	
	
}
