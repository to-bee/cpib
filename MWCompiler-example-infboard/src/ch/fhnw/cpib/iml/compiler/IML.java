package ch.fhnw.cpib.iml.compiler;

import java.awt.Desktop;
import java.io.File;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.List;

import ch.fhnw.cpib.iml.parser.Parser;
import ch.fhnw.cpib.iml.parser.TreeUtils;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn;
import ch.fhnw.cpib.iml.scanner.FileSourceCode;
import ch.fhnw.cpib.iml.scanner.Scanner;
import ch.fhnw.cpib.iml.scanner.TokenList;
import ch.fhnw.cpib.iml.vm.VirtualMachine;

/** Main-Class for the Compiler. */
public class IML {
  public static void main(final String[] args) {
    if (args.length == 0) {
      System.out.println("Usage: ");
      System.out.println("java -jar IML.jar FILENAME [ ENCODING ] [ -debug ]");
      System.exit(0);
      return;
    }

    final File file = new File(args[0]);
    if (!file.exists() || !file.isFile() || !file.canRead()) {
      System.out.println("Can not find/read file.");
    }

    final Charset defaultCharset = Charset.forName("UTF-8");
    Charset encoding = defaultCharset;
    if (args.length >= 2 && !"-debug".equals(args[1])) try {
      encoding = Charset.forName(args[1]);
    } catch (final Throwable t) {
      System.out.println(t);
      System.exit(2);
      return;
    }

    boolean debug = false;
    for (final String string : args)
      if ("-debug".equalsIgnoreCase(string)) debug = true;

    try (FileSourceCode src = new FileSourceCode(file, encoding)) {
      final TokenList tokenList = Scanner.SCANNER.scan(src);
      Parser.PARSER.init(tokenList);
      final IConcSyn.IProgram program = Parser.PARSER.parse();
      final IAbsSyn.IProgram absSyn = program.toAbsSyn();
      final Compilation compilation = Compiler.COMPILER.compile(absSyn);

      if (debug) {
        final String EOL = System.getProperty("line.separator");
        final File temp = File.createTempFile("Syntax-Trees-" + file.getName()
            + "-", ".tmp");
        System.out.println(temp.getAbsolutePath());
        try (PrintStream out = new PrintStream(temp)) {
          out.append("Concrete Syntax Tree:").append(EOL);
          TreeUtils.printTree(program, out);
          out.append(EOL).append(EOL);
          out.append("Abstract Syntax Tree:").append(EOL);
          TreeUtils.printTree(absSyn, out);
          out.append(EOL).append(EOL);
          out.append("VM-Code").append(EOL);
          final List<String> code = ((VirtualMachine) compilation.getVM())
              .getCode();
          for (final String c : code)
            out.append(c).append(EOL);
          out.append(EOL).append(EOL);
          Desktop.getDesktop().browse(temp.toURI());
        }
      }

      // All, but not heap:
      compilation.getVM().setDebug(debug, debug, debug, false);

      compilation.execute();
    } catch (final Throwable t) {
      System.err.println(t);
      if (debug) t.printStackTrace();
      System.exit(3);
      return;
    }
    System.exit(0);
  }
}
