package ch.fhnw.cpib.iml.parser.abssyntaxtree;

import static ch.fhnw.cpib.iml.compiler.Compiler.COMPILER;
import ch.fhnw.cpib.iml.compiler.Variable;
import ch.fhnw.cpib.iml.lib.enums.ChangeMode;
import ch.fhnw.cpib.iml.lib.enums.FlowMode;
import ch.fhnw.cpib.iml.lib.enums.Scope;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IGlobalImport;
import ch.fhnw.cpib.iml.parser.exception.ContextException;
import ch.fhnw.cpib.iml.scanner.Token;
import ch.fhnw.cpib.iml.scanner.attr.Attribute;
import ch.fhnw.cpib.iml.vm.IVirtualMachine;
import ch.fhnw.cpib.iml.vm.IVirtualMachine.CodeTooSmallError;
import ch.fhnw.cpib.iml.vm.IVirtualMachine.HeapTooSmallError;

public class GlobalImport implements IGlobalImport {

  /**
   * Either IN, OUT or INOUT
   */
  private final Attribute<FlowMode>   flowMode;

  /**
   * Either CONST or VAR
   */
  private final Attribute<ChangeMode> changeMode;
  private final Token<String>         ident;

  public GlobalImport(final Attribute<FlowMode> flowMode,
      final Attribute<ChangeMode> changeMode, final Token<String> ident) {
    this.flowMode = flowMode;
    this.changeMode = changeMode;
    this.ident = ident;
  }

  @Override
  public void check() throws ContextException {
    final Variable var = COMPILER.getGlobalContext().getVariable(this.ident);
    if (var == null || var.getScope() != Scope.GLOBAL)
      throw new ContextException("Variable " + this.ident + " is not global.",
          this.ident);
    COMPILER.getCurrentContext().importVariable(var);

    final Variable localVar = COMPILER.getCurrentContext().getVariable(
        this.ident);
    localVar.setChangeMode(this.changeMode.getValue());
    localVar.setFlowMode(this.flowMode.getValue());
  }

  @Override
  public Token<String> getToken() {
    return this.ident;
  }

  @Override
  public int code(final int location) throws CodeTooSmallError, HeapTooSmallError {
    final IVirtualMachine vm = COMPILER.getVM();
    int loc = location;
    vm.DebugInfo(loc++, this.getClass().getSimpleName(), this.getToken());
    return loc;
  }
}
