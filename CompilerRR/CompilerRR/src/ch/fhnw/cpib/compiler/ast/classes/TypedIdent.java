package ch.fhnw.cpib.compiler.ast.classes;

import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.ITypedIdent;
import ch.fhnw.cpib.compiler.context.CompilerE;
import ch.fhnw.cpib.compiler.context.Variable;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.operators.Type;
import ch.fhnw.cpib.compiler.vm.ICodeArray.CodeTooSmallError;
import ch.fhnw.cpib.compiler.vm.IInstructions;

public class TypedIdent implements ITypedIdent{

	//name
	Token ident;
	
	//Ident or Type
	ITypeDeclaration typeDeclaration;
	
	public TypedIdent(Token ident, ITypeDeclaration typeDeclaration) {
		super();
		this.ident = ident;
		this.typeDeclaration = typeDeclaration;
	}

	@Override
	public void check() {
		//TODO: Check if name is valid.
		typeDeclaration.check();
	}

	@Override
	public Token getToken() {
		return ident;
	}

	@Override
	public Type getType() {
		return typeDeclaration.getType();
	}

	@Override
	public int code(int i) throws CodeTooSmallError {
	    int loc = i;
	    final Variable var = CompilerE.COMPILER.getCurrentContext().getVariable(this.ident);

	    // Load address of Variable onto the stack
	    CompilerE.COMPILER.getCodeArray().put(loc++, new IInstructions.LoadAddrRel(var.getRelLocation()));
	    CompilerE.COMPILER.getCodeArray().put(loc++, new IInstructions.Store());

	    System.out.println("[ "+this.getClass().getSimpleName()+" ]");
	    for(int ii = i; ii < loc; ii++){
	    	if(CompilerE.COMPILER.getCodeArray().get(ii) != null)
	    		System.out.println(CompilerE.COMPILER.getCodeArray().get(ii).toString());
	    	else System.out.println("null <--------------------------");
	    }
	    return loc;
	}
	
	

}
