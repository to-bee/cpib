package ch.fhnw.cpib.compiler.ast.classes;


import java.util.List;

import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IDeclaration;
import ch.fhnw.cpib.compiler.context.CompilerE;
import ch.fhnw.cpib.compiler.context.Context;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.operators.Type;

public class ProcedureDeclaration implements IDeclaration{

	Token ident;
	List<IParameter> parameters;
	List<IGlobalImport> globalImports;
	List<IDeclaration> storageDeclarations;
	List<ICommand> commands;
	
	private Context context = null;
	private int location = -1;
	
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

	@Override
	public void check() {
		if (CompilerE.COMPILER.getCurrentContext().getParent() != null)
		      throw new RuntimeException(
		          "Procedures can only be declared in global context" + ident.toString());

	    if (CompilerE.COMPILER.getCurrentContext().getProcedure(this.ident) != null)
	      throw new RuntimeException("PROC " + this.ident + " exists already");

	    CompilerE.COMPILER.getCurrentContext().addProcedure(this);
	    assert this.context == null;
	    this.context = CompilerE.COMPILER.switchContext();

//		    /**
//		     * These Variables are set on vm.Enter to store the old pointers to return
//		     * to after function call. They are never used in the code, the declaration
//		     * here serves only the purpose of having the right relativePosition of
//		     * variables after vm.Enter
//		     */
//		    this.context.addVariable(FRAME_POINTER_OLD, Type.INT32, ChangeMode.CONST);
//		    this.context.addVariable(EXTREME_POINTER, Type.INT32, ChangeMode.CONST);
//		    this.context.addVariable(PROGRAM_COUNTER_OLD, Type.INT32, ChangeMode.CONST);

	    for (IParameter iParameter : parameters) {
			iParameter.check();
		}
	    for (IGlobalImport iGlobalImport : globalImports) {
			iGlobalImport.check();
		}
	    for (IDeclaration iDeclaration : storageDeclarations) {
			iDeclaration.check();
		}
	    for (ICommand iCommand : commands) {
			iCommand.check();
		}
	    
	    CompilerE.COMPILER.returnContext();
		
	}
	
	public List<IParameter> getParamList() {
	    return this.parameters;
	 }

	@Override
	public Token getToken() {
		return ident;
	}

	@Override
	public Type getType() {
		return null; //PRoc has no return type
	}
	
	public List<IGlobalImport> getGlobalImportList() {
	    return this.globalImports;
	}

	public Context getContext() {
	    return this.context;
	}

	
}
