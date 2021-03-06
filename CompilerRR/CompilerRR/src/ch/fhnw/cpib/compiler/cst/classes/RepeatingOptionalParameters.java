package ch.fhnw.cpib.compiler.cst.classes;

import java.util.LinkedList;
import java.util.List;

import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.scanner.Token;

public class RepeatingOptionalParameters implements IConcSyn.IRepeatingOptionalParameters {

	Token comma;
	IConcSyn.IParameter param;
	IConcSyn.IRepeatingOptionalParameters repOptParams;
	public RepeatingOptionalParameters(Token comma, IParameter param, IRepeatingOptionalParameters repOptParams) {
		super();
		this.comma = comma;
		this.param = param;
		this.repOptParams = repOptParams;
	}
	
	@Override
	public List<ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IParameter> toAbs() {
		List<ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IParameter>
		params = new LinkedList<IAbsSyn.IParameter>();
		params.add(param.toAbs());
		params.addAll(repOptParams.toAbs());
		return params;
	}
	
	
}
