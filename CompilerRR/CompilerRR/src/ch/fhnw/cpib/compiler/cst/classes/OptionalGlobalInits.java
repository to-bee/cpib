package ch.fhnw.cpib.compiler.cst.classes;

import java.util.LinkedList;
import java.util.List;

import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.scanner.Token;

public class OptionalGlobalInits implements IConcSyn.IOptionalGlobalInits{

	Token initToken;
	IConcSyn.IIdents identsL;
	public OptionalGlobalInits(Token initToken, IIdents identsL) {
		super();
		this.initToken = initToken;
		this.identsL = identsL;
	}
	@Override
	public List<Token> toAbs() {
		List<Token> tokens = new LinkedList<Token>();
		tokens.add(initToken);
		tokens.addAll(identsL.toAbs());
		return tokens;
	}
	
	
}
