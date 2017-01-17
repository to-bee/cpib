package ch.fhnw.cpib.compiler.ast.classes;



import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IGlobalImport;
import ch.fhnw.cpib.compiler.context.CompilerE;
import ch.fhnw.cpib.compiler.context.Variable;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.Operators;
import ch.fhnw.cpib.compiler.scanner.enums.operators.ChangeMode;
import ch.fhnw.cpib.compiler.scanner.enums.operators.FlowMode;
import ch.fhnw.cpib.compiler.scanner.enums.operators.Scope;
import ch.fhnw.cpib.compiler.vm.ICodeArray;
import ch.fhnw.cpib.compiler.vm.ICodeArray.CodeTooSmallError;

public class GlobalImport implements IGlobalImport {
	Token flowMode;
	Token changeMode;
	Token ident;

	public GlobalImport(Token flowMode, Token changeMode, Token ident) {
		super();
		this.flowMode = flowMode;
		this.changeMode = changeMode;
		this.ident = ident;
	}

	@Override
	public void check() {
		Variable var = CompilerE.COMPILER.getGlobalContext().getVariable(this.ident);
	    if (var == null || var.getScope() != Scope.GLOBAL)
	      throw new RuntimeException("Variable " + this.ident + " is not global.");
	    CompilerE.COMPILER.getCurrentContext().importVariable(var);

	    Variable localVar = CompilerE.COMPILER.getCurrentContext().getVariable(this.ident);
	    
	    
	    //TODO: Probably should have done this in Scanner?
	    switch (this.changeMode.getOperator()) {
		case VAR:
			localVar.setChangeMode(ChangeMode.VAR);
			break;
		case CONST:
			localVar.setChangeMode(ChangeMode.CONST);
			break;
		default:
			break;
		}
	    
		switch (this.flowMode.getOperator()) {
		case IN:
			localVar.setFlowMode(FlowMode.IN);
			break;
		case OUT:
			localVar.setFlowMode(FlowMode.OUT);
			break;
		case INOUT:
			localVar.setFlowMode(FlowMode.INOUT);
			break;
		default:
			break;
		}
		
	}

	@Override
	public Token getToken() {
		return ident;
	}

	@Override
	public int code(int i) throws CodeTooSmallError {
	    return i;
	}

	@Override
	public void print(String prefix) {
		flowMode.print(prefix);;
		changeMode.print(prefix);;
		ident.print(prefix);;
	}

}
