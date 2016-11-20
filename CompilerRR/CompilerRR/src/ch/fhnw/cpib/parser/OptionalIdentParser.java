package ch.fhnw.cpib.parser;

import java.util.LinkedList;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public class OptionalIdentParser extends AbstractParser {

	public OptionalIdentParser() {
		super();
	}

	@Override
	public void parse() throws GrammarError {
		if (terminal == Terminals.RPAREN) {
			// TODO: leer?
		}
		else if (terminal == Terminals.COMMA) {
			// TODO: leer?
		}
		else if (terminal == Terminals.DO) {
			// TODO: leer?
		}
		else if (terminal == Terminals.THEN) {
			// TODO: leer?
		}
		else if (terminal == Terminals.ENDPROC) {
			// TODO: leer?
		}
		else if (terminal == Terminals.ENDFUN) {
			// TODO: leer?
		}
		else if (terminal == Terminals.ENDSWITCH) {
			// TODO: leer?
		}
		else if (terminal == Terminals.CASEDEFAULT) {
			// TODO: leer?
		}
		else if (terminal == Terminals.CASE) {
			// TODO: leer?
		}
		else if (terminal == Terminals.ENDWHILE) {
			// TODO: leer?
		}
		else if (terminal == Terminals.ENDIF) {
			// TODO: leer?
		}
		else if (terminal == Terminals.ELSE) {
			// TODO: leer?
		}
		else if (terminal == Terminals.ENDPROGRAM) {
			// TODO: leer?
		}
		else if (terminal == Terminals.SEMICOLON) {
			// TODO: leer?
		}
		else if (terminal == Terminals.BECOMES) {
			// TODO: leer?
		}
		else if (terminal == Terminals.BOOLOPR) {
			// TODO: leer?
		}
		else if (terminal == Terminals.RELOPR) {
			// TODO: leer?
		}
		else if (terminal == Terminals.ADDOPR) {
			// TODO: leer?
		}
		else if (terminal == Terminals.MULTOPR) {
			// TODO: leer?
		}
		else if (terminal == Terminals.INIT) {
			consume(Terminals.INIT);
		}
		else if (terminal == Terminals.LPAREN) {
			new ExpressionListParser().parse();
		}
		else {
			throw new GrammarError("GrammarError at: "+ this.getClass().toString(), 0);
		}
		
	}

}
