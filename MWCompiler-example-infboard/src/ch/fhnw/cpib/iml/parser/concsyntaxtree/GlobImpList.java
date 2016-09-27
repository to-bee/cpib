package ch.fhnw.cpib.iml.parser.concsyntaxtree;

import ch.fhnw.cpib.iml.parser.abssyntaxtree.GlobalImportList;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IGlobalImport;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IList;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IGlobImp;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IGlobImpList;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IRepGlobImp;

public class GlobImpList implements IGlobImpList {

  private final IGlobImp    globImp;
  private final IRepGlobImp repGlobImp;

  public GlobImpList(final IGlobImp globImp, final IRepGlobImp repGlobImp) {
    this.globImp = globImp;
    this.repGlobImp = repGlobImp;
  }

  @Override
  public void appendAsString(final StringBuilder sb, final String indent) {
    this.globImp.appendAsString(sb, indent + " ");
    this.repGlobImp.appendAsString(sb, indent + " ");
  }

  @Override
  public IList<IGlobalImport> toAbsSyn() {
    return new GlobalImportList(this.globImp.toAbsSyn(), this.repGlobImp
        .toAbsSyn());
  }

}
