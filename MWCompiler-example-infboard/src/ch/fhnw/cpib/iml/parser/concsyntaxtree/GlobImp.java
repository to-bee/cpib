package ch.fhnw.cpib.iml.parser.concsyntaxtree;

import ch.fhnw.cpib.iml.parser.abssyntaxtree.GlobalImport;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IGlobalImport;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IGlobImp;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IOptChangeMode;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IOptFlowMode;
import ch.fhnw.cpib.iml.scanner.Token;

public class GlobImp implements IGlobImp {

  private final IOptFlowMode              optFlowMode;
  private final IOptChangeMode            optChangeMode;
  private final Token<String> ident;

  public GlobImp(final IOptFlowMode optFlowMode,
      final IOptChangeMode optChangeMode, final Token<String> ident) {
    this.optFlowMode = optFlowMode;
    this.optChangeMode = optChangeMode;
    this.ident = ident;
  }

  @Override
  public void appendAsString(final StringBuilder sb, final String indent) {
    this.optFlowMode.appendAsString(sb, indent + " ");
    this.optChangeMode.appendAsString(sb, indent + " ");
    sb.append(indent);
    sb.append(this.ident.getAttribute());
    sb.append("\n");
  }

  @Override
  public IGlobalImport toAbsSyn() {
    return new GlobalImport(this.optFlowMode.toAbsSyn(), this.optChangeMode
        .toAbsSyn(), this.ident);
  }

}
