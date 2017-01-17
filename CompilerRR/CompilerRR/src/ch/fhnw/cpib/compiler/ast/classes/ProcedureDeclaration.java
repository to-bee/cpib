package ch.fhnw.cpib.compiler.ast.classes;


import java.util.List;

import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IDeclaration;
import ch.fhnw.cpib.compiler.context.CompilerE;
import ch.fhnw.cpib.compiler.context.Context;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.operators.ChangeMode;
import ch.fhnw.cpib.compiler.scanner.enums.operators.Type;
import ch.fhnw.cpib.compiler.vm.CodeArray;
import ch.fhnw.cpib.compiler.vm.ICodeArray.CodeTooSmallError;
import ch.fhnw.cpib.compiler.vm.IInstructions;
import static ch.fhnw.cpib.compiler.scanner.tokens.PseudoToken.EXTREME_POINTER;
import static ch.fhnw.cpib.compiler.scanner.tokens.PseudoToken.FRAME_POINTER_OLD;
import static ch.fhnw.cpib.compiler.scanner.tokens.PseudoToken.PROGRAM_COUNTER_OLD;

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
	    
	    this.context.addVariable(FRAME_POINTER_OLD, Type.INT32, ChangeMode.CONST);
	    this.context.addVariable(EXTREME_POINTER, Type.INT32, ChangeMode.CONST);
	    this.context.addVariable(PROGRAM_COUNTER_OLD, Type.INT32, ChangeMode.CONST);


	    int posOfPar = parameters.size();
	    for (IParameter iParameter : parameters) {
	    	iParameter.setLocationInParamList(posOfPar);
	    	posOfPar -= 1;
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

	@Override
	public int code(int i) throws CodeTooSmallError {
	    int loc = i;
	    final int jumpLoc = loc++;

	    CompilerE.COMPILER.switchToContext(this.context);

	    this.setLocation(loc);
	    
	    CompilerE.COMPILER.getCodeArray().put(loc++, new IInstructions.Enter(0,0));

	    for(IParameter p: parameters){
	    	loc = p.code(loc);
	    }
	    for(IGlobalImport g : globalImports){
	    	loc = g.code(loc);
	    }
	    for(IDeclaration d : storageDeclarations){
	    	loc = d.code(loc);
	    }
	    for(ICommand c : commands){
	    	loc = c.code(loc);
	    }

	    final int size = this.context.getVariableCount() - 3
	        - this.globalImports.size();

	    CompilerE.COMPILER.getCodeArray().put(loc++, new IInstructions.Return(size));
	    CompilerE.COMPILER.returnContext();

	    // To jump over procDecl since it should not be executed until a Call to it
	    CompilerE.COMPILER.getCodeArray().put(jumpLoc, new IInstructions.UncondJump(loc));

	    return loc;
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}

	@Override
	public void print(String prefix) {
		ident.print(prefix);
		
		for(IParameter p : parameters){
			p.print(prefix + "-");
		}
		for(IGlobalImport g : globalImports){
			g.print(prefix + "-");
		}
		for(IDeclaration d : storageDeclarations){
			d.print(prefix + "-");
		}
		for(ICommand c : commands){
			c.print(prefix + "-");
		}
	}

	
}
