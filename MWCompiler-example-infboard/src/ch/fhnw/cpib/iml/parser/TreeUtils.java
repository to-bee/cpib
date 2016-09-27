package ch.fhnw.cpib.iml.parser;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashSet;

import ch.fhnw.cpib.iml.scanner.IToken;

public class TreeUtils {
  private static final HashSet<Class<?>> WRAPPER_TYPES = getWrapperTypes();
  private static final String            EOL           = System
                                                           .getProperty("line.separator");

  private TreeUtils() {
  }

  private static void printTree2(final Object o, final Appendable out,
      final int level) throws IOException {
    if (level > 50) out.append("LEVEL > 50 ").append(EOL);
    if (o == null) {
      out.append("NULL").append(EOL);
      return;
    }

    final Class<?> c = o.getClass();
    if (o instanceof CharSequence) {
      out.append(o.toString()).append(EOL);
    } else if (c.isPrimitive() || c.isArray() || isWrapperType(c)) {
      out.append(o.toString()).append(EOL);
    } else if (o instanceof IToken<?>) {
      out.append(o.toString()).append(EOL);
    } else {
      out.append(c.getSimpleName());
      out.append(" = ");
      out.append(o.toString().replace(c.getName(), ""));
      out.append(EOL);
      final Field fields[] = c.getDeclaredFields();
      final int subLevel = level + 1;
      for (final Field field : fields) {
        if (Modifier.isStatic(field.getModifiers())) continue;
        field.setAccessible(true);
        for (int i = 1; i < subLevel; i++)
          out.append(num(i)).append(' ');
        out.append("→ ");
        out.append(field.getName()).append(": ");
        try {
          final Object o2 = field.get(o);
          if (o2 != o) printTree2(o2, out, subLevel);
        } catch (IllegalArgumentException | IllegalAccessException e) {
          out.append(String.valueOf(e)).append(EOL);
        }
      }
    }
  }

  /** Integer as one character. */
  private static char num(final int i) {
    if (i == 0) return '⓪';
    if (i < 10) return (char) ('①' + (i - 1));
    if (i <= 20) return (char) ('⑩' + (i - 10));
    if (i < 30) return (char) ('㉑' + (i - 21));
    return (char) ('㉚' + (i - 30));

  }

  /**
   * Print a tree structure to <code>System.out</code>.
   * Note that this does not check for loops!
   * */
  public static void printTree(final Object o) {
    final StringBuilder sb = new StringBuilder();
    try {
      printTree2(o, sb, 0);
    } catch (final IOException e) {
      System.out.println(e);
    }
    System.out.println(sb.toString());
  }

  /**
   * Print a tree structure to <code>System.out</code>.
   * Note that this does not check for loops!
   *
   * @throws IOException
   * */
  public static void printTree(final Object o, final Appendable out)
      throws IOException {
    if (out == null) throw new IOException("out == null");
    printTree2(o, out, 0);
  }

  public static boolean isWrapperType(final Class<?> clazz) {
    return WRAPPER_TYPES.contains(clazz);
  }

  private static HashSet<Class<?>> getWrapperTypes() {
    final HashSet<Class<?>> ret = new HashSet<Class<?>>();
    ret.add(Boolean.class);
    ret.add(Character.class);
    ret.add(Byte.class);
    ret.add(Short.class);
    ret.add(Integer.class);
    ret.add(Long.class);
    ret.add(Float.class);
    ret.add(Double.class);
    ret.add(Void.class);
    return ret;
  }
}
