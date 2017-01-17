package ch.fhnw.cpib.compiler.ast.classes;


import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IExpression;
import ch.fhnw.cpib.compiler.context.CompilerE;
import ch.fhnw.cpib.compiler.context.Variable;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.operators.ChangeMode;
import ch.fhnw.cpib.compiler.scanner.enums.operators.FlowMode;
import ch.fhnw.cpib.compiler.scanner.enums.operators.MechMode;
import ch.fhnw.cpib.compiler.scanner.enums.operators.Scope;
import ch.fhnw.cpib.compiler.scanner.enums.operators.Type;
import ch.fhnw.cpib.compiler.vm.ICodeArray.CodeTooSmallError;
import ch.fhnw.cpib.compiler.vm.IInstructions;

public class StoreExpression implements IExpression{

	private Token ident;
	private boolean isInit;

	
	private Scope               scope;
	private MechMode            mechMode;
	private ChangeMode          changeMode;
	private FlowMode            flowMode;

	private boolean             isWrite = false;
	private Type type = null;
	
	public StoreExpression(Token ident, boolean isInit) {
		super();
		this.ident = ident;
		this.isInit = isInit;
	}
	
	public boolean isInit() {
	  return this.isInit;
	}

	public boolean isWrite() {
	  return this.isWrite;
	}

	public void setWrite(final boolean isWrite) {
	  this.isWrite = isWrite;
	}

	@Override
	public void check() {
		final Variable var = CompilerE.COMPILER.getCurrentContext().getVariable(this.ident);

	    if (var == null)
	      throw new RuntimeException("Variable '" + this.ident.toString()
	          + "' does not exist.");

	    /**
	     * If variable has not been initialized check if this is initialization.
	     * Only in global context, not in context of Proc- or FunDecl
	     */
	    if (CompilerE.COMPILER.getCurrentContext().getParent() == null
	        || var.getFlowMode() == FlowMode.OUT) {
	      if (!var.isInitialized()) {
	        if (this.isInit) {
	          var.setInitialized(true);
	        } else {
	          throw new RuntimeException("Variable has not been initialized yet");
	        }
	      } else if (this.isInit) {
	        /** If variable already initialized ensure this is no initialization. */
	        throw new RuntimeException(
	            "Initialisation on already initialised variable");
	      }
	    }

	    this.type = var.getType();
	    this.scope = var.getScope();
	    this.mechMode = var.getMechMode();
	    this.changeMode = var.getChangeMode();
	    this.flowMode = var.getFlowMode();

	    if (this.isWrite) {
	      if (this.changeMode == ChangeMode.CONST && !this.isInit) {
	        throw new RuntimeException("No write access to const allowed");
	      } else if (this.flowMode == FlowMode.IN) { throw new RuntimeException(
	          "No write acces to variable marked as IN allowed"); }
	    }
	}

	@Override
	public Token getToken() {
		return this.ident;
	}

	@Override
	public Type getType() {
		return type;
	}

	@Override
	public int code(int i) throws CodeTooSmallError {
		
	    int loc = i;
	    
	    final Variable var = CompilerE.COMPILER.getCurrentContext().getVariable(this.ident);

	    if (this.scope == Scope.GLOBAL) {
	    	CompilerE.COMPILER.getCodeArray().put(loc++, new IInstructions.LoadImInt(var.getAbsLocation()));
	    } else {
	    	CompilerE.COMPILER.getCodeArray().put(loc++, new IInstructions.LoadAddrRel(var.getRelLocation()));
	    }

	    // If MechMode.REF, get the address of the referenced variable
	    if (var.getMechMode() == MechMode.REF) 
	    	CompilerE.COMPILER.getCodeArray().put(loc++, new IInstructions.Deref());


	    if (!this.isWrite) {
	    	CompilerE.COMPILER.getCodeArray().put(loc++, new IInstructions.Deref());
	    }
	    
	    return loc;
	}

	@Override
	public void print(String prefix) {
		ident.print(prefix);
	}

}
