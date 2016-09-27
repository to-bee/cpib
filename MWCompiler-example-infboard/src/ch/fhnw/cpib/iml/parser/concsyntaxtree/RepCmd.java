package ch.fhnw.cpib.iml.parser.concsyntaxtree;

import ch.fhnw.cpib.iml.parser.abssyntaxtree.CmdList;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IList;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.ICmd;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IRepCmd;
import ch.fhnw.cpib.iml.scanner.Token;

public class RepCmd implements IRepCmd {

  private final Token<?> semiColon;
  private final ICmd                 cmd;
  private final IRepCmd              repCmd;

  public RepCmd(final Token<?> semiColon, final ICmd cmd,
      final IRepCmd repCmd) {
    this.semiColon = semiColon;
    this.cmd = cmd;
    this.repCmd = repCmd;
  }

  @Override
  public void appendAsString(final StringBuilder sb, final String indent) {
    sb.append(indent);
    sb.append(this.semiColon.getTerminal());
    sb.append("\n");
    this.cmd.appendAsString(sb, indent + " ");
    this.repCmd.appendAsString(sb, indent + " ");
  }

  @Override
  public IList<ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.ICmd> toAbsSyn() {
    return new CmdList(this.cmd.toAbsSyn(), this.repCmd.toAbsSyn());
  }

}
