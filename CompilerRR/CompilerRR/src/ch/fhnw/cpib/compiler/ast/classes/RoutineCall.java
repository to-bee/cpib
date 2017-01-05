package ch.fhnw.cpib.compiler.ast.classes;


import java.util.List;

import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IExpression;
import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IParameter;
import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IRoutineCall;
import ch.fhnw.cpib.compiler.context.CompilerE;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.operators.MechMode;
import ch.fhnw.cpib.compiler.vm.ICodeArray.CodeTooSmallError;
import ch.fhnw.cpib.compiler.vm.IInstructions;

public class RoutineCall implements IRoutineCall {
	
	 private Token ident;
	 private List<IExpression>   paramCallList;
	 private boolean             isFunc = false;

	 private List<IParameter>   paramList;

	public RoutineCall(Token ident, List<IExpression> paramCallList) {
		super();
		this.ident = ident;
		this.paramCallList = paramCallList;
	}
	
	@Override
	public void setFunc(final boolean isFunc) {
		this.isFunc = isFunc;
	}

	@Override
	public Token getToken() {
		return ident;
	}

	@Override
	public void check() {

		if (this.paramCallList != null) {
			for (IExpression iExpression : paramCallList) {
				iExpression.check();
			}
		}

	    if (this.isFunc) {
	      this.paramList = CompilerE.COMPILER.getCurrentContext().getFunction(this.ident)
	          .getParamList();
	    } else {
	      this.paramList = CompilerE.COMPILER.getCurrentContext().getProcedure(this.ident)
	          .getParamList();
	    }

	    
	    //Check size
	    if (paramList.size() != paramCallList.size()) {
			throw new RuntimeException("Length of parameter list is incorrect: ");
		}
	    
	    
	    //Check types
	    for (int i = 0; i < paramList.size(); i++) {
			if (paramList.get(i).getType() != paramCallList.get(i).getType()) {
				throw new RuntimeException("Parameter Types do not match");
			}
	    	
		}
	    
		
	}

	@Override
	public int code(int i) throws CodeTooSmallError {
	    int loc = i;
	    int routineLocation;

	    if (this.isFunc) {
	      routineLocation = CompilerE.COMPILER.getCurrentContext().getFunction(this.ident)
	          .getLocation();
	      // Allocate Stack cell for return variable
	      // TODO: allocstack or allocblock?
	      CompilerE.COMPILER.getCodeArray().put(loc++, new IInstructions.AllocBlock(1));
	    } else {
	      routineLocation =  CompilerE.COMPILER.getCurrentContext().getProcedure(this.ident)
	          .getLocation();
	    }
	    
		for(int j = 0; j < this.paramCallList.size(); j++){
			
			IExpression expression = paramCallList.get(j);
			if(expression instanceof StoreExpression){
				StoreExpression storeEx = (StoreExpression) expression;
				IParameter parameter = paramList.get(j);
				if (parameter.getMechMode() == MechMode.REF) storeEx.setWrite(true);
			}
			
			loc = expression.code(loc);
		}
		
	    CompilerE.COMPILER.getCodeArray().put(loc++, new IInstructions.Call(routineLocation));
	    
	    System.out.println("[ "+this.getClass().getSimpleName()+" ]");
	    for(int ii = i; ii < loc; ii++){
	    	if(CompilerE.COMPILER.getCodeArray().get(ii) != null)
	    		System.out.println(CompilerE.COMPILER.getCodeArray().get(ii).toString());
	    	else System.out.println("null <--------------------------");
	    }
	    return loc;
	}
	 
	 

}
