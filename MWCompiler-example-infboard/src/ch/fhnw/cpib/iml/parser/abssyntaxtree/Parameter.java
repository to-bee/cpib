package ch.fhnw.cpib.iml.parser.abssyntaxtree;

import static ch.fhnw.cpib.iml.compiler.Compiler.COMPILER;
import ch.fhnw.cpib.iml.compiler.Variable;
import ch.fhnw.cpib.iml.lib.enums.FlowMode;
import ch.fhnw.cpib.iml.lib.enums.MechMode;
import ch.fhnw.cpib.iml.lib.enums.Type;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IParameter;
import ch.fhnw.cpib.iml.parser.exception.ContextException;
import ch.fhnw.cpib.iml.scanner.Token;
import ch.fhnw.cpib.iml.scanner.attr.Attribute;
import ch.fhnw.cpib.iml.vm.IVirtualMachine;
import ch.fhnw.cpib.iml.vm.IVirtualMachine.CodeTooSmallError;
import ch.fhnw.cpib.iml.vm.IVirtualMachine.HeapTooSmallError;

public class Parameter implements IParameter {

  /**
   * Either IN, OUT, or INOUT
   */
  private final Attribute<FlowMode> flowMode;

  /**
   * Either COPY or REF
   */
  private final Attribute<MechMode> mechMode;
  private final StoreDecl           storeDecl;

  private int                       locationInParamList = 0;

  public Parameter(final Attribute<FlowMode> flowMode,
      final Attribute<MechMode> mechMode, final StoreDecl storeDecl) {
    this.flowMode = flowMode;
    this.mechMode = mechMode;
    this.storeDecl = storeDecl;
  }

  @Override
  public void check() throws ContextException {
    final Token<String> token = this.storeDecl.getToken();
    if (COMPILER.getCurrentContext().getVariable(token) != null)
      throw new ContextException("Parameter " + token.getAttribute()
          + " already exists.", token);

    this.storeDecl.check();
    final Variable var = COMPILER.getCurrentContext().getVariable(token);
    assert var != null;

    var.setFlowMode(FlowMode.ofAttribute(this.flowMode));
    var.setMechMode(MechMode.ofAttribute(this.mechMode));
    var.setParameter(true);
    var.setParamLocation(this.locationInParamList);
  }

  @Override
  public Token<String> getToken() {
    return this.storeDecl.getToken();
  }

  @Override
  public Type getType() {
    return this.storeDecl.getType();
  }

  @Override
  public int code(final int location) throws CodeTooSmallError, HeapTooSmallError {
    final IVirtualMachine vm = COMPILER.getVM();
    final Variable var = COMPILER.getCurrentContext().getVariable(
        this.storeDecl.getToken());
    int loc = location;
    vm.DebugInfo(loc++, this.getClass().getSimpleName(), this.getToken());

    loc = this.storeDecl.code(loc);

    vm.LoadRel(loc++, -var.getParamLocation());
    vm.Deref(loc++);
    vm.LoadRel(loc++, var.getRelLocation());
    vm.Store(loc++);

    return loc;
  }

  @Override
  public void setLocationInParamList(final int location) {
    this.locationInParamList = location;
  }

  @Override
  public FlowMode getFlowMode() {
    return this.flowMode.getValue();
  }

  @Override
  public MechMode getMechMode() {
    return this.mechMode.getValue();
  }
}
