package ch.fhnw.cpib.iml.parser.concsyntaxtree;

import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IBlockCmd;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IOptGlobImpList;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IOptLocalCpsDecl;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IParamList;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IProcDecl;
import ch.fhnw.cpib.iml.scanner.Token;

public class ProcDecl implements IProcDecl {

  private final Token<?>      proc;
  private final Token<String> ident;
  private final IParamList                paramList;
  private final IOptGlobImpList           optGlobImpList;
  private final IOptLocalCpsDecl          optLocaCpsDecl;
  private final IBlockCmd                 blockCmd;

  public ProcDecl(final Token<?> proc,
      final Token<String> ident, final IParamList paramList,
      final IOptGlobImpList optGlobImpList,
      final IOptLocalCpsDecl optLocalCpsDecl, final IBlockCmd blockCmd) {
    this.proc = proc;
    this.ident = ident;
    this.paramList = paramList;
    this.optGlobImpList = optGlobImpList;
    this.optLocaCpsDecl = optLocalCpsDecl;
    this.blockCmd = blockCmd;
  }

  @Override
  public void appendAsString(final StringBuilder sb, final String indent) {
    final String newIndent = indent + " ";

    sb.append(indent);
    sb.append("proc\n");
    sb.append(indent);
    sb.append(this.ident.getAttribute());
    sb.append("\n");

    this.paramList.appendAsString(sb, newIndent);

    if (this.optGlobImpList != null)
      this.optGlobImpList.appendAsString(sb, newIndent);

    if (this.optLocaCpsDecl != null)
      this.optLocaCpsDecl.appendAsString(sb, newIndent);

    this.blockCmd.appendAsString(sb, newIndent);
  }

  @Override
  public ch.fhnw.cpib.iml.parser.abssyntaxtree.ProcDecl toAbsSyn() {
    return new ch.fhnw.cpib.iml.parser.abssyntaxtree.ProcDecl(this.ident,
        this.paramList.toAbsSyn(), this.optGlobImpList.toAbsSyn(),
        this.optLocaCpsDecl.toAbsSyn(), this.blockCmd.toAbsSyn());
  }

}
