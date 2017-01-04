package ch.fhnw.cpib.compiler.ast.classes;


import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn;
import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IDeclaration;
import ch.fhnw.cpib.compiler.context.CompilerE;
import ch.fhnw.cpib.compiler.context.Context;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.Operators;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;
import ch.fhnw.cpib.compiler.scanner.enums.operators.ChangeMode;
import ch.fhnw.cpib.compiler.scanner.enums.operators.Type;
import ch.fhnw.cpib.compiler.vm.ICodeArray.CodeTooSmallError;
import ch.fhnw.cpib.compiler.vm.IInstructions;

//A var
public class StorageDeclaration implements IDeclaration {
	
	//Const or VAR
	Token changeMode;
	//Ident and Ident/Type
	IAbsSyn.ITypedIdent typeIdent;

	public StorageDeclaration(Token changeMode, ITypedIdent typeIdent) {
		super();
		this.changeMode = changeMode;
		this.typeIdent = typeIdent;
	}

	@Override
	public void check() {
		// Note that the caller of this method can be a Parameter.
	    // When this call returns the variable is known to the context.
	    // Some of the modes are set to default values and must be altered
	    // afterwards.
		
	    final Context context = CompilerE.COMPILER.getCurrentContext();

	    if(context.exists(this.typeIdent.getToken()))
	      throw new RuntimeException("Duplicate decleration.");

	    ChangeMode newMode = null;
	    if (changeMode.getTerminal() == Terminals.CHANGEMODE) {
	    	if (changeMode.getOperator() == Operators.VAR || changeMode.getOperator() == Operators.CONST) {
	    		newMode = (changeMode.getOperator() == Operators.VAR) ? ChangeMode.VAR : ChangeMode.CONST;
	    	}
		}
	    if (newMode == null) {
			throw new RuntimeException("Wrong Operator. Only Var/Const allowed");
		}
	    typeIdent.check();
	    context.addVariable(this.typeIdent.getToken(), typeIdent.getType(), newMode);
	}

	@Override
	public Token getToken() {
		return typeIdent.getToken();
	}

	@Override
	public Type getType() {
		return typeIdent.getType();
	}

	@Override
	public int code(int i) throws CodeTooSmallError {
	    // Note that the caller of this method can be a Parameter.
	    // When this call returns the variable is known to the context.
	    // Some of the modes are set to default values and must be altered
	    // afterwards.

	    int loc = i;
	    // Allocate one cell on the stack for the variable value:
	    // TODO: allocstack or allocblock?
	    CompilerE.COMPILER.getCodeArray().put(loc++, new IInstructions.AllocBlock(1));

	    return loc;
	}

}
