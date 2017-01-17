package ch.fhnw.cpib.compiler.ast.classes;

import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.ICommand;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.vm.ICodeArray.CodeTooSmallError;

public class SkipCmd implements ICommand{

	@Override
	public void check() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Token getToken() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int code(int i) throws CodeTooSmallError {
		return i;
	}

	@Override
	public void print(String prefix) {
		System.out.println("SKIP");
	}

	
}
