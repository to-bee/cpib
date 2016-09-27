package ch.fhnw.cpib.iml.parser.concsyntaxtree;

import ch.fhnw.cpib.iml.lib.enums.MechMode;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn.IOptMechMode;
import ch.fhnw.cpib.iml.scanner.attr.Attribute;

public class OptMechModeEpsilon implements IOptMechMode {

  @Override
  public void appendAsString(final StringBuilder sb, final String indent) {

  }

  @Override
  public Attribute<MechMode> toAbsSyn() {
    // return Token.getToken("REF").getAttribute();
    return Attribute.getAttribute(MechMode.REF);
  }

}
