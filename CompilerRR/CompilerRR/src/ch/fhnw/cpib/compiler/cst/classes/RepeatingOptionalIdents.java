package ch.fhnw.cpib.compiler.cst.classes;

import java.util.LinkedList;
import java.util.List;

import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn.IIdents;
import ch.fhnw.cpib.compiler.scanner.Token;

public class RepeatingOptionalIdents implements IConcSyn.IRepeatingOptionalIdents{

	Token commaToken;
	Token ident;
	IConcSyn.IIdents idents;
	public RepeatingOptionalIdents(Token commaToken, Token ident, IIdents idents) {
		super();
		this.commaToken = commaToken;
		this.ident = ident;
		this.idents = idents;
	}
	
	@Override
	public List<Token> toAbs() {
		List<Token> tokens = new LinkedList<Token>();
		tokens.add(ident);
		tokens.addAll(idents.toAbs());
		return tokens;
	}
	
	
	
}
