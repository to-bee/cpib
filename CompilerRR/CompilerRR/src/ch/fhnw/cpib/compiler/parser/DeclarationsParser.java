package ch.fhnw.cpib.compiler.parser;

import ch.fhnw.cpib.compiler.cst.classes.Declarations;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public class DeclarationsParser extends AbstractParser {

	public DeclarationsParser() {
		super();
	}

	public IConcSyn.IDeclarations parse() throws GrammarError {
		if (terminal == Terminals.PROC) {
			IConcSyn.IDeclaration decl = new DeclarationParser().parse();
			IConcSyn.IRepeatingOptionalDeclarations repOptDecl = new RepeatingOptionalDeclarationsParser().parse();
			return new Declarations(decl, repOptDecl);
		}
		else if (terminal == Terminals.FUN) {
			IConcSyn.IDeclaration decl = new DeclarationParser().parse();
			IConcSyn.IRepeatingOptionalDeclarations repOptDecl = new RepeatingOptionalDeclarationsParser().parse();
			return new Declarations(decl, repOptDecl);
		}
		else if (terminal == Terminals.IDENT) {
			IConcSyn.IDeclaration decl = new DeclarationParser().parse();
			IConcSyn.IRepeatingOptionalDeclarations repOptDecl = new RepeatingOptionalDeclarationsParser().parse();
			return new Declarations(decl, repOptDecl);
		}
		else if (terminal == Terminals.CHANGEMODE) {
			IConcSyn.IDeclaration decl = new DeclarationParser().parse();
			IConcSyn.IRepeatingOptionalDeclarations repOptDecl = new RepeatingOptionalDeclarationsParser().parse();
			return new Declarations(decl, repOptDecl);
		}
		else {
			System.out.println(tokenlist.toString());
			throw new GrammarError("GrammarError at: "+ this.getClass().toString() + " terminal found: " + terminal, 0);
		}
	}

}
