package ch.fhnw.cpib.iml.parser.concsyntaxtree;

import ch.fhnw.cpib.iml.parser.abssyntaxtree.WhileCmd;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IBlockCmd;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.ICmd;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IExpr;
import ch.fhnw.cpib.iml.scanner.Token;

public class CmdWhile implements ICmd {

  private final Token<?> whileToken;
  private final Token<?> lparen;
  private final IExpr                expr;
  private final Token<?> rparen;
  private final IBlockCmd            blockCmd;

  public CmdWhile(final Token<?> whileToken,
      final Token<?> lparen, final IExpr expr,
      final Token<?> rparen, final IBlockCmd blockCmd) {
    this.whileToken = whileToken;
    this.lparen = lparen;
    this.expr = expr;
    this.rparen = rparen;
    this.blockCmd = blockCmd;
  }

  @Override
  public void appendAsString(final StringBuilder sb, final String indent) {
    sb.append(indent);
    sb.append(this.whileToken.getTerminal());
    sb.append("\n");
    sb.append(indent);
    sb.append(this.lparen.getTerminal());
    sb.append("\n");
    this.expr.appendAsString(sb, indent + " ");
    sb.append(indent);
    sb.append(this.rparen.getTerminal());
    sb.append("\n");
    this.blockCmd.appendAsString(sb, indent + " ");
  }

  @Override
  public ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.ICmd toAbsSyn() {
    return new WhileCmd(this.expr.toAbsSyn(), this.blockCmd.toAbsSyn());
  }

}
