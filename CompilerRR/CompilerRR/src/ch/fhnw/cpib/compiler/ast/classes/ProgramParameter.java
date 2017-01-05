package ch.fhnw.cpib.compiler.ast.classes;

import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn;
import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.ITypedIdent;
import ch.fhnw.cpib.compiler.context.CompilerE;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.vm.ICodeArray.CodeTooSmallError;

public class ProgramParameter implements IAbsSyn.IProgramParameter{

	Token flowMode;
	Token changeMode;
	IAbsSyn.ITypedIdent typeIdent;
	
	public ProgramParameter(Token flowMode, Token changeMode,
			ITypedIdent typeIdent) {
		super();
		this.flowMode = flowMode;
		this.changeMode = changeMode;
		this.typeIdent = typeIdent;
	}

	@Override
	public void check() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int code(int i) throws CodeTooSmallError {
		return i;
	}

}
