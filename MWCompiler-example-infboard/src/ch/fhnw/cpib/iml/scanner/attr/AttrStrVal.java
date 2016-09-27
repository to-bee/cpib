package ch.fhnw.cpib.iml.scanner.attr;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import ch.fhnw.cpib.iml.scanner.StringSourceCode;
import ch.fhnw.cpib.iml.scanner.exception.InvalidEscapeSequenceException;

/**
 * String Literals.
 */
public final class AttrStrVal extends AttrLiteral {
  public final static int                  ESCAPE_CHARACTER = '\\';
  public final static int                  DELIMITER        = '\"';

  public static final Map<Integer, String> escapes;
  private static final String              set;

  static {
    final Map<Integer, String> esc = new HashMap<>();
    esc.put(DELIMITER, "" + ((char) DELIMITER));
    esc.put(ESCAPE_CHARACTER, "" + ((char) ESCAPE_CHARACTER));
    esc.put((int) '0', "\0");
    esc.put((int) 't', "\t");
    esc.put((int) 'n', "\n");
    esc.put((int) 'r', "\r");
    esc.put((int) 'b', "\b");
    esc.put((int) 'f', "\f");
    esc.put((int) 'R', System.getProperty("line.separator"));

    escapes = Collections.unmodifiableMap(esc);
    final StringBuilder sb = new StringBuilder("[");
    for (final Integer i : esc.keySet())
      sb.append((char) i.intValue()).append(",");
    sb.deleteCharAt(sb.length() - 1);
    sb.append("]");
    set = sb.toString();
  }

  public AttrStrVal(final String lexem) {
    super(lexem, getInts(lexem));
    assert lexem.charAt(0) == AttrStrVal.DELIMITER;
    assert lexem.charAt(lexem.length() - 1) == AttrStrVal.DELIMITER;
  }

  /**
   * The "raw" data of the string as int32-array of the UTF-32 encoded
   * characters, holding the (maximal) length of the string at index 0.
   *
   * @see #toJavaString()
   */
  static private final int[] getInts(final String lexem) {
    final int[] utf32 = StringSourceCode.convertUTF16ToUTF32(lexem);
    final ArrayList<Integer> list = new ArrayList<>(1 + utf32.length);
    list.add(new Integer(0));// Actual length is not known yet
    assert utf32[0] == AttrStrVal.DELIMITER;
    for (int i = 1; i < utf32.length - 1; i++) {
      final int cp = utf32[i];
      if (cp == AttrStrVal.ESCAPE_CHARACTER) {
        final String str = escapes.get(utf32[++i]);
        if (str == null)
          throw new InvalidEscapeSequenceException(
              "Invalid Escape Sequence: \\" + ((char) utf32[i])
                  + " - Must be one of " + set + ".");
        for (int j = 0; j < str.length(); j++)
          list.add((int) str.charAt(j));
      } else {
        list.add(cp);
      }
    }

    list.set(0, list.size() - 1); // Actual length of string
    final int[] ints = new int[list.size()];
    for (int i = 0; i < list.size(); i++)
      ints[i] = list.get(i);
    return ints;
  }

  /**
   * Java String representation of the lexem.
   */
  public String toJavaString() {
    return new String(this.value, 1, this.value.length - 1);
  }

  @Override
  public String toString() {
    return "StrVal " + this.lexem;
  }
}
