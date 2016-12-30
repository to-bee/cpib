package ch.fhnw.cpib.compiler.ast.classes;


import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IParameter;
import ch.fhnw.cpib.compiler.context.CompilerE;
import ch.fhnw.cpib.compiler.context.Variable;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.operators.FlowMode;
import ch.fhnw.cpib.compiler.scanner.enums.operators.MechMode;
import ch.fhnw.cpib.compiler.scanner.enums.operators.Type;

public class Parameter implements IParameter {
	//IN,OUT,INOUT
	Token flowMode;
	//REF, COPY
	Token mechMode;
	
	IDeclaration storageDeclaration;
	
	private int locationInParamList = 0;

	public Parameter(Token flowMode, Token mechMode, IDeclaration storageDeclaration2) {
		super();
		this.flowMode = flowMode;
		this.mechMode = mechMode;
		this.storageDeclaration = storageDeclaration2;
	}
	
	@Override
	public Token getToken() {
	    return this.storageDeclaration.getToken();
	}

	@Override
	public Type getType() {
	    return this.storageDeclaration.getType();
	}

	@Override
	public void check() {
		final Token token = this.storageDeclaration.getToken();
	    if (CompilerE.COMPILER.getCurrentContext().getVariable(token) != null)
	      throw new RuntimeException("Parameter " + token.getOperator()
	          + " already exists.");

	    this.storageDeclaration.check();
	    Variable var = CompilerE.COMPILER.getCurrentContext().getVariable(token);
	    assert var != null;

	    //TODO: In Scanner?
	    switch (flowMode.getOperator()) {
		case IN:
			var.setFlowMode(FlowMode.IN);
			break;
		case OUT:
			var.setFlowMode(FlowMode.OUT);
			break;
		case INOUT:
			var.setFlowMode(FlowMode.INOUT);
			break;
		default:
			throw new RuntimeException("Unable to set FlowMode" + flowMode.toString());
		}
	    
	    switch (mechMode.getOperator()) {
		case REF:
			var.setMechMode(MechMode.REF);
			break;
		case COPY:
			var.setMechMode(MechMode.COPY);
			break;
		default:
			throw new RuntimeException("Unable to set MechMode" + flowMode.toString());
		}
	    
	    var.setParameter(true);
	    var.setParamLocation(this.locationInParamList);
	}
	
	@Override
	public void setLocationInParamList(final int location) {
		this.locationInParamList = location;
	}
	@Override
	public FlowMode getFlowMode() {
		switch (flowMode.getOperator()) {
		case IN:
			return FlowMode.IN;
		case OUT:
			return FlowMode.OUT;
		case INOUT:
			return FlowMode.INOUT;
		default:
			return FlowMode.IN;
		}
	}
	
	@Override
	public MechMode getMechMode() {
		switch (mechMode.getOperator()) {
		case REF:
			return MechMode.REF;
		case COPY:
			return MechMode.COPY;
		default:
			return MechMode.COPY;
		}
	}

}
