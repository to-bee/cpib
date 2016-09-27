package ch.fhnw.cpib.iml.parser.abssyntaxtree;

import ch.fhnw.cpib.iml.lib.enums.Type;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IExpr;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IList;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.ITyped;
import ch.fhnw.cpib.iml.parser.exception.ContextException;
import ch.fhnw.cpib.iml.scanner.Token;
import ch.fhnw.cpib.iml.vm.IVirtualMachine.CodeTooSmallError;
import ch.fhnw.cpib.iml.vm.IVirtualMachine.HeapTooSmallError;

public class Empty implements IExpr, IList<Object>, ITyped {
  // IParamCallList, ICmdList, IIdentList,
  // IGlobalImportList, IDeclarationList, IParameterList

  @Override
  public void check() throws ContextException {
    // Nothing to be checked.
  }

  @Override
  public Token<String> getToken() {
    return null; // No Token available in empty node.
  }

  @Override
  public Type getType() {
    return null;
  }

  @Override
  public IList<Object> next() {
    return null;
  }

  @Override
  public Object getItem() {
    return null;
  }

  @SuppressWarnings("unchecked")
  public static <T extends IList<?>> T list() {
    return (T) new Empty();
  }

  @Override
  public int code(final int location) throws CodeTooSmallError, HeapTooSmallError {
    return location;
  }

  @Override
  public String toString() {
    return "Empty";
  }
}
