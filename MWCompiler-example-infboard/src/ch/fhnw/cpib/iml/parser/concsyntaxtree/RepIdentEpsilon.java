package ch.fhnw.cpib.iml.parser.concsyntaxtree;

import ch.fhnw.cpib.iml.parser.abssyntaxtree.Empty;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IIdent;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IList;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IRepIdent;

public class RepIdentEpsilon implements IRepIdent {

  @Override
  public void appendAsString(final StringBuilder sb, final String indent) {

  }

  @Override
  public IList<IIdent> toAbsSyn() {
    return Empty.list();
  }

}
