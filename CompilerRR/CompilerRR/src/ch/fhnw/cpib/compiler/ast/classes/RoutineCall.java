package ch.fhnw.cpib.compiler.ast.classes;


import java.util.LinkedList;
import java.util.List;

import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IExpression;
import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IParameter;
import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IRoutineCall;
import ch.fhnw.cpib.compiler.context.CompilerE;
import ch.fhnw.cpib.compiler.context.Variable;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.operators.FlowMode;
import ch.fhnw.cpib.compiler.scanner.enums.operators.MechMode;
import ch.fhnw.cpib.compiler.vm.ICodeArray.CodeTooSmallError;
import ch.fhnw.cpib.compiler.vm.IInstructions;

public class RoutineCall implements IRoutineCall {
	
	 private Token ident;
	 
	 // Übergebene Params
	 private List<IExpression>   paramCallList;
	 private boolean             isFunc = false;

	 //Erwartete Dekl
	 private List<IParameter>   paramList;

	public RoutineCall(Token ident, List<IExpression> paramCallList) {
		super();
		this.ident = ident;
		this.paramCallList = paramCallList;
		this.paramList = new LinkedList<IParameter>();
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
	      
	      //Space for ret var
	      CompilerE.COMPILER.getCodeArray().put(loc++, new IInstructions.AllocBlock(1));
	    } else {
	      routineLocation =  CompilerE.COMPILER.getCurrentContext().getProcedure(this.ident)
	          .getLocation();
	    }
	    
		for(int j = 0; j < this.paramCallList.size(); j++){
			IParameter var = paramList.get(j);
			
			//TODO: Change back
			//Allow Expressions as funparam if copy in
			if (var.getFlowMode() ==  FlowMode.IN && var.getMechMode() == MechMode.COPY && paramCallList.get(j) instanceof LiteralExpression) { //TODO: check if copy in, allow literalexpr
				LiteralExpression litExpr = (LiteralExpression) paramCallList.get(j);
				loc = litExpr.code(loc);
			} else if (var.getFlowMode() ==  FlowMode.IN && var.getMechMode() == MechMode.COPY && paramCallList.get(j) instanceof DyadicExpression){
				DyadicExpression litExpr = (DyadicExpression) paramCallList.get(j);
				loc = litExpr.code(loc);
			} else if (var.getFlowMode() ==  FlowMode.IN && var.getMechMode() == MechMode.COPY && paramCallList.get(j) instanceof MonadicExpression){
				MonadicExpression litExpr = (MonadicExpression) paramCallList.get(j);
				loc = litExpr.code(loc);
			} else {
				StoreExpression storeEx = (StoreExpression) paramCallList.get(j);
				if (var.getMechMode() == MechMode.REF) storeEx.setWrite(true);
				
				loc = storeEx.code(loc);
			}
			
			
		}
		
	    CompilerE.COMPILER.getCodeArray().put(loc++, new IInstructions.Call(routineLocation));
	    //CompilerE.COMPILER.getCodeArray().put(loc++, new IInstructions.
	    
	    
	    return loc;
	}

	@Override
	public void print(String prefix) {

		 ident.print(prefix);
		 
		 for (IExpression e : paramCallList){
			 e.print(prefix+"-");
		 }
		 for(IParameter p : paramList){
			 p.print(prefix + "-");
		 }
		 
	}
	 
	 

}
