package ch.fhnw.cpib.iml.lib;

import java.lang.Character.UnicodeBlock;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ch.fhnw.cpib.iml.scanner.attr.AttrStrVal;

/**
 * This represents one unicode character.
 * Allowed values are in [-1,...,0xD7FF] &#8746; [0xE000,...,0x10FFFF] .
 * This is the same as UTF-32, plus one character for "EOF".
 *
 */
public final class Chr {
  public enum Category {
    /**
     * [0-9]
     */
    NUMERIC,
    /**
     * [a-zA-Z]
     */
    ALPHA,
    /**
     * <pre>
     * [\[\]\(\)\{\}]
     * parenthesis ()
     * braces {}
     * square brackets []
     * </pre>
     */
    BRACKET,
    /**
     * [+-/*=<. etc]
     */
    SYMBOL,
    /**
     * Used for String Literals.
     *
     * @see AttrStrVal
     * */
    STRING_CONTROL,
    /**
     * [ \n\r\t\b]
     */
    WHITESPACE,
    /**
     * Everything else...
     */
    ILLEGAL,
    /**
     * End of file (not a character).
     */
    EOF;
  }

  private Chr(final int value) {
    assert value >= -1;
    assert value <= 0x10FFFF;
    this.value = value;

    if (this.value >= '0' && this.value <= '9') {
      this.cat = Category.NUMERIC;
    } else if ((this.value >= 'A' && this.value <= 'Z')
        || (this.value >= 'a' && this.value <= 'z')) {
      this.cat = Category.ALPHA;
    } else if (this.value == ' ' || this.value == '\t' || this.value == '\n'
        || this.value == '\r' || this.value == '\b') {
      this.cat = Category.WHITESPACE;
    } else if ("()[]{}".indexOf(this.value, 0) != -1) {
      this.cat = Category.BRACKET;
    } else if ("+-*/=<>,:;?!.∧∨¬≔≥≤≠ƒ÷×→←".indexOf(this.value, 0) != -1) {
      this.cat = Category.SYMBOL;
    } else if (this.value == AttrStrVal.DELIMITER
        || this.value == AttrStrVal.ESCAPE_CHARACTER) {
      this.cat = Category.STRING_CONTROL;
    } else if (this.value == -1) {
      this.cat = Category.EOF;
    } else {
      this.cat = Category.ILLEGAL;
    }
  }

  private final Category              cat;
  /** Allowed range: [-1,...,0xD7FF] &#8746; [0xE000,...,0x10FFFF]. */
  private final int                   value;

  private static final Map<Integer, Chr> pool = new HashMap<>();

  public static Chr get(final int value) {
    if (!pool.containsKey(value)) {
      final Chr newChr = new Chr(value);
      pool.put(value, newChr);
      return newChr;
    }
    return pool.get(value);
  }

  public Category getCategory() {
    return this.cat;
  }

  public int getValue() {
    return this.value;
  }

  public boolean isEOF() {
    return this.value == -1;
  }

  public static String toString(final List<Chr> list) {
    final StringBuilder sb = new StringBuilder(list.size());
    for (final Chr chr : list) {
      assert chr.value > 0 : "Unexpected character (e.g. EOF, \\0) in string literal: \""
          + sb.toString() + "?...\"";
      sb.appendCodePoint(chr.value);
    }
    return sb.toString();
  }

  @Override
  public String toString() {
    if (this.isEOF()) { return "<EOF>"; }
    return new String(new int[] { this.value }, 0, 1);
  }

  public String getUnicodeData() {
    return "U+" + Integer.toString(this.value, 16) + " name='"
        + Character.getName(this.value) + "' block="
        + UnicodeBlock.of(this.value).toString();
  }
}
