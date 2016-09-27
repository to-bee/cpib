package ch.fhnw.cpib.iml.parser.concsyntaxtree;

import ch.fhnw.cpib.iml.parser.abssyntaxtree.GlobalImportList;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IGlobalImport;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IList;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IGlobImp;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IRepGlobImp;
import ch.fhnw.cpib.iml.scanner.Token;

public class RepGlobImp implements IRepGlobImp {

  private final Token<?> comma;
  private final IGlobImp             globImp;
  private final IRepGlobImp          repGlobImp;

  public RepGlobImp(final Token<?> comma, final IGlobImp globImp,
      final IRepGlobImp repGlobImp) {
    this.comma = comma;
    this.globImp = globImp;
    this.repGlobImp = repGlobImp;
  }

  @Override
  public void appendAsString(final StringBuilder sb, final String indent) {
    sb.append(indent);
    sb.append(this.comma.getTerminal());
    sb.append("\n");
    this.globImp.appendAsString(sb, indent + " ");
    this.repGlobImp.appendAsString(sb, indent + " ");
  }

  @Override
  public IList<IGlobalImport> toAbsSyn() {
    return new GlobalImportList(this.globImp.toAbsSyn(), this.repGlobImp
        .toAbsSyn());
  }

}
