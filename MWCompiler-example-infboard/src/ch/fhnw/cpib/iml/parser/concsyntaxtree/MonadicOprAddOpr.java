package ch.fhnw.cpib.iml.parser.concsyntaxtree;

import ch.fhnw.cpib.iml.lib.enums.AddOpr;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IMonadicOpr;
import ch.fhnw.cpib.iml.scanner.Token;

public class MonadicOprAddOpr implements IMonadicOpr {

  private final Token<AddOpr> addOpr;

  public MonadicOprAddOpr(final Token<AddOpr> addOpr) {
    this.addOpr = addOpr;
  }

  @Override
  public void appendAsString(final StringBuilder sb, final String indent) {
    sb.append(indent);
    sb.append(this.addOpr.getAttribute());
    sb.append("\n");
  }

  @Override
  public Token<AddOpr> toAbsSyn() {
    return this.addOpr;
  }

}
