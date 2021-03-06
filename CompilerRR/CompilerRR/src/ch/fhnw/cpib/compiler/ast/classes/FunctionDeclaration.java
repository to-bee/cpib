package ch.fhnw.cpib.compiler.ast.classes;




import java.util.List;

import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IDeclaration;
import ch.fhnw.cpib.compiler.context.CompilerE;
import ch.fhnw.cpib.compiler.context.Context;
import ch.fhnw.cpib.compiler.context.Variable;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.operators.ChangeMode;
import ch.fhnw.cpib.compiler.scanner.enums.operators.FlowMode;
import ch.fhnw.cpib.compiler.scanner.enums.operators.Type;
import ch.fhnw.cpib.compiler.vm.ICodeArray;
import ch.fhnw.cpib.compiler.vm.ICodeArray.CodeTooSmallError;
import ch.fhnw.cpib.compiler.vm.IInstructions;
import static ch.fhnw.cpib.compiler.scanner.tokens.PseudoToken.EXTREME_POINTER;
import static ch.fhnw.cpib.compiler.scanner.tokens.PseudoToken.FRAME_POINTER_OLD;
import static ch.fhnw.cpib.compiler.scanner.tokens.PseudoToken.PROGRAM_COUNTER_OLD;

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
	    
	    this.context.addVariable(FRAME_POINTER_OLD, Type.INT32, ChangeMode.CONST);
	    this.context.addVariable(EXTREME_POINTER, Type.INT32, ChangeMode.CONST);
	    this.context.addVariable(PROGRAM_COUNTER_OLD, Type.INT32, ChangeMode.CONST);

	    this.storageDeclaration.check();
	    
	    assert this.retVar == null;
	    this.retVar = this.context.getVariable(this.storageDeclaration.getToken());
	    this.retVar.setReturnVar(true);
	    this.retVar.setFlowMode(FlowMode.OUT);
	    this.context.setReturnStoreDecl(this.retVar);
	    
	    
	    int posOfPar = parameters.size();
	    for (IParameter param : parameters) {
	    	param.setLocationInParamList(posOfPar);
	    	posOfPar -= 1;
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

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}

	@Override
	public int code(int i) throws CodeTooSmallError {
		int loc = i;
	    final int jumpLoc = loc++;

	    CompilerE.COMPILER.switchToContext(this.context);
	    final ICodeArray codeArray =  CompilerE.COMPILER.getCodeArray();

	    this.setLocation(loc);
	    CompilerE.COMPILER.getCodeArray().put(loc++, new IInstructions.Enter(0,0));

	    loc = this.storageDeclaration.code(loc); // return variable, allocates 1 block
	    for(IParameter p : parameters)
	    	loc = p.code(loc);
	    for(IGlobalImport g : globalImports)
	    	loc = g.code(loc);
	    for(IDeclaration d : storageDeclarations) // locals
	    	loc = d.code(loc);
	    for(ICommand c : commands)
	    	loc = c.code(loc);

	    final int localVariableCount = this.context.getVariableCount() - 4
	    		- globalImports.size()
	    		- storageDeclarations.size();

	    // Store Return Variable on stack cell before funCall
	    final Variable var = CompilerE.COMPILER.getCurrentContext().getVariable(
	        this.storageDeclaration.getToken());
	    
	    codeArray.put(loc++, new IInstructions.LoadAddrRel(var.getRelLocation()));
	    codeArray.put(loc++, new IInstructions.Deref());
	    codeArray.put(loc++, new IInstructions.LoadAddrRel(-(parameters.size() + 1)));
	    codeArray.put(loc++, new IInstructions.Store());
	    
	    codeArray.put(loc++, new IInstructions.Return(localVariableCount));
	    CompilerE.COMPILER.returnContext();

	    // To jump over funDecl since it should not be executed until a Call to it
	    codeArray.put(jumpLoc, new IInstructions.UncondJump(loc));

	    return loc;
	}

	@Override
	public void print(String prefix) {
		ident.print(prefix);
		for(IParameter p : parameters){
			p.print(prefix + "-");
		}
		storageDeclaration.print(prefix);
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
