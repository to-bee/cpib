package ch.fhnw.cpib.iml.parser.abssyntaxtree;

import static ch.fhnw.cpib.iml.compiler.Compiler.COMPILER;
import ch.fhnw.cpib.iml.lib.enums.Type;
import ch.fhnw.cpib.iml.parser.ListUtils;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IExpr;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IRoutineCall;
import ch.fhnw.cpib.iml.parser.exception.ContextException;
import ch.fhnw.cpib.iml.scanner.Token;
import ch.fhnw.cpib.iml.vm.IVirtualMachine;
import ch.fhnw.cpib.iml.vm.IVirtualMachine.CodeTooSmallError;
import ch.fhnw.cpib.iml.vm.IVirtualMachine.HeapTooSmallError;

public class FunCallExpr implements IExpr {

  private final IRoutineCall  routineCall;

  public FunCallExpr(final IRoutineCall routineCall) {
    this.routineCall = routineCall;
  }

  @Override
  public void check() throws ContextException {
    assert this.type == null;
    final Token<String> ident = this.routineCall.getToken();

    final FunDecl function = COMPILER.getGlobalContext().getFunction(ident);
    if (function == null)
      throw new ContextException("Function does not exist.", ident);

    this.routineCall.setFunc(true);
    this.routineCall.check();

    this.type = function.getType();

    ListUtils.checkInitialized(function.getGlobalImportList(), COMPILER
        .getGlobalContext());

    if (!function.getReturnVariable().isInitialized())
      throw new ContextException("Return Value not set.", ident);
  }

  private Type type = null;

  @Override
  public Token<String> getToken() {
    return this.routineCall.getToken();
  }

  @Override
  public Type getType() {
    return this.type;
  }

  @Override
  public int code(final int location) throws CodeTooSmallError, HeapTooSmallError {
    final IVirtualMachine vm = COMPILER.getVM();
    int loc = location;
    vm.DebugInfo(loc++, this.getClass().getSimpleName(), this.getToken());
    loc = this.routineCall.code(loc);
    return loc;
  }
}
