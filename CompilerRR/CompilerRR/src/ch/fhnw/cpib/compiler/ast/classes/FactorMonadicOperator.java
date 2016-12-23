package ch.fhnw.cpib.compiler.ast.classes;

import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IFactor;
import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IMonadicOperator;

public class FactorMonadicOperator implements IFactor {
	IMonadicOperator monadicOperator;
	IFactor factor;

	public FactorMonadicOperator(IMonadicOperator monadicOperator,
			IFactor factor) {
		super();
		this.monadicOperator = monadicOperator;
		this.factor = factor;
	}

}
