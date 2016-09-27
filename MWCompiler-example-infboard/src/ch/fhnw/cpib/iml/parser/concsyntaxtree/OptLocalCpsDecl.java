package ch.fhnw.cpib.iml.parser.concsyntaxtree;

import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IDecl;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IList;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.ICpsDecl;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IOptLocalCpsDecl;
import ch.fhnw.cpib.iml.scanner.Token;

public class OptLocalCpsDecl implements IOptLocalCpsDecl {

  private final Token<?> local;
  private final ICpsDecl          cpsDecl;

  public OptLocalCpsDecl(final Token<?> local,
      final ICpsDecl cpsDecl) {
    this.local = local;
    this.cpsDecl = cpsDecl;
  }

  @Override
  public void appendAsString(final StringBuilder sb, final String indent) {
    sb.append(indent);
    sb.append(this.local.getTerminal());
    sb.append("\n");
    this.cpsDecl.appendAsString(sb, indent + " ");
  }

  @Override
  public IList<IDecl> toAbsSyn() {
    return this.cpsDecl.toAbsSyn();
  }

}
