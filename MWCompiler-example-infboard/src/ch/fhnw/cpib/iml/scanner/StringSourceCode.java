package ch.fhnw.cpib.iml.scanner;

import java.io.IOException;

import ch.fhnw.cpib.iml.lib.Chr;

public class StringSourceCode implements SourceCode {

  /**
   * Representation of the source code as UTF-32.
   */
  final int[] src;
  int         i = 0;

  public StringSourceCode(final String src) {
    // Convert UTF-16 to UTF-32:
    this.src = convertUTF16ToUTF32(src);
  }

  @Override
  public void close() throws IOException {
    // nothing to close...
  }

  @Override
  public Chr next() {
    try {
      return Chr.get(this.src[this.i]);
    } catch (final Exception e) {
      return Chr.get(-1);
    } finally {
      this.i++;
    }
  }

  public static int[] convertUTF16ToUTF32(final String utf16) {
    final int length = utf16.length();
    final int[] utf32 = new int[utf16.codePointCount(0, length)];
    int index = 0;
    int offset = 0;
    int codepoint;
    while (offset < length) {
      codepoint = utf16.codePointAt(offset);
      utf32[index++] = codepoint;
      offset += Character.charCount(codepoint);
    }
    assert index == utf32.length;
    return utf32;
  }

}
