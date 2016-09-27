package ch.fhnw.cpib.iml.parser.concsyntaxtree;

import ch.fhnw.cpib.iml.parser.abssyntaxtree.SkipCmd;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.ICmd;
import ch.fhnw.cpib.iml.scanner.Token;

public class CmdSkip implements ICmd {

  private final Token<?> skip;

  public CmdSkip(final Token<?> skip) {
    this.skip = skip;
  }

  @Override
  public void appendAsString(final StringBuilder sb, final String indent) {
    sb.append(indent);
    sb.append(this.skip.getTerminal());
    sb.append("\n");
  }

  @Override
  public ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.ICmd toAbsSyn() {
    return new SkipCmd(this.skip);
  }

}
