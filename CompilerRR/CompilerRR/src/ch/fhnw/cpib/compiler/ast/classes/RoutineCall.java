package ch.fhnw.cpib.compiler.ast.classes;


import java.util.List;

import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IExpression;
import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IParameter;
import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IRoutineCall;
import ch.fhnw.cpib.compiler.context.CompilerE;
import ch.fhnw.cpib.compiler.scanner.Token;

public class RoutineCall implements IRoutineCall {
	
	 private Token ident;
	 private List<IExpression>        paramCallList;
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
	 
	 

}
