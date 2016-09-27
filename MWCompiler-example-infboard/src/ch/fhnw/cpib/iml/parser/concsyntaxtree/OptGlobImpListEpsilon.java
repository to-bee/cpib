package ch.fhnw.cpib.iml.parser.concsyntaxtree;

import ch.fhnw.cpib.iml.parser.abssyntaxtree.Empty;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IGlobalImport;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IList;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IOptGlobImpList;

public class OptGlobImpListEpsilon implements IOptGlobImpList {

  @Override
  public void appendAsString(final StringBuilder sb, final String indent) {

  }

  @Override
  public IList<IGlobalImport> toAbsSyn() {
    return Empty.list();
  }

}
