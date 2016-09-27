package ch.fhnw.cpib.iml.scanner;

import java.util.HashMap;
import java.util.Map;

/**
 * All Symbols without Attribute.
 * Note that the IML Specification states that other Tokens are also Symbols.
 * However, this parser handles Attributes and Symbols distinctively.
 * This is therefore a Subset of the Terminal-Enumeration.
 */
public enum Symbol {
  LPAREN("("),
  RPAREN(")"),
  COMMA(","),
  SEMICOLON(";"),
  COLON(":"),
  QUESTMARK("?"),
  EXCLAMARK("!"),
  BECOMES(":="),
  LBRACE("{"),
  RBRACE("}"),
  LBRACKET("["),
  RBRACKET("]");

  final private String                     lexem;
  final private Terminal                   terminal;
  final private static Map<String, Symbol> pool = new HashMap<>(
                                                    values().length * 3);

  private Symbol(final String sym) {
    this.lexem = sym;
    this.terminal = Terminal.valueOf(this.name());
  }

  public Terminal getTerminal() {
    return this.terminal;
  }

  public String getLexem() {
    return this.lexem;
  }

  public static boolean isSymbol(final String lexem) {
    return pool.containsKey(lexem);
  }

  public static Symbol getSymbol(final String lexem) {
    return pool.get(lexem);
  }

  static {
    for (final Symbol sym : values())
      pool.put(sym.getLexem(), sym);
  }
}
