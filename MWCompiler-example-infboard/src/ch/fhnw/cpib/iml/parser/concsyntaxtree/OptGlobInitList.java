package ch.fhnw.cpib.iml.parser.concsyntaxtree;

import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IIdent;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IList;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IGlobInitList;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IOptGlobInitList;
import ch.fhnw.cpib.iml.scanner.Token;

public class OptGlobInitList implements IOptGlobInitList {

  private final Token<?> init;
  private final IGlobInitList     globInitList;

  public OptGlobInitList(final Token<?> init,
      final IGlobInitList globInitList) {
    this.init = init;
    this.globInitList = globInitList;
  }

  @Override
  public void appendAsString(final StringBuilder sb, final String indent) {
    sb.append(indent);
    sb.append(this.init.getAttribute());
    sb.append("\n");
    this.globInitList.appendAsString(sb, indent + " ");
  }

  @Override
  public IList<IIdent> toAbsSyn() {
    return this.globInitList.toAbsSyn();
  }

}
