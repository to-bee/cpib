package ch.fhnw.cpib.compiler.ast.classes;

import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IExpression;
import ch.fhnw.cpib.compiler.context.CompilerE;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.Operators;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;
import ch.fhnw.cpib.compiler.scanner.enums.operators.AddOpr;
import ch.fhnw.cpib.compiler.scanner.enums.operators.RelOpr;
import ch.fhnw.cpib.compiler.scanner.enums.operators.Type;
import ch.fhnw.cpib.compiler.vm.ICodeArray;
import ch.fhnw.cpib.compiler.vm.ICodeArray.CodeTooSmallError;
import ch.fhnw.cpib.compiler.vm.IInstructions.*;

public class DyadicExpression implements IExpression {

	private Token operator;
	private IExpression lExpr;
	private IExpression rExpr;
	private Type type = null;

	public DyadicExpression(Token operator, IExpression lExpr, IExpression rExpr) {
		super();
		this.operator = operator;
		this.lExpr = lExpr;
		this.rExpr = rExpr;
	}

	@Override
	public void check() {
		this.lExpr.check();

		final Operators op = this.operator.getOperator();
		if (op == Operators.EQ || op == Operators.LT || op == Operators.GT
				|| op == Operators.NE || op == Operators.GE
				|| op == Operators.LE)
			this.type = Type.BOOL;
		else
			this.type = this.lExpr.getType();

		this.rExpr.check();

		if (this.lExpr.getType() != this.lExpr.getType())
			throw new RuntimeException(
					"Two different types in dyadic expression.");
	}

	@Override
	public Token getToken() {
		return operator;
	}

	@Override
	public Type getType() {
		return this.type;
	}

	@Override
	public int code(int i) throws CodeTooSmallError {
		ICodeArray codeArr = CompilerE.COMPILER.getCodeArray();

		int loc1 = this.lExpr.code(i);

		Operators opr = this.operator.getOperator();

		if (opr == Operators.CAND || opr == Operators.COR) {
			switch (opr) {
			case CAND:
				int loc2 = loc1 + 1;
				int loc3 = rExpr.code(loc2);
				int loc4 = loc3 + 1;
				codeArr.put(loc3, new UncondJump(loc4 + 1));
				codeArr.put(loc1, new CondJump(loc4));
				return loc4 + 1;
			case COR:
				int loc5 = loc1 + 1;
				int loc6 = loc5 + 2;
				int loc7 = rExpr.code(loc6);
				int loc8 = loc7 + 1;
				int loc9 = loc8 + 1;
				codeArr.put(loc1, new AddInt()); // 0 = FALSE / 1,2 = TRUE
				return loc1 + 1;
			default:
				throw new RuntimeException("Unknown Operator: " + opr);
			}
		}

		int loc2 = this.rExpr.code(loc1);

		// ADDOPR
		if (opr == Operators.PLUS || opr == Operators.MINUS) {
			switch (opr) {
			case PLUS:
				codeArr.put(loc2, new AddInt());
				return loc2 + 1;
			case MINUS:
				codeArr.put(loc2, new SubInt());
				return loc2 + 1;
			default:
				throw new RuntimeException("Unknown Operator: " + opr);
			}
			// MULTOPR
		} else if (opr == Operators.TIMES || opr == Operators.DIV_E
				|| opr == Operators.MOD_E) {
			switch (opr) {
			case DIV_E:
				codeArr.put(loc2, new DivTruncInt());
				return loc2 + 1;
			case MOD_E:
				codeArr.put(loc2, new ModTruncInt());
				return loc2 + 1;
			case TIMES:
				codeArr.put(loc2, new MultInt());
				return loc2 + 1;
			default:
				throw new RuntimeException("Unknown Operator: " + opr);
			}
			// RELOPR
		} else if (opr == Operators.EQ || opr == Operators.LT
				|| opr == Operators.GT || opr == Operators.NE
				|| opr == Operators.GE || opr == Operators.LE) {
			switch (opr) {
			case EQ:
				codeArr.put(loc2, new EqInt());
				return loc2 + 1;
			case GE:
				codeArr.put(loc2, new GeInt());
				return loc2 + 1;
			case GT:
				codeArr.put(loc2, new GtInt());
				return loc2 + 1;
			case LE:
				codeArr.put(loc2, new LeInt());
				return loc2 + 1;
			case LT:
				codeArr.put(loc2, new LtInt());
				return loc2 + 1;
			case NE:
				codeArr.put(loc2, new NeInt());
				return loc2 + 1;
			default:
				throw new RuntimeException("Unknown Operator: " + opr);
			}
		}
		throw new RuntimeException("Unknown Operator: " + opr);
	}

	@Override
	public void print(String prefix) {
		operator.print(prefix);
		lExpr.print(prefix);
		rExpr.print(prefix);
	}
}