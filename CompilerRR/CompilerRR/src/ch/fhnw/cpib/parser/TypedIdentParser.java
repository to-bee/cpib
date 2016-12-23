package ch.fhnw.cpib.parser;

import ch.fhnw.cpib.compiler.cst.classes.TypedIdent;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public class TypedIdentParser extends AbstractParser {

	public TypedIdentParser() {
		super();
	}

	public IConcSyn.ITypedIdent parse() throws GrammarError {
		if (terminal == Terminals.IDENT) {
			Token ident = consume(Terminals.IDENT);
			Token colon = consume(Terminals.COLON);
			IConcSyn.ITypeDeclaration typedecl = new TypeDeclarationParser().parse();
			return new TypedIdent(ident, colon, typedecl);
		} 
		else {
			throw new GrammarError("GrammarError at: "+ this.getClass().toString(), 0);
		}
	}

}
