package ch.fhnw.cpib.iml.parser.concsyntaxtree;

import ch.fhnw.cpib.iml.parser.abssyntaxtree.ProcCallCmd;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.RoutineCall;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.ICmd;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IExprList;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IOptGlobInitList;
import ch.fhnw.cpib.iml.scanner.Token;

public class CmdCall implements ICmd {

  private final Token<?>      call;
  private final Token<String> ident;
  private final IExprList                 exprList;
  private final IOptGlobInitList          optGlobInitList;

  public CmdCall(final Token<?> call,
      final Token<String> ident, final IExprList exprList,
      final IOptGlobInitList optGlobInitList) {
    this.call = call;
    this.ident = ident;
    this.exprList = exprList;
    this.optGlobInitList = optGlobInitList;
  }

  @Override
  public void appendAsString(final StringBuilder sb, final String indent) {
    sb.append(indent);
    sb.append(this.call.getTerminal());
    sb.append("\n");
    sb.append(indent);
    sb.append(this.ident.getAttribute());
    sb.append("\n");
    this.exprList.appendAsString(sb, indent + " ");
    this.optGlobInitList.appendAsString(sb, indent + " ");
  }

  @Override
  public ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.ICmd toAbsSyn() {
    return new ProcCallCmd(
        new RoutineCall(this.ident, this.exprList.toAbsSyn()),
        this.optGlobInitList.toAbsSyn());
  }

}
