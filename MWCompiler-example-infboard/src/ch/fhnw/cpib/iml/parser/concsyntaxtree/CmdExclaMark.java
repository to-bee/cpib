package ch.fhnw.cpib.iml.parser.concsyntaxtree;

import ch.fhnw.cpib.iml.parser.abssyntaxtree.OutputCmd;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.ICmd;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IExpr;
import ch.fhnw.cpib.iml.scanner.Token;

public class CmdExclaMark implements ICmd {

  private final Token<?> exclaMark;
  private final IExpr                expr;

  public CmdExclaMark(final Token<?> exclaMark, final IExpr expr) {
    this.exclaMark = exclaMark;
    this.expr = expr;
  }

  @Override
  public void appendAsString(final StringBuilder sb, final String indent) {
    sb.append(indent);
    sb.append(this.exclaMark.getTerminal());
    sb.append("\n");
    this.expr.appendAsString(sb, indent + " ");
  }

  @Override
  public ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.ICmd toAbsSyn() {
    return new OutputCmd(this.expr.toAbsSyn());
  }

}
