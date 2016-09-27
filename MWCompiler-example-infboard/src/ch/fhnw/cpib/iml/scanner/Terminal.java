package ch.fhnw.cpib.iml.scanner;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import ch.fhnw.cpib.iml.scanner.attr.AttrAddOpr;
import ch.fhnw.cpib.iml.scanner.attr.AttrArrFun;
import ch.fhnw.cpib.iml.scanner.attr.AttrBoolOpr;
import ch.fhnw.cpib.iml.scanner.attr.AttrChangeMode;
import ch.fhnw.cpib.iml.scanner.attr.AttrFlowMode;
import ch.fhnw.cpib.iml.scanner.attr.AttrIdent;
import ch.fhnw.cpib.iml.scanner.attr.AttrLiteral;
import ch.fhnw.cpib.iml.scanner.attr.AttrMechMode;
import ch.fhnw.cpib.iml.scanner.attr.AttrMultOpr;
import ch.fhnw.cpib.iml.scanner.attr.AttrRelOpr;
import ch.fhnw.cpib.iml.scanner.attr.AttrType;
import ch.fhnw.cpib.iml.scanner.attr.Attribute;

public enum Terminal {
  /* With Attribute */
  /** Variable Identifier (<code>/[a-zA-Z][a-zA-Z0-9]+/</code>). */
  IDENT(AttrIdent.class),
  /** Literal (<code>/([0-9]+|TRUE|FALSE|\".*?\")/</code>). */
  LITERAL(AttrLiteral.class),
  /** Relational Operator (=,&lt;,/=) */
  RELOPR(AttrRelOpr.class),
  /** Multiplication Operator (*,DIV) */
  MULTOPR(AttrMultOpr.class),
  /** Addition Operator (+,-) */
  ADDOPR(AttrAddOpr.class),
  /** Flow Mode (IN, INOUT) */
  FLOWMODE(AttrFlowMode.class),
  /** Type (INT32, BOOL, STRING) */
  TYPE(AttrType.class),
  /** Boolean Operator (CAND, COR) */
  BOOLOPR(AttrBoolOpr.class),
  /** Change Mode (CONST, VAR) */
  CHANGEMODE(AttrChangeMode.class),
  /** Mech Mode (COPY, REF) */
  MECHMODE(AttrMechMode.class),

  /* Without Attribute : */

  /* Symbols that are also Tokens: */
  LPAREN,
  RPAREN,
  COMMA,
  SEMICOLON,
  COLON,
  QUESTMARK,
  EXCLAMARK,
  BECOMES,
  LBRACE,
  RBRACE,
  LBRACKET,
  RBRACKET,

  /* Other Tokens without Attribute : */
  PROGRAM,
  NOT,
  ELSE,
  GLOBAL,
  WHILE,
  IF,
  INIT,
  LOCAL,
  CALL,
  FUN,
  PROC,
  RETURNS,
  SKIP,
  SENTINEL,

  // Terminals for arrays and strings:
  ARRFUN(AttrArrFun.class);

  /**
   * Indicates whether a Token with this Terminal is used with an Attribute.
   */
  public boolean hasAttribute() {
    return this.attrClass != null;
  }

  final Class<? extends Attribute<?>> attrClass;

  private Terminal() {
    this.attrClass = null;
  }

  private Terminal(final Class<? extends Attribute<?>> ac) {
    this.attrClass = ac;
  }

  private static Map<String, Terminal> with    = new HashMap<>();
  private static Map<String, Terminal> without = new HashMap<>();

  static Map<String, Terminal> getTerminalsWithAttribute() {
    if (with.size() == 0) {
      for (final Terminal t : Terminal.values()) {
        if (t.hasAttribute()) {
          with.put(t.name(), t);
        }
      }
      with = Collections.unmodifiableMap(with);
    }
    return with;
  }

  static Map<String, Terminal> getTerminalsWithoutAttribute() {
    if (without.size() == 0) {
      for (final Terminal t : Terminal.values()) {
        if (!t.hasAttribute()) {
          without.put(t.name(), t);
        }
      }
      without = Collections.unmodifiableMap(without);
    }
    return without;
  }

  static {
    assert getTerminalsWithAttribute().size()
        + getTerminalsWithoutAttribute().size() == values().length;
  }
}
