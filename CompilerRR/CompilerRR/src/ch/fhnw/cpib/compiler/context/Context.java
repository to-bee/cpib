package ch.fhnw.cpib.compiler.context;

import java.util.LinkedHashMap;
import java.util.Map;

import ch.fhnw.cpib.compiler.ast.classes.FunctionDeclaration;
import ch.fhnw.cpib.compiler.ast.classes.ProcedureDeclaration;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.operators.ChangeMode;
import ch.fhnw.cpib.compiler.scanner.enums.operators.FlowMode;
import ch.fhnw.cpib.compiler.scanner.enums.operators.MechMode;
import ch.fhnw.cpib.compiler.scanner.enums.operators.Scope;
import ch.fhnw.cpib.compiler.scanner.enums.operators.Type;

public final class Context {
  private final Context                       parent;
  private final Scope                         scope;

  private final int                           startLocation;
  private int                                 globalImportedVars = 0;

  private final Map<Token, Variable> variables      = new LinkedHashMap<>();
  public final Map<Token, FunctionDeclaration>  functions      = new LinkedHashMap<>();
  private final Map<Token, ProcedureDeclaration> procedures     = new LinkedHashMap<>();

  private Variable                            returnVariable = null;

  public Context() {
    this.parent = null;
    this.scope = Scope.GLOBAL;
    this.startLocation = 0;
  }

  public Context(final Context context) {
    this.parent = context;
    this.scope = Scope.LOCAL;
    this.startLocation = context.startLocation + context.variables.size();
  }

  public Context getParent() {
    return this.parent;
  }

  public Scope getScope() {
    return this.scope;
  }

  /** Does anything exist that has the given identifier? */
  public boolean exists(final Token ident) {
    return this.variables.containsKey(ident)
        || this.functions.containsKey(ident)
        || this.procedures.containsKey(ident);
  }

  /**
   * Get a variable of this context. return null if it does not exist.
   */
  public Variable getVariable(final Token varIdent) {
    return this.variables.get(varIdent);
  }

  /**
   * Get a variable of this context or a parent.
   * The returned object is null if the variable does not exist or a
   * {@link Variable}. Use the scope of that {@link Variable} to see if it is
   * local or global.
   */
  public Variable getVariableDeep(final Token varIdent) {
    final Variable var = this.variables.get(varIdent);
    if (var != null) return var;
    if (this.parent == null) return null;
    return this.parent.getVariableDeep(varIdent);
  }

  public int getVariableCount() {
    return this.variables.size();
  }

  /**
   * Add a variable from global scope to this context.
   *
   * @see GlobalImport
   * */
  public void importVariable(final Variable globalVariable) {
    if (globalVariable.getScope() != Scope.GLOBAL)
      throw new RuntimeException("Variable is not global: "
          + globalVariable.getName());
    if (this.scope == Scope.GLOBAL)
      throw new RuntimeException("Scope is already global: "
          + globalVariable.getName());
    this.addVariable(globalVariable.getToken(),
        globalVariable.getAbsLocation(), globalVariable.getRelLocation(),
        globalVariable.getType(), globalVariable.getMechMode(), globalVariable
            .isReturnVar(), Scope.GLOBAL, globalVariable.getChangeMode(),
        globalVariable.getFlowMode());
    this.globalImportedVars++;
  }

  public void addVariable(final Token token, final Type type,
      final ChangeMode changeMode) {
    final Variable var = new Variable(token, type, this.scope, changeMode);
    var.setRelLocation(this.variables.size() - this.globalImportedVars);
    var.setAbsLocation(this.startLocation + this.variables.size()
        - this.globalImportedVars);
    this.variables.put(token, var);
  }

  private void addVariable(final Token token, final int absLocation,
      final int relLocation, final Type type, final MechMode mechMode,
      final boolean isReturnVar, final Scope scope,
      final ChangeMode changeMode, final FlowMode flowMode) {
    final Variable var = new Variable(token, absLocation, relLocation, type,
        mechMode, isReturnVar, scope, changeMode, flowMode);
    this.variables.put(token, var);
  }

  public FunctionDeclaration getFunction(final Token procIdent) {
    assert this.scope == Scope.GLOBAL;
    return this.functions.get(procIdent);
  }

  public void addFunction(final FunctionDeclaration fun) {
    assert this.scope == Scope.GLOBAL;
    this.functions.put(fun.getToken(), fun);
  }

  public ProcedureDeclaration getProcedure(final Token procIdent) {
    assert this.scope == Scope.GLOBAL;
    return this.procedures.get(procIdent);
  }

  public void addProcedure(final ProcedureDeclaration proc) {
    assert this.scope == Scope.GLOBAL;
    this.procedures.put(proc.getToken(), proc);
  }

  public void setReturnStoreDecl(final Variable returnVariable) {
    if (this.returnVariable != null)
      throw new RuntimeException("returnVariable already set.");
    this.returnVariable = returnVariable;
  }
}
