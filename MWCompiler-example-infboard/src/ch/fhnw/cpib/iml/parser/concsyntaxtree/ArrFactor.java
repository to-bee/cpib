package ch.fhnw.cpib.iml.parser.concsyntaxtree;

import ch.fhnw.cpib.iml.parser.abssyntaxtree.ArrExpr;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IArrFactor;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IExpr;
import ch.fhnw.cpib.iml.scanner.Token;

public class ArrFactor implements IArrFactor {

  private final Token<?> lbracket;
  private final IExpr             expr;
  private final Token<?> rbracket;

  public ArrFactor(final Token<?> lbracket, final IExpr expr,
      final Token<?> rbracket) {
    this.lbracket = lbracket;
    this.expr = expr;
    this.rbracket = rbracket;
  }

  @Override
  public void appendAsString(final StringBuilder sb, final String indent) {
    sb.append(indent);
    sb.append(this.lbracket.getTerminal());
    sb.append("\n");
    this.expr.appendAsString(sb, indent + " ");
    sb.append(indent);
    sb.append(this.rbracket.getTerminal());
    sb.append("\n");
  }

  @Override
  public ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IExpr toAbsSyn() {
    return new ArrExpr(this.expr.toAbsSyn());
  }

}
