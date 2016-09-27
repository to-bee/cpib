package ch.fhnw.cpib.iml.scanner;

import java.io.Closeable;
import java.io.IOException;
import java.util.Iterator;

import ch.fhnw.cpib.iml.lib.Chr;

/**
 * Interface for objects that allow access to source code.
 * The {@link Scanner} has his own cache, so this only needs one method to read
 * the next {@link Chr}.
 * This works like an {@link Iterator}. Instead of {@link Iterator#hasNext()} it
 * returns a Chr of value -1 at the end of file.
 * */
public interface SourceCode extends Closeable {

  /** Returns the next {@link Chr} in the iteration. */
  public Chr next() throws IOException;
}
