package ch.fhnw.cpib.compiler.ast.classes;

import java.util.List;

import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IDeclaration;
import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IProgramParameter;
import ch.fhnw.cpib.compiler.scanner.Token;

public class ProcedureDeclaration implements IDeclaration{

	Token ident;
	List<IParameter> parameters;
	List<IGlobalImport> globalImports;
	List<IDeclaration> storageDeclarations;
	List<ICommand> commands;
	
	public ProcedureDeclaration(Token ident, List<IParameter> parameters,
			List<IGlobalImport> globalImports,
			List<IDeclaration> storageDeclarations,
			List<ICommand> commands) {
		super();
		this.ident = ident;
		this.parameters = parameters;
		this.globalImports = globalImports;
		this.storageDeclarations = storageDeclarations;
		this.commands = commands;
	}

	
}
