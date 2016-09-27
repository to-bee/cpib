package ch.fhnw.cpib.iml.parser.concsyntaxtree;

import ch.fhnw.cpib.iml.parser.abssyntaxtree.DeclarationList;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IList;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.ICpsDecl;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IDecl;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IRepDecl;

public class CpsDecl implements ICpsDecl {
  private final IDecl decl;
  private final IRepDecl repDecl;

  public CpsDecl(final IDecl decl, final IRepDecl repDecl) {
    this.decl = decl;
    this.repDecl = repDecl;
  }

  @Override
  public void appendAsString(final StringBuilder sb, final String indent) {
    this.decl.appendAsString(sb, indent + " ");
    this.repDecl.appendAsString(sb, indent + " ");
  }

  @Override
  public IList<ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IDecl> toAbsSyn() {
    return new DeclarationList(this.decl.toAbsSyn(), this.repDecl.toAbsSyn());
  }

}
