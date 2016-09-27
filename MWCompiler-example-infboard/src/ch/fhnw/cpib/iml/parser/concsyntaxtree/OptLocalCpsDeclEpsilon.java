package ch.fhnw.cpib.iml.parser.concsyntaxtree;

import ch.fhnw.cpib.iml.parser.abssyntaxtree.Empty;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IDecl;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IList;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IOptLocalCpsDecl;

public class OptLocalCpsDeclEpsilon implements IOptLocalCpsDecl {

  @Override
  public void appendAsString(final StringBuilder sb, final String indent) {

  }

  @Override
  public IList<IDecl> toAbsSyn() {
    return Empty.list();
  }

}
