package ch.fhnw.cpib.iml.parser.concsyntaxtree;

import ch.fhnw.cpib.iml.parser.abssyntaxtree.CmdList;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IBlockCmd;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.ICmd;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IRepCmd;
import ch.fhnw.cpib.iml.scanner.Token;

public class BlockCmd implements IBlockCmd {

  private final Token<?> lbrace;
  private final ICmd              cmd;
  private final IRepCmd           repCmd;
  private final Token<?> rbrace;

  public BlockCmd(final Token<?> lbrace, final ICmd cmd,
      final IRepCmd repCmd, final Token<?> rbrace) {
    this.lbrace = lbrace;
    this.cmd = cmd;
    this.repCmd = repCmd;
    this.rbrace = rbrace;
  }

  @Override
  public void appendAsString(final StringBuilder sb, final String indent) {
    sb.append(indent);
    sb.append(this.lbrace.getTerminal());
    sb.append("\n");
    this.cmd.appendAsString(sb, indent + " ");
    this.repCmd.appendAsString(sb, indent + " ");
    sb.append(indent);
    sb.append(this.rbrace.getTerminal());
    sb.append("\n");
  }

  @Override
  public ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.ICmd toAbsSyn() {
    return new CmdList(this.cmd.toAbsSyn(), this.repCmd.toAbsSyn());
  }

}
