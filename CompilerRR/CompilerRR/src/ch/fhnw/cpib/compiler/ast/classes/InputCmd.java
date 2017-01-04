package ch.fhnw.cpib.compiler.ast.classes;


import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.ICommand;
import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IExpression;
import ch.fhnw.cpib.compiler.context.CompilerE;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.tokens.LiteralToken;
import ch.fhnw.cpib.compiler.vm.ICodeArray;
import ch.fhnw.cpib.compiler.vm.ICodeArray.CodeTooSmallError;
import ch.fhnw.cpib.compiler.vm.IInstructions.*;

public class InputCmd implements ICommand{
	IExpression expression;

	public InputCmd(IExpression expression) {
		super();
		this.expression = expression;
	}

	@Override
	public void check() {
		expression.check();
		
		if (!(this.expression instanceof StoreExpression))
		      throw new RuntimeException("Cannot store input into given Expression " +
		          this.expression.getToken().toString());

		((StoreExpression) this.expression).setWrite(true);
		
	}

	@Override
	public Token getToken() {
		return expression.getToken();
	}

	@Override
	public int code(int i) throws CodeTooSmallError {
		int loc = i;
	    ICodeArray codeArr = CompilerE.COMPILER.getCodeArray();
	    //vm.DebugInfo(loc++, this.getClass().getSimpleName(), this.getToken());


	    Token token = this.expression.getToken();
	    switch (this.expression.getType()) {
	    case BOOL:
	      loc = this.expression.code(loc);
	      codeArr.put(loc++, new InputBool( String.valueOf( ((LiteralToken)token).getValue() ) ) );
	      break;
	    default:
	      loc = this.expression.code(loc);
	      codeArr.put(loc++, new InputInt( String.valueOf( ((LiteralToken)token).getValue() ) ) );
	      break;
	    }

	    return loc;
	}
	
	
}
