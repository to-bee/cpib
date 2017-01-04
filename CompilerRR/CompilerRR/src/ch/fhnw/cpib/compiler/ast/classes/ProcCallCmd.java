package ch.fhnw.cpib.compiler.ast.classes;



import static ch.fhnw.cpib.iml.compiler.Compiler.COMPILER;

import java.util.List;

import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.ICommand;
import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IExpression;
import ch.fhnw.cpib.compiler.context.CompilerE;
import ch.fhnw.cpib.compiler.context.Context;
import ch.fhnw.cpib.compiler.context.Variable;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.operators.Type;
import ch.fhnw.cpib.compiler.vm.ICodeArray.CodeTooSmallError;
import ch.fhnw.cpib.iml.vm.IVirtualMachine;

public class ProcCallCmd implements ICommand{
	
	IRoutineCall  routineCall;
	List<Token> globalInitList;
	
	public ProcCallCmd(IRoutineCall routineCall, List<Token> globalInitList) {
		super();
		this.routineCall = routineCall;
		this.globalInitList = globalInitList;
	}

	@Override
	public void check() {
		Context globalContext = CompilerE.COMPILER.getGlobalContext();
	    Token ident = this.routineCall.getToken();
	    ProcedureDeclaration procedure = globalContext.getProcedure(ident);

	    if (procedure == null)
	      throw new RuntimeException("Procedure does not exist." + ident.toString());

	    this.routineCall.check();
	    
	    
	    //Check the list of inits
	    for (Token token : globalInitList) {
	    	Variable var = CompilerE.COMPILER.getCurrentContext().getVariable(token);
	    	
	    	if (var == null)
	    		throw new RuntimeException("Variable " + token + " not found");
	    	
	    	if (var.isInitialized())
	    		throw new RuntimeException("Variable " + token + " already initialized");
	    	
	    	Type t = var.getType();
	    	
	    	var.setInitialized(true);
		}
		
	}

	@Override
	public Token getToken() {
		return routineCall.getToken();
	}

	@Override
	public int code(int i) throws CodeTooSmallError {
	    int loc = i;
	    loc = this.globalInitList.code(loc);
	    loc = this.routineCall.code(loc);
	    return loc;
	}
	
	
}
