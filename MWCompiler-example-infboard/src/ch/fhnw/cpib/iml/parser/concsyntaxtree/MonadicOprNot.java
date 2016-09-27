package ch.fhnw.cpib.iml.parser.concsyntaxtree;

import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IMonadicOpr;
import ch.fhnw.cpib.iml.scanner.Token;

public class MonadicOprNot implements IMonadicOpr {

  private final Token<?> notOpr;

  public MonadicOprNot(final Token<?> notOpr) {
    this.notOpr = notOpr;
  }

  @Override
  public void appendAsString(final StringBuilder sb, final String indent) {
    sb.append(indent);
    sb.append(this.notOpr.getTerminal());
    sb.append("\n");
  }

  @Override
  public Token<?> toAbsSyn() {
    return this.notOpr;
  }

}
