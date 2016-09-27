package ch.fhnw.cpib.iml.scanner;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import ch.fhnw.cpib.iml.lib.Chr;

public class FileSourceCode implements SourceCode {

  public static final Charset     DEFAULT_CHARSET = Charset.forName("US-ASCII");
  private final InputStreamReader reader;

  public FileSourceCode(final File file) throws IOException {
    this.reader = new InputStreamReader(new FileInputStream(file),
        DEFAULT_CHARSET);
  }

  public FileSourceCode(final File file, final Charset charset)
      throws IOException {
    this.reader = new InputStreamReader(new FileInputStream(file), charset);
  }

  @Override
  public Chr next() throws IOException {
    return Chr.get(this.reader.read());
  }

  @Override
  public void close() throws IOException {
    this.reader.close();
  }

}
