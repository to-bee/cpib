package ch.fhnw.cpib.parser;

import ch.fhnw.cpib.compiler.classes.RepeatingOptionalDeclarations;
import ch.fhnw.cpib.compiler.classes.RepeatingOptionalDeclarationsEps;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public class RepeatingOptionalDeclarationsParser extends AbstractParser {

	public RepeatingOptionalDeclarationsParser() {
		super();
	}
	
	public IConcSyn.IRepeatingOptionalDeclarations parse() throws GrammarError {
		if (terminal == Terminals.DO) {
			return new RepeatingOptionalDeclarationsEps();
		} 
		else if(terminal == Terminals.SEMICOLON){
			Token semicolonToken = consume(Terminals.SEMICOLON);
			IConcSyn.IDeclaration decl = new DeclarationParser().parse();
			IConcSyn.IRepeatingOptionalDeclarations repOptStoDecl = new RepeatingOptionalDeclarationsParser().parse();
			return new RepeatingOptionalDeclarations(semicolonToken, decl, repOptStoDecl);
		}
		else {
			throw new GrammarError("GrammarError at: "+ this.getClass().toString(), 0);
		}
	}
	
}
