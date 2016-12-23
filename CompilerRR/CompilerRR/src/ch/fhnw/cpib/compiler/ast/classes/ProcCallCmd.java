package ch.fhnw.cpib.compiler.ast.classes;

import java.util.List;

import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.ICommand;
import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IExpression;
import ch.fhnw.cpib.compiler.scanner.Token;

public class ProcCallCmd implements ICommand{
	Token ident;
	List<IExpression> expressions;
	List<Token> indents;
	public ProcCallCmd(Token ident, List<IExpression> expressions,
			List<Token> indents) {
		super();
		this.ident = ident;
		this.expressions = expressions;
		this.indents = indents;
	}
	
	
}
