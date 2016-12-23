package ch.fhnw.cpib.parser;

import ch.fhnw.cpib.compiler.cst.classes.Idents;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public class IdentsParser extends AbstractParser {

	public IdentsParser() {
		super();
	}
	
	public IConcSyn.IIdents parse() throws GrammarError {
		if(terminal == Terminals.IDENT){
			Token ident = consume(Terminals.IDENT);
			IConcSyn.IRepeatingOptionalIdents  repOptId = new RepeatingOptionalIdentsParser().parse();
			return new Idents(ident, repOptId);
		}
		else {
			System.out.println(tokenlist.toString());
			throw new GrammarError("GrammarError at: "+ this.getClass().toString() + " terminal found: " + terminal, 0);
		}
	}
	
}
