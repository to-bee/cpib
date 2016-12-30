package ch.fhnw.cpib.compiler.ast.classes;

import java.util.List;

import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn;
import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.ICommand;
import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IDeclaration;
import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IProgramParameter;
import ch.fhnw.cpib.compiler.scanner.Token;

public class Program implements IAbsSyn.IProgram{

	Token ident;
	List<IAbsSyn.IProgramParameter> programList;
	List<IAbsSyn.IDeclaration> declarationList;
	List<IAbsSyn.ICommand> commandList;
	
	public Program(Token ident, List<IProgramParameter> programList,
			List<IDeclaration> declarationList, List<ICommand> commandList) {
		super();
		this.ident = ident;
		this.programList = programList;
		this.declarationList = declarationList;
		this.commandList = commandList;
	}

	@Override
	public void check() {
		for (IDeclaration iDeclaration : declarationList) {
			iDeclaration.check();
		}
		for (ICommand iCommand : commandList) {
			iCommand.check();
		}
	}

	@Override
	public void code(int i) {
		// TODO Auto-generated method stub
		
	}
	
}
