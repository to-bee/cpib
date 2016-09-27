package ch.fhnw.cpib.iml.compiler;

import java.util.LinkedHashMap;
import java.util.Map;

import ch.fhnw.cpib.iml.lib.enums.ChangeMode;
import ch.fhnw.cpib.iml.lib.enums.FlowMode;
import ch.fhnw.cpib.iml.lib.enums.MechMode;
import ch.fhnw.cpib.iml.lib.enums.Scope;
import ch.fhnw.cpib.iml.lib.enums.Type;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.FunDecl;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.GlobalImport;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.ProcDecl;
import ch.fhnw.cpib.iml.scanner.IToken;
import ch.fhnw.cpib.iml.scanner.Token;

public final class Context {
  private final Context                       parent;
  private final Scope                         scope;

  private final int                           startLocation;
  private int                                 globalImportedVars = 0;

  private final Map<IToken<String>, Variable> variables      = new LinkedHashMap<>();
  private final Map<IToken<String>, FunDecl>  functions      = new LinkedHashMap<>();
  private final Map<IToken<String>, ProcDecl> procedures     = new LinkedHashMap<>();

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
  public boolean exists(final Token<String> ident) {
    return this.variables.containsKey(ident)
        || this.functions.containsKey(ident)
        || this.procedures.containsKey(ident);
  }

  /**
   * Get a variable of this context. return null if it does not exist.
   */
  public Variable getVariable(final IToken<String> varIdent) {
    return this.variables.get(varIdent);
  }

  /**
   * Get a variable of this context or a parent.
   * The returned object is null if the variable does not exist or a
   * {@link Variable}. Use the scope of that {@link Variable} to see if it is
   * local or global.
   */
  public Variable getVariableDeep(final IToken<String> varIdent) {
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

  public void addVariable(final IToken<String> token, final Type type,
      final ChangeMode changeMode) {
    final Variable var = new Variable(token, type, this.scope, changeMode);
    var.setRelLocation(this.variables.size() - this.globalImportedVars);
    var.setAbsLocation(this.startLocation + this.variables.size()
        - this.globalImportedVars);
    this.variables.put(token, var);
  }

  private void addVariable(final IToken<String> token, final int absLocation,
      final int relLocation, final Type type, final MechMode mechMode,
      final boolean isReturnVar, final Scope scope,
      final ChangeMode changeMode, final FlowMode flowMode) {
    final Variable var = new Variable(token, absLocation, relLocation, type,
        mechMode, isReturnVar, scope, changeMode, flowMode);
    this.variables.put(token, var);
  }

  public FunDecl getFunction(final IToken<String> procIdent) {
    assert this.scope == Scope.GLOBAL;
    return this.functions.get(procIdent);
  }

  public void addFunction(final FunDecl fun) {
    assert this.scope == Scope.GLOBAL;
    this.functions.put(fun.getToken(), fun);
  }

  public ProcDecl getProcedure(final IToken<String> procIdent) {
    assert this.scope == Scope.GLOBAL;
    return this.procedures.get(procIdent);
  }

  public void addProcedure(final ProcDecl proc) {
    assert this.scope == Scope.GLOBAL;
    this.procedures.put(proc.getToken(), proc);
  }

  public void setReturnStoreDecl(final Variable returnVariable) {
    if (this.returnVariable != null)
      throw new RuntimeException("returnVariable already set.");
    this.returnVariable = returnVariable;
  }
}
