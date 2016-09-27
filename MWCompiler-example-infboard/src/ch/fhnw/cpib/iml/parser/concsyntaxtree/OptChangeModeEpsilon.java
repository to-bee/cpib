package ch.fhnw.cpib.iml.parser.concsyntaxtree;

import ch.fhnw.cpib.iml.lib.enums.ChangeMode;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IOptChangeMode;
import ch.fhnw.cpib.iml.scanner.attr.Attribute;

public class OptChangeModeEpsilon implements IOptChangeMode {

  @Override
  public void appendAsString(final StringBuilder sb, final String indent) {

  }

  @Override
  public Attribute<ChangeMode> toAbsSyn() {
    // return Token.getToken("VAR").getAttribute();
    return Attribute.getAttribute(ChangeMode.VAR);
  }

}
