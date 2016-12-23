package ch.fhnw.cpib.compiler.cst.classes;

import java.util.LinkedList;
import java.util.List;

import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.scanner.Token;

public class Idents implements IConcSyn.IIdents{

	Token identToken;
	IConcSyn.IRepeatingOptionalIdents repId;
	public Idents(Token identToken, IRepeatingOptionalIdents repId) {
		super();
		this.identToken = identToken;
		this.repId = repId;
	}
	@Override
	public List<Token> toAbs() {
		List<Token> tokens = new LinkedList<Token>();
		tokens.add(identToken);
		tokens.addAll(repId.toAbs());
		return tokens;
	}
	
	
}
