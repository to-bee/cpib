package ch.fhnw.cpib.iml.testcode;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.List;

import org.junit.Test;

import ch.fhnw.cpib.iml.compiler.Compilation;
import ch.fhnw.cpib.iml.compiler.Compiler;
import ch.fhnw.cpib.iml.parser.Parser;
import ch.fhnw.cpib.iml.parser.TreeUtils;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn;
import ch.fhnw.cpib.iml.scanner.FileSourceCode;
import ch.fhnw.cpib.iml.scanner.Scanner;
import ch.fhnw.cpib.iml.scanner.TokenList;
import ch.fhnw.cpib.iml.scanner.exception.ScannerException;
import ch.fhnw.cpib.iml.vm.IVirtualMachine.CodeTooSmallError;
import ch.fhnw.cpib.iml.vm.IVirtualMachine.ExecutionError;
import ch.fhnw.cpib.iml.vm.IVirtualMachine.HeapTooSmallError;
import ch.fhnw.cpib.iml.vm.VirtualMachine;

public class CodeTest {
  final Charset        utf8                       = Charset.forName("UTF-8");
  final static boolean PRINT_CONCRETE_SYNTAX_TREE = false;
  final static boolean PRINT_ABSOLUTE_SYNTAX_TREE = false;
  final static boolean PRINT_CODE                 = false;

  @Test
  public void testGROSSBUCHSTABEN() throws ScannerException, IOException,
      CodeTooSmallError, HeapTooSmallError, ExecutionError {
    this.execute("./docs/IML/GROSSBUCHSTABEN.iml");
  }

  @Test
  public void testHallo() throws ScannerException, IOException,
      CodeTooSmallError, HeapTooSmallError, ExecutionError {
    this.execute("./docs/IML/Hallo.iml");
  }

  @Test
  public void testHalloWelt() throws ScannerException, IOException,
      CodeTooSmallError, HeapTooSmallError, ExecutionError {
    this.execute("./docs/IML/HalloWelt.iml");
  }

  @Test
  public void testTestProg() throws ScannerException, IOException,
      CodeTooSmallError, HeapTooSmallError, ExecutionError {
    this.execute("./docs/IML/testProg.iml");
  }

  @Test
  public void testFunction() throws ScannerException, IOException,
      CodeTooSmallError, HeapTooSmallError, ExecutionError {
    this.execute("./docs/IML/function.iml");
  }

  @Test
  public void testAll() throws ScannerException, IOException,
      CodeTooSmallError, HeapTooSmallError, ExecutionError {
    this.execute("./docs/IML/all.iml");
  }

  @Test
  public void testCipher() throws ScannerException, IOException,
      CodeTooSmallError, HeapTooSmallError, ExecutionError {
    this.execute("./docs/IML/Cipher.iml");
  }

  @Test
  public void testStore() throws ScannerException, IOException,
      CodeTooSmallError, HeapTooSmallError, ExecutionError {
    this.execute("./docs/IML/Store.iml");
  }

  // Code is "dead" when PRINT_* are all false
  @SuppressWarnings("unused")
  private void execute(final String file) throws CodeTooSmallError,
      HeapTooSmallError, ExecutionError, IOException {
    final File f = new File(file);
    try (FileSourceCode src = new FileSourceCode(f, this.utf8)) {
      final TokenList tokenList = Scanner.SCANNER.scan(src);
      Parser.PARSER.init(tokenList);
      final IConcSyn.IProgram program = Parser.PARSER.parse();
      final IAbsSyn.IProgram absSyn = program.toAbsSyn();
      final Compilation compilation = Compiler.COMPILER.compile(absSyn);

      if (PRINT_CONCRETE_SYNTAX_TREE || PRINT_ABSOLUTE_SYNTAX_TREE
          || PRINT_CODE) {
        final String EOL = System.getProperty("line.separator");
        final File temp = File.createTempFile("Syntax-Trees-" + f.getName()
            + "-", ".tmp");
        System.out.println(temp.getAbsolutePath());
        try (PrintStream out = new PrintStream(temp)) {
          if (PRINT_CONCRETE_SYNTAX_TREE) {
            out.append("Concrete Syntax Tree:").append(EOL);
            TreeUtils.printTree(program, out);
            out.append(EOL).append(EOL);
          }
          if (PRINT_ABSOLUTE_SYNTAX_TREE) {
            out.append("Abstract Syntax Tree:").append(EOL);
            TreeUtils.printTree(absSyn, out);
            out.append(EOL).append(EOL);
          }
          if (PRINT_CODE) {
            out.append("VM-Code").append(EOL);
            final List<String> code = ((VirtualMachine) compilation.getVM())
                .getCode();
            for (final String c : code)
              out.append(c).append(EOL);
            out.append(EOL).append(EOL);
          }
          Desktop.getDesktop().browse(temp.toURI());
        }
      }

      compilation.execute();
    }
  }
}
