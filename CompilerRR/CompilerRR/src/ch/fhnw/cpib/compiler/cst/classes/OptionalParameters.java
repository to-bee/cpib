package ch.fhnw.cpib.compiler.cst.classes;

import java.util.LinkedList;
import java.util.List;

import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;

public class OptionalParameters implements IConcSyn.IOptionalParameters {

	IConcSyn.IParameter param;
	IConcSyn.IRepeatingOptionalParameters repOptPar;
	public OptionalParameters(IParameter param, IRepeatingOptionalParameters repOptPar) {
		super();
		this.param = param;
		this.repOptPar = repOptPar;
	}
	
	@Override
	public List<ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IParameter> toAbs() {
		List<ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IParameter>
		params = new LinkedList<IAbsSyn.IParameter>();
		params.add(param.toAbs());
		params.addAll(repOptPar.toAbs());
		return params;
	};
	
	
}
