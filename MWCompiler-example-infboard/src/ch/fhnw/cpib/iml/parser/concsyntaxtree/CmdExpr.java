package ch.fhnw.cpib.iml.parser.concsyntaxtree;

import ch.fhnw.cpib.iml.parser.abssyntaxtree.AssiCmd;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.ICmd;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IExpr;
import ch.fhnw.cpib.iml.scanner.Token;

public class CmdExpr implements ICmd {

  private final IExpr                expr1;
  private final Token<?> becomes;
  private final IExpr                expr2;

  public CmdExpr(final IExpr expr1, final Token<?> becomes,
      final IExpr expr2) {
    this.expr1 = expr1;
    this.becomes = becomes;
    this.expr2 = expr2;
  }

  @Override
  public void appendAsString(final StringBuilder sb, final String indent) {
    this.expr1.appendAsString(sb, indent + " ");
    sb.append(indent);
    sb.append(this.becomes.getTerminal());
    sb.append("\n");
    sb.append(indent);
    this.expr2.appendAsString(sb, indent + " ");
  }

  @Override
  public ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.ICmd toAbsSyn() {
    return new AssiCmd(this.expr1.toAbsSyn(), this.expr2.toAbsSyn());
  }

}
