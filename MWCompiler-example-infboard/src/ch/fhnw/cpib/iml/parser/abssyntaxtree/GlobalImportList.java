package ch.fhnw.cpib.iml.parser.abssyntaxtree;

import static ch.fhnw.cpib.iml.compiler.Compiler.COMPILER;
import ch.fhnw.cpib.iml.parser.ListUtils;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IGlobalImport;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IList;
import ch.fhnw.cpib.iml.parser.exception.ContextException;
import ch.fhnw.cpib.iml.scanner.Token;
import ch.fhnw.cpib.iml.vm.IVirtualMachine;
import ch.fhnw.cpib.iml.vm.IVirtualMachine.CodeTooSmallError;
import ch.fhnw.cpib.iml.vm.IVirtualMachine.HeapTooSmallError;

public class GlobalImportList implements IList<IGlobalImport> {

  private final IGlobalImport     globalImport;
  private final IList<IGlobalImport> globalImportList;

  public GlobalImportList(final IGlobalImport globalImport,
      final IList<IGlobalImport> globalImportList) {
    this.globalImport = globalImport;
    this.globalImportList = globalImportList;
  }

  @Override
  public void check() throws ContextException {
    this.globalImport.check();
    this.globalImportList.check();
  }

  @Override
  public Token<String> getToken() {
    return this.globalImport.getToken();
  }

  @Override
  public IGlobalImport getItem() {
    return this.globalImport;
  }

  @Override
  public IList<IGlobalImport> next() {
    return this.globalImportList;
  }

  @Override
  public int code(final int location) throws CodeTooSmallError, HeapTooSmallError {
    final IVirtualMachine vm = COMPILER.getVM();
    int loc = location;
    vm.DebugInfo(loc++, this.getClass().getSimpleName(), this.getToken());
    loc = this.globalImport.code(loc);
    return this.globalImportList.code(loc);
  }

  @Override
  public String toString() {
    return ListUtils.toString(this);
  }
}
