package ch.fhnw.cpib.compiler.ast.classes;


import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn;
import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IDeclaration;
import ch.fhnw.cpib.compiler.context.CompilerE;
import ch.fhnw.cpib.compiler.context.Context;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.operators.Type;

//A var
public class StorageDeclaration implements IDeclaration {

	
	Token changeMode;
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
//TODO: Figure the whole Typedidetn shit out
//	    final Context context = CompilerE.COMPILER.getCurrentContext();
//
//	    if(context.exists(this.ident))
//	      throw new ContextException("Duplicate decleration.", this.ident);
//
//	    context.addVariable(this.ident,
//	        Type.ofAttribute(this.type),
//	        this.changeMode.getValue());
	}

	@Override
	public Token getToken() {
		return changeMode;
	}

	@Override
	public Type getType() {
		// TODO Auto-generated method stub
		return null;
	}

}
