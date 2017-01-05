package ch.fhnw.cpib.compiler.ast.classes;


import java.util.List;

import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IExpression;
import ch.fhnw.cpib.compiler.context.CompilerE;
import ch.fhnw.cpib.compiler.context.Variable;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.operators.Type;
import ch.fhnw.cpib.compiler.scanner.tokens.IdentToken;
import ch.fhnw.cpib.compiler.vm.ICodeArray;
import ch.fhnw.cpib.compiler.vm.ICodeArray.CodeTooSmallError;


public class FunCallExpression implements IExpression{

	private IRoutineCall routineCall;
	private Type type = null;

	public FunCallExpression(IRoutineCall routineCall) {
		this.routineCall = routineCall;
	}
	
	
	
	@Override
	public void check() {
		assert this.type == null;
	    final Token ident = this.routineCall.getToken();
	    
	    final FunctionDeclaration function = CompilerE.COMPILER.getGlobalContext().getFunction(ident);
	    if (function == null)
	      throw new RuntimeException("Function does not exist.");

	    this.routineCall.setFunc(true);
	    this.routineCall.check();

	    this.type = function.getType();

	    List<IGlobalImport> l = function.getGlobalImportList();
	    
	    for (IGlobalImport iGlobalImport : l) {
			Token token = iGlobalImport.getToken();
			Variable var = CompilerE.COMPILER.getGlobalContext().getVariable(token);
			if (var == null) throw new RuntimeException("Importet variable does not exist" + token.toString());
			if (!var.isInitialized())
		          throw new RuntimeException("Imported variable " + token
		              + " was not initialized before call.");
	    }
	    
	    if (!function.getReturnVariable().isInitialized())
	      throw new RuntimeException("Return Value not set.");
	}

	@Override
	public Token getToken() {
		return routineCall.getToken();
	}

	@Override
	public Type getType() {
		return type;
	}



	@Override
	public int code(int i) throws CodeTooSmallError {
	    int loc = i;
	    //vm.DebugInfo(loc++, this.getClass().getSimpleName(), this.getToken());
	    loc = this.routineCall.code(loc);
	    
	    System.out.println("[ "+this.getClass().getSimpleName()+" ]");
	    for(int ii = i; ii < loc; ii++){
	    	if(CompilerE.COMPILER.getCodeArray().get(ii) != null)
	    		System.out.println(CompilerE.COMPILER.getCodeArray().get(ii).toString());
	    	else System.out.println("null <--------------------------");
	    }
	    return loc;
	}

}
