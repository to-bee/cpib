package ch.fhnw.cpib.iml.scanner.exception;

import static java.lang.String.format;
import ch.fhnw.cpib.iml.lib.Chr;

public class IllegalChrException extends ScannerException {
  private static final long   serialVersionUID = -8343860155257439435L;
  public final Chr            chr;

  private static final String MSG_FRMT         = "Illegal Character: %s";

  public IllegalChrException(final Chr chr) {
    super(format(MSG_FRMT, chr.getUnicodeData()));
    this.chr = chr;
  }

}