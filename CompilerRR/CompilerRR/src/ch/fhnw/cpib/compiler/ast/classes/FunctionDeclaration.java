package ch.fhnw.cpib.compiler.ast.classes;

import java.util.List;

import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IDeclaration;
import ch.fhnw.cpib.compiler.scanner.Token;

public class FunctionDeclaration implements IDeclaration{

	Token ident;
	List<IParameter> parameters;
	IDeclaration storageDeclaration;
	List<IGlobalImport> globalImports;
	List<IDeclaration> storageDeclarations;
	List<ICommand> commands;
	
	public FunctionDeclaration(
			Token ident, 
			List<IParameter> parameters,
			IDeclaration storageDeclaration,
			List<IGlobalImport> globalImports,
			List<IDeclaration> storageDeclarations,
			List<ICommand> commands) {
		
		super();
		this.ident = ident;
		this.parameters = parameters;
		this.storageDeclaration = storageDeclaration;
		this.globalImports = globalImports;
		this.storageDeclarations = storageDeclarations;
		this.commands = commands;
	}
	
	
}
