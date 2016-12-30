package ch.fhnw.cpib.compiler.ast.classes;



import java.util.IllformedLocaleException;
import java.util.List;

import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IDeclaration;
import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IParameter;
import ch.fhnw.cpib.compiler.context.CompilerE;
import ch.fhnw.cpib.compiler.context.Context;
import ch.fhnw.cpib.compiler.context.Variable;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn.IBlockCmd;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.operators.FlowMode;
import ch.fhnw.cpib.compiler.scanner.enums.operators.Type;

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
	
	Variable retVar = null;
	private Context                    context  = null;
	private int                        location = -1;

	@Override
	public void check() {
		if (CompilerE.COMPILER.getCurrentContext() != CompilerE.COMPILER.getCurrentContext())
		      throw new RuntimeException("Functions can only be declared in global context.");
		if (CompilerE.COMPILER.getCurrentContext().exists(this.ident))
		      throw new RuntimeException(this.ident + " exists already.");
		
		CompilerE.COMPILER.getGlobalContext().addFunction(this);
	
	    assert this.context == null;
	    this.context = CompilerE.COMPILER.switchContext();
	    
	    //TODO: Idk
//	    this.context.addVariable(FRAME_POINTER_OLD, Type.INT32, ChangeMode.CONST);
//	    this.context.addVariable(EXTREME_POINTER, Type.INT32, ChangeMode.CONST);
//	    this.context.addVariable(PROGRAM_COUNTER_OLD, Type.INT32, ChangeMode.CONST);

	    this.storageDeclaration.check();
	    
	    assert this.retVar == null;
	    this.retVar = this.context.getVariable(this.storageDeclaration.getToken());
	    
	    assert this.retVar != null : "No return-variable for function available.";
	    this.retVar.setReturnVar(true);
	    this.retVar.setFlowMode(FlowMode.OUT);
	    
	    this.context.setReturnStoreDecl(this.retVar);
	    
	    //TODO: REally list?
	    for (IParameter param : parameters) {
	    	param.check();
		}

	    if (globalImports.size() > 0)
	      throw new RuntimeException(
	          "Functions can not import global variables. Use procedure with OUT-variable instead."); // Because functions should be functional!

	    
	    for (IGlobalImport globImp : globalImports) {
	    	globImp.check();
	    }
	    for (IDeclaration localDecl : storageDeclarations){
	    	localDecl.check();
	    }
	    for (ICommand iCommand : commands) {
			iCommand.check();
		}
	    CompilerE.COMPILER.returnContext();
	}

	@Override
	public Token getToken() {
		return ident;
	}

	public Type getType() {
		return storageDeclaration.getType();
	}

	public List<IGlobalImport> getGlobalImportList() {
		return this.globalImports;
	}

	public Variable getReturnVariable() {
		return this.retVar;
	}

	public List<IParameter> getParamList() {
		return parameters;
	}
	
	
}
