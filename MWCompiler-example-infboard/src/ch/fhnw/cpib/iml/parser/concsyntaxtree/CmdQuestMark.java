package ch.fhnw.cpib.iml.parser.concsyntaxtree;

import ch.fhnw.cpib.iml.parser.abssyntaxtree.InputCmd;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.ICmd;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IExpr;
import ch.fhnw.cpib.iml.scanner.Token;

public class CmdQuestMark implements ICmd {

  private final Token<?> questMark;
  private final IExpr                expr;

  public CmdQuestMark(final Token<?> questMark, final IExpr expr) {
    this.questMark = questMark;
    this.expr = expr;
  }

  @Override
  public void appendAsString(final StringBuilder sb, final String indent) {
    sb.append(indent);
    sb.append(this.questMark.getTerminal());
    sb.append("\n");
    this.expr.appendAsString(sb, indent + " ");
  }

  @Override
  public ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.ICmd toAbsSyn() {
    return new InputCmd(this.expr.toAbsSyn());
  }

}
