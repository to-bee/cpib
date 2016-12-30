package ch.fhnw.cpib.compiler.parser;

import ch.fhnw.cpib.compiler.cst.classes.OptionalIdentEps;
import ch.fhnw.cpib.compiler.cst.classes.OptionalIdentExprList;
import ch.fhnw.cpib.compiler.cst.classes.OptionalIdentInit;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public class OptionalIdentParser extends AbstractParser {

	public OptionalIdentParser() {
		super();
	}

	public IConcSyn.IOptionalIdent parse() throws GrammarError {
		if (terminal == Terminals.RPAREN) {
			return new OptionalIdentEps();
		}
		else if (terminal == Terminals.COMMA) {
			return new OptionalIdentEps();
		}
		else if (terminal == Terminals.DO) {
			return new OptionalIdentEps();
		}
		else if (terminal == Terminals.THEN) {
			return new OptionalIdentEps();
		}
		else if (terminal == Terminals.ENDPROC) {
			return new OptionalIdentEps();
		}
		else if (terminal == Terminals.ENDFUN) {
			return new OptionalIdentEps();
		}
		else if (terminal == Terminals.ENDSWITCH) {
			return new OptionalIdentEps();
		}
		else if (terminal == Terminals.CASEDEFAULT) {
			return new OptionalIdentEps();
		}
		else if (terminal == Terminals.CASE) {
			return new OptionalIdentEps();
		}
		else if (terminal == Terminals.ENDWHILE) {
			return new OptionalIdentEps();
		}
		else if (terminal == Terminals.ENDIF) {
			return new OptionalIdentEps();
		}
		else if (terminal == Terminals.ELSE) {
			return new OptionalIdentEps();
		}
		else if (terminal == Terminals.ENDPROGRAM) {
			return new OptionalIdentEps();
		}
		else if (terminal == Terminals.SEMICOLON) {
			return new OptionalIdentEps();
		}
		else if (terminal == Terminals.BECOMES) {
			return new OptionalIdentEps();
		}
		else if (terminal == Terminals.BOOLOPR) {
			return new OptionalIdentEps();
		}
		else if (terminal == Terminals.RELOPR) {
			return new OptionalIdentEps();
		}
		else if (terminal == Terminals.ADDOPR) {
			return new OptionalIdentEps();
		}
		else if (terminal == Terminals.MULTOPR) {
			return new OptionalIdentEps();
		}
		else if (terminal == Terminals.INIT) {
			return new OptionalIdentInit(consume(Terminals.INIT));
		}
		else if (terminal == Terminals.LPAREN) {
			IConcSyn.IExpressionList expr = new ExpressionListParser().parse();
			return new OptionalIdentExprList(expr);
		}
		else {
			throw new GrammarError("GrammarError at: "+ this.getClass().toString(), 0);
		}
	}

}
