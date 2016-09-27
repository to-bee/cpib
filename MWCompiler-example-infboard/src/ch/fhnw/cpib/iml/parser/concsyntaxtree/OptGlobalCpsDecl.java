package ch.fhnw.cpib.iml.parser.concsyntaxtree;

import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IDecl;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IList;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IOptGlobalCpsDecl;
import ch.fhnw.cpib.iml.scanner.Token;

public class OptGlobalCpsDecl implements IOptGlobalCpsDecl {
  private final Token<?> global;
  private final IConcSyn.ICpsDecl    decl;

  public OptGlobalCpsDecl(final Token<?> global,
      final IConcSyn.ICpsDecl decl) {
    this.global = global;
    this.decl = decl;
  }

  @Override
  public void appendAsString(final StringBuilder sb, final String indent) {
    sb.append(indent);
    sb.append("global\n");
    this.decl.appendAsString(sb, indent + " ");
  }

  @Override
  public IList<IDecl> toAbsSyn() {
    return this.decl.toAbsSyn();
  }
}
