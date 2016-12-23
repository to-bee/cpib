package ch.fhnw.cpib.parser;

import ch.fhnw.cpib.compiler.cst.classes.CmdBecomes;
import ch.fhnw.cpib.compiler.cst.classes.CmdCall;
import ch.fhnw.cpib.compiler.cst.classes.CmdDebugIn;
import ch.fhnw.cpib.compiler.cst.classes.CmdDebugOut;
import ch.fhnw.cpib.compiler.cst.classes.CmdIf;
import ch.fhnw.cpib.compiler.cst.classes.CmdSkip;
import ch.fhnw.cpib.compiler.cst.classes.CmdSwitch;
import ch.fhnw.cpib.compiler.cst.classes.CmdWhile;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public class CmdParser extends AbstractParser {

	public CmdParser() {
		super();
	}

	public IConcSyn.ICmd parse() throws GrammarError {
		if (terminal == Terminals.SKIP) {
			return new CmdSkip(consume(Terminals.SKIP));
		} else if (terminal == Terminals.LPAREN) {
			IConcSyn.IExpression expr = new ExpressionParser().parse();
			Token becomes = consume(Terminals.BECOMES);
			IConcSyn.IExpression expr2 = new ExpressionParser().parse();
			return new CmdBecomes(expr, becomes, expr2);
		} else if (terminal == Terminals.ADDOPR) {
			IConcSyn.IExpression expr = new ExpressionParser().parse();
			Token becomes = consume(Terminals.BECOMES);
			IConcSyn.IExpression expr2 = new ExpressionParser().parse();
			return new CmdBecomes(expr, becomes, expr2);
		} else if (terminal == Terminals.NOTOPER) {
			IConcSyn.IExpression expr = new ExpressionParser().parse();
			Token becomes = consume(Terminals.BECOMES);
			IConcSyn.IExpression expr2 = new ExpressionParser().parse();
			return new CmdBecomes(expr, becomes, expr2);
		} else if (terminal == Terminals.IDENT) {
			IConcSyn.IExpression expr = new ExpressionParser().parse();
			Token becomes = consume(Terminals.BECOMES);
			IConcSyn.IExpression expr2 = new ExpressionParser().parse();
			return new CmdBecomes(expr, becomes, expr2);
		} else if (terminal == Terminals.LITERAL) {
			IConcSyn.IExpression expr = new ExpressionParser().parse();
			Token becomes = consume(Terminals.BECOMES);
			IConcSyn.IExpression expr2 = new ExpressionParser().parse();
			return new CmdBecomes(expr, becomes, expr2);
		} else if (terminal == Terminals.IF) {
			Token ifToken = consume(Terminals.IF);
			IConcSyn.IExpression ifExpr =  new ExpressionParser().parse();
			Token thenToken = consume(Terminals.THEN);
			IConcSyn.IBlockCmd thenBlock = new BlockCmdParser().parse();
			Token elseToken = consume(Terminals.ELSE);
			IConcSyn.IBlockCmd elseBlock = new BlockCmdParser().parse();
			Token endToken = consume(Terminals.ENDIF);
			return new CmdIf(ifToken, ifExpr, thenToken, thenBlock, elseToken, elseBlock, endToken);
		} else if (terminal == Terminals.WHILE) {
			Token whileToken = consume(Terminals.WHILE);
			IConcSyn.IExpression expr = new ExpressionParser().parse();
			Token doToken = consume(Terminals.DO);
			IConcSyn.IBlockCmd blockCmd= new BlockCmdParser().parse();
			Token endToken = consume(Terminals.ENDWHILE);
			return new CmdWhile(whileToken, expr, doToken, blockCmd, endToken);
		} else if (terminal == Terminals.CALL) {
			Token callToken = consume(Terminals.CALL);
			Token identToken = consume(Terminals.IDENT);
			IConcSyn.IExpressionList expr = new ExpressionListParser().parse();
			IConcSyn.IOptionalGlobalInits optGloInits = new OptionalGlobalInitsParser().parse();
			return new CmdCall(callToken, identToken, expr, optGloInits);
		} else if (terminal == Terminals.DEBUGIN) {
			Token debugin = consume(Terminals.DEBUGIN);
			IConcSyn.IExpression expr = new ExpressionParser().parse();
			return new CmdDebugIn(debugin, expr);
		} else if (terminal == Terminals.DEBUGOUT) {
			Token debugOut = consume(Terminals.DEBUGOUT);
			IConcSyn.IExpression expr = new ExpressionParser().parse();
			return new CmdDebugOut(debugOut, expr);
		} else if (terminal == Terminals.SWITCH) {
			Token switchToken = consume(Terminals.SWITCH);
			IConcSyn.IExpression expr = new ExpressionParser().parse();
			Token caseToken = consume(Terminals.CASE);
			Token literalToken = consume(Terminals.LITERAL);
			Token colonToken = consume(Terminals.COLON);
			IConcSyn.IBlockCmd blockCmd = new BlockCmdParser().parse();
			IConcSyn.IRepeatingOptionalCase repOptCase = new RepeatingOptionalCaseParser().parse();
			Token caseDef = consume(Terminals.CASEDEFAULT);
			Token colonSec = consume(Terminals.COLON);
			IConcSyn.IBlockCmd blockCmdSec = new BlockCmdParser().parse();
			Token endSwitch = consume(Terminals.ENDSWITCH);
			return new CmdSwitch(switchToken, expr, caseToken, literalToken, colonToken, blockCmd, repOptCase, caseDef, colonSec, blockCmdSec, endSwitch);
			
		} else {
			System.out.println(tokenlist.toString());
			throw new GrammarError("GrammarError at: "+ this.getClass().toString() + " terminal found: " + terminal, 0);
		}
	}

}
