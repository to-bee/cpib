package ch.fhnw.cpib.iml.parser.concsyntaxtree;

import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IExpr;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.MonadicExpr;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IFactor;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IMonadicOpr;

public class FactorMonadicOpr implements IFactor {

  private final IMonadicOpr monadicOpr;
  private final IFactor     factor;

  public FactorMonadicOpr(final IMonadicOpr monadicOpr, final IFactor factor) {
    this.monadicOpr = monadicOpr;
    this.factor = factor;
  }

  @Override
  public void appendAsString(final StringBuilder sb, final String indent) {
    this.monadicOpr.appendAsString(sb, indent + " ");
    this.factor.appendAsString(sb, indent + " ");
  }

  @Override
  public IExpr toAbsSyn() {
    return new MonadicExpr(this.monadicOpr.toAbsSyn(), this.factor.toAbsSyn());
  }
}
