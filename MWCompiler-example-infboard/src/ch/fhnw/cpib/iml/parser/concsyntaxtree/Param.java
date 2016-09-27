package ch.fhnw.cpib.iml.parser.concsyntaxtree;

import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IParameter;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.Parameter;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IOptFlowMode;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IOptMechMode;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IParam;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IStoreDecl;

public class Param implements IParam {

  private final IOptFlowMode optFlowMode;
  private final IOptMechMode optMechMode;
  private final IStoreDecl   storeDecl;

  public Param(final IOptFlowMode optFlowMode, final IOptMechMode optMechMode,
      final IStoreDecl storeDecl) {
    this.optFlowMode = optFlowMode;
    this.optMechMode = optMechMode;
    this.storeDecl = storeDecl;
  }

  @Override
  public void appendAsString(final StringBuilder sb, final String indent) {
    final String newIndent = indent + " ";
    this.optFlowMode.appendAsString(sb, newIndent);
    this.optMechMode.appendAsString(sb, newIndent);
    this.storeDecl.appendAsString(sb, newIndent);
  }

  @Override
  public IParameter toAbsSyn() {
    return new Parameter(this.optFlowMode.toAbsSyn(), this.optMechMode
        .toAbsSyn(), this.storeDecl.toAbsSyn());
  }

}
