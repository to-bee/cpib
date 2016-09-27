package ch.fhnw.cpib.iml.parser.concsyntaxtree;

import ch.fhnw.cpib.iml.parser.abssyntaxtree.CondCmd;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IBlockCmd;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.ICmd;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IExpr;
import ch.fhnw.cpib.iml.scanner.Token;

public class CmdIf implements ICmd {

  private final Token<?> ifToken;
  private final Token<?> lparen;
  private final IExpr                expr;
  private final Token<?> rparen;
  private final IBlockCmd            blockCmd1;
  private final Token<?> elseToken;
  private final IBlockCmd            blockCmd2;

  public CmdIf(final Token<?> ifToken,
      final Token<?> lparen, final IExpr expr,
      final Token<?> rparen, final IBlockCmd blockCmd1,
      final Token<?> elseToken,
      final IBlockCmd blockCmd2) {
    this.ifToken = ifToken;
    this.lparen = lparen;
    this.expr = expr;
    this.rparen = rparen;
    this.blockCmd1 = blockCmd1;
    this.elseToken = elseToken;
    this.blockCmd2 = blockCmd2;
  }

  @Override
  public void appendAsString(final StringBuilder sb, final String indent) {
    sb.append(indent);
    sb.append(this.ifToken.getTerminal());
    sb.append(this.lparen.getTerminal());
    sb.append("\n");
    this.expr.appendAsString(sb, indent + " ");
    sb.append(indent);
    sb.append(this.rparen.getTerminal());
    sb.append("\n");
    this.blockCmd1.appendAsString(sb, indent + " ");
    sb.append(indent);
    sb.append(this.elseToken.getTerminal());
    sb.append("\n");
    this.blockCmd2.appendAsString(sb, indent + " ");
  }

  @Override
  public ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.ICmd toAbsSyn() {
    return new CondCmd(this.expr.toAbsSyn(), this.blockCmd1.toAbsSyn(),
        this.blockCmd2.toAbsSyn());
  }

}
