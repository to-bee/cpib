package ch.fhnw.cpib.compiler.ast.classes;


import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.ICommand;
import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IExpression;
import ch.fhnw.cpib.compiler.context.CompilerE;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.vm.ICodeArray;
import ch.fhnw.cpib.compiler.vm.ICodeArray.CodeTooSmallError;
import ch.fhnw.cpib.compiler.vm.IInstructions.*;

public class OutputCmd implements ICommand{
	
	IExpression expression;

	public OutputCmd(IExpression expression) {
		super();
		this.expression = expression;
	}

	@Override
	public void check() {
		this.expression.check();		
	}
	
	 @Override
	 public Token getToken() {
	    return this.expression.getToken();
	 }

	@Override
	public int code(int i) throws CodeTooSmallError {
		final ICodeArray codeArr = CompilerE.COMPILER.getCodeArray();
	    int loc = i;
	    //vm.DebugInfo(loc++, this.getClass().getSimpleName(), this.getToken());

	    loc = this.expression.code(loc);

	    switch (this.expression.getType()) {
	    case BOOL:
	    	codeArr.put(loc++, new OutputBool(this.expression.getToken().toString()));  
	    	break;
	    case INT32:
	    	codeArr.put(loc++, new OutputInt(this.expression.getToken().toString()));  
	      	break;
	    default:
	      new RuntimeException("Unknown type!");
	    }
	    
	    System.out.println("[ "+this.getClass().getSimpleName()+" ]");
	    for(int ii = i; ii < loc; ii++){
	    	if(CompilerE.COMPILER.getCodeArray().get(ii) != null)
	    		System.out.println(CompilerE.COMPILER.getCodeArray().get(ii).toString());
	    	else System.out.println("null <--------------------------");
	    }
	    return loc;
	}
	
	
}
