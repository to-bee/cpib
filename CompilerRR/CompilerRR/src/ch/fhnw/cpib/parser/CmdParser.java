package ch.fhnw.cpib.parser;

import java.util.LinkedList;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public class CmdParser extends AbstractParser {

	public CmdParser() {
		super();
	}

	@Override
	public void parse() throws GrammarError {
		if (terminal == Terminals.SKIP) {
			consume(Terminals.SKIP);
		} else if (terminal == Terminals.LPAREN) {
			new ExpressionParser().parse();
			consume(Terminals.BECOMES);
			new ExpressionParser().parse();
		} else if (terminal == Terminals.ADDOPR) {
			new ExpressionParser().parse();
			consume(Terminals.BECOMES);
			new ExpressionParser().parse();
		} else if (terminal == Terminals.NOTOPER) { // FIXME: Acturally NOT in fixfox
			new ExpressionParser().parse();
			consume(Terminals.BECOMES);
			new ExpressionParser().parse();
		} else if (terminal == Terminals.IDENT) {
			new ExpressionParser().parse();
			consume(Terminals.BECOMES);
			new ExpressionParser().parse();
		} else if (terminal == Terminals.LITERAL) {
			new ExpressionParser().parse();
			consume(Terminals.BECOMES);
			new ExpressionParser().parse();
		} else if (terminal == Terminals.IF) {
			consume(Terminals.IF);
			new ExpressionParser().parse();
			consume(Terminals.THEN);
			new BlockCmdParser().parse();
			consume(Terminals.ELSE);
			new BlockCmdParser().parse();
			consume(Terminals.ENDIF);
		} else if (terminal == Terminals.WHILE) {
			consume(Terminals.WHILE);
			new ExpressionParser().parse();
			consume(Terminals.DO);
			new BlockCmdParser().parse();
			consume(Terminals.ENDWHILE);
		} else if (terminal == Terminals.CALL) {
			consume(Terminals.CALL);
			consume(Terminals.IDENT);
			new ExpressionListParser().parse();
			new OptionalGlobalInitsParser().parse();
		} else if (terminal == Terminals.DEBUGIN) {
			consume(Terminals.DEBUGIN);
			new ExpressionParser().parse();
		} else if (terminal == Terminals.DEBUGOUT) {
			consume(Terminals.DEBUGOUT);
			new ExpressionParser().parse();
		} else if (terminal == Terminals.SWITCH) {
			consume(Terminals.SWITCH);
			consume(Terminals.IDENT);
			consume(Terminals.CASE);
			consume(Terminals.LITERAL);
			consume(Terminals.COLON);
			new BlockCmdParser().parse();
			new RepeatingOptionalCaseParser().parse();
			consume(Terminals.CASEDEFAULT);
			consume(Terminals.COLON);
			new BlockCmdParser().parse();
			consume(Terminals.ENDSWITCH);
			
		} else {
			System.out.println(tokenlist.toString());
			throw new GrammarError("GrammarError at: "+ this.getClass().toString() + " terminal found: " + terminal, 0);
		}
		
	}

}
