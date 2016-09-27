package ch.fhnw.cpib.iml.parser.concsyntaxtree;

import ch.fhnw.cpib.iml.parser.abssyntaxtree.DeclarationList;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IList;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IDecl;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IRepDecl;
import ch.fhnw.cpib.iml.scanner.Token;

public class RepDecl implements IRepDecl {

  private final Token<?> semiColon;
  private final IDecl                decl;
  private final IRepDecl             repDecl;

  public RepDecl(final Token<?> semiColon, final IDecl decl,
      final IRepDecl repDecl) {
    this.semiColon = semiColon;
    this.decl = decl;
    this.repDecl = repDecl;
  }

  @Override
  public void appendAsString(final StringBuilder sb, final String indent) {
    sb.append(indent);
    sb.append(this.semiColon.getTerminal());
    sb.append("\n");
    this.decl.appendAsString(sb, indent + " ");
    this.repDecl.appendAsString(sb, indent + " ");
  }

  @Override
  public IList<ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IDecl> toAbsSyn() {
    return new DeclarationList(this.decl.toAbsSyn(), this.repDecl.toAbsSyn());
  }

}
