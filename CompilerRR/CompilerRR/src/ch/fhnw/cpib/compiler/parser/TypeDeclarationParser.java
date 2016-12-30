package ch.fhnw.cpib.compiler.parser;

import ch.fhnw.cpib.compiler.cst.classes.TypeDeclarationIdent;
import ch.fhnw.cpib.compiler.cst.classes.TypeDeclarationType;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public class TypeDeclarationParser extends AbstractParser {

	public TypeDeclarationParser() {
		super();
	}

	public IConcSyn.ITypeDeclaration parse() throws GrammarError {
		if (terminal == Terminals.TYPE) {
			Token type = consume(Terminals.TYPE);
			return new TypeDeclarationType(type);
		} 
		else if(terminal == Terminals.IDENT){
			Token ident = consume(Terminals.IDENT);
			return new TypeDeclarationIdent(ident);
		}
		else {
			throw new GrammarError("GrammarError at: "+ this.getClass().toString(), 0);
		}
	}

}
