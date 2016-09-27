package ch.fhnw.cpib.iml.parser.abssyntaxtree;

import static ch.fhnw.cpib.iml.compiler.Compiler.COMPILER;
import ch.fhnw.cpib.iml.compiler.Context;
import ch.fhnw.cpib.iml.lib.enums.ChangeMode;
import ch.fhnw.cpib.iml.lib.enums.Type;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IDecl;
import ch.fhnw.cpib.iml.parser.exception.ContextException;
import ch.fhnw.cpib.iml.scanner.Token;
import ch.fhnw.cpib.iml.scanner.attr.Attribute;
import ch.fhnw.cpib.iml.vm.IVirtualMachine;
import ch.fhnw.cpib.iml.vm.IVirtualMachine.CodeTooSmallError;
import ch.fhnw.cpib.iml.vm.IVirtualMachine.HeapTooSmallError;

/**
 * Declaration of a {@link StoreExpr} (a variable).
 *
 */
public class StoreDecl implements IDecl {

  /**
   * Either CONST or VAR
   */
  private final Attribute<ChangeMode> changeMode;
  private final Token<String>         ident;

  /**
   * Either INT32, STRING or BOOL
   */
  private final Attribute<Type>       type;

  public StoreDecl(final Attribute<ChangeMode> changeMode,
      final Token<String> ident, final Attribute<Type> type) {
    this.changeMode = changeMode;
    this.ident = ident;
    this.type = type;
  }

  @Override
  public void check() throws ContextException {

    // Note that the caller of this method can be a Parameter.
    // When this call returns the variable is known to the context.
    // Some of the modes are set to default values and must be altered
    // afterwards.

    final Context context = COMPILER.getCurrentContext();

    if(context.exists(this.ident))
      throw new ContextException("Duplicate decleration.", this.ident);

    context.addVariable(this.ident,
        Type.ofAttribute(this.type),
        this.changeMode.getValue());
  }

  /**
   * In this case it is the identifier.
   * <hr/>
   * {@inheritDoc}
   */
  @Override
  public Token<String> getToken() {
    return this.ident;
  }

  public Attribute<ChangeMode> getChangeMode() {
    return this.changeMode;
  }

  @Override
  public Type getType() {
    return Type.ofAttribute(this.type);
  }

  @Override
  public int code(final int location) throws CodeTooSmallError, HeapTooSmallError {
    // Note that the caller of this method can be a Parameter.
    // When this call returns the variable is known to the context.
    // Some of the modes are set to default values and must be altered
    // afterwards.

    int loc = location;
    final IVirtualMachine vm = COMPILER.getVM();
    vm.DebugInfo(loc++, this.getClass().getSimpleName(), this.getToken());
    // Allocate one cell on the stack for the variable value:
    vm.Alloc(loc++, 1);

    return loc;
  }
}
