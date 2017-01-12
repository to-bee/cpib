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
		System.out.println(this.getClass().getSimpleName());
		
	    int loc = i;
	    CompilerE.COMPILER.getCodeArray().put(loc++, new IInstructions.AllocBlock(1));

	    System.out.println("[ "+this.getClass().getSimpleName()+" ]");
	    for(int ii = i; ii < loc; ii++){
	    	if(CompilerE.COMPILER.getCodeArray().get(ii) != null)
	    		System.out.println(CompilerE.COMPILER.getCodeArray().get(ii).toString());
	    	else System.out.println("null <--------------------------");
	    }
	    return loc;
	}

}
