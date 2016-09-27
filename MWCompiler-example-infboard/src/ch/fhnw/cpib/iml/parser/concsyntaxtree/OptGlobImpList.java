package ch.fhnw.cpib.iml.parser.concsyntaxtree;

import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IGlobalImport;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IList;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IGlobImpList;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IOptGlobImpList;
import ch.fhnw.cpib.iml.scanner.Token;

public class OptGlobImpList implements IOptGlobImpList {

  private final Token<?> global;
  private final IGlobImpList         globImpList;

  public OptGlobImpList(final Token<?> global,
      final IGlobImpList globImpList) {
    this.global = global;
    this.globImpList = globImpList;
  }

  @Override
  public void appendAsString(final StringBuilder sb, final String indent) {
    sb.append(indent);
    sb.append(this.global.getTerminal());
    sb.append("\n");
    this.globImpList.appendAsString(sb, indent + " ");
  }

  @Override
  public IList<IGlobalImport> toAbsSyn() {
    return this.globImpList.toAbsSyn();
  }

}
