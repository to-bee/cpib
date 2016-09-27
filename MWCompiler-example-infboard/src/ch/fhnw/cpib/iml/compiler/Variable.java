package ch.fhnw.cpib.iml.compiler;

import ch.fhnw.cpib.iml.lib.enums.ChangeMode;
import ch.fhnw.cpib.iml.lib.enums.FlowMode;
import ch.fhnw.cpib.iml.lib.enums.MechMode;
import ch.fhnw.cpib.iml.lib.enums.Scope;
import ch.fhnw.cpib.iml.lib.enums.Type;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.StoreDecl;
import ch.fhnw.cpib.iml.scanner.IToken;
import ch.fhnw.cpib.iml.scanner.attr.AttrIdent;

public final class Variable {
  Variable(final IToken<String> token, final Type type, final Scope scope,
      final ChangeMode changeMode) {
    super();
    this.token = token;
    this.type = type;
    this.scope = scope;
    this.changeMode = changeMode;
  }

  Variable(final IToken<String> token, final int absLocation,
      final int relLocation, final Type type, final MechMode mechMode,
      final boolean isReturnVal, final Scope scope,
      final ChangeMode changeMode, final FlowMode flowMode) {
    super();
    this.token = token;
    this.absLocation = absLocation;
    this.relLocation = relLocation;
    this.type = type;
    this.mechMode = mechMode;
    this.isReturnVar = isReturnVal;
    this.scope = scope;
    this.changeMode = changeMode;
    this.flowMode = flowMode;
  }

  /** Identifier-Token that gives this variable its name. */
  private final IToken<String> token;
  private final Type           type;
  private final Scope          scope;

  /** Determine if variable has been initialized at given time. */
  private boolean              isInitialized = false;

  /** Determine if variable is Parameter */
  private boolean              isParameter   = false;
  private int                  paramLocation = 0;

  /**
   * When the variables are added to the context, neither absolute nor relative
   * location is known. This has to be set on initialisation.
   */
  private int                  absLocation   = -1;
  private int                  relLocation   = -1;

  /**
   * If {@link StoreDecl} is either in a {@link Parameter} or in a
   * {@link FunDecl} those attributes can be changed outside of the
   * {@link StoreDecl}.
   */
  private MechMode             mechMode      = MechMode.COPY;
  private ChangeMode           changeMode    = ChangeMode.VAR;
  private boolean              isReturnVar   = false;
  private FlowMode             flowMode      = FlowMode.NONE;

  public AttrIdent getAttribute() {
    return (AttrIdent) this.token.getAttribute();
  }

  public IToken<String> getToken() {
    return this.token;
  }

  public String getName() {
    return this.token.getAttribute().getValue();
  }

  public int getAbsLocation() {
    return this.absLocation;
  }

  public void setAbsLocation(final int absLoc) {
    assert this.relLocation == -1;
    this.absLocation = absLoc;
  }

  public Type getType() {
    return this.type;
  }

  public MechMode getMechMode() {
    return this.mechMode;
  }

  public void setMechMode(final MechMode mechMode) {
    this.mechMode = mechMode;
  }

  public boolean isReturnVar() {
    return this.isReturnVar;
  }

  public void setReturnVar(final boolean isReturnVar) {
    this.isReturnVar = isReturnVar;
  }

  public Scope getScope() {
    return this.scope;
  }

  public ChangeMode getChangeMode() {
    return this.changeMode;
  }

  public void setChangeMode(final ChangeMode changeMode) {
    this.changeMode = changeMode;
  }

  public FlowMode getFlowMode() {
    return this.flowMode;
  }

  public void setFlowMode(final FlowMode flowMode) {
    this.flowMode = flowMode;
  }

  public int getRelLocation() {
    return this.relLocation;
  }

  void setRelLocation(final int relLoc) {
    assert this.relLocation == -1;
    this.relLocation = relLoc;
  }

  public boolean isInitialized() {
    return this.isInitialized;
  }

  public void setInitialized(final boolean init) {
    this.isInitialized = init;
  }

  public boolean isParameter() {
    return this.isParameter;
  }

  public void setParameter(final boolean isParam) {
    this.isParameter = isParam;
  }

  public int getParamLocation() {
    return this.paramLocation;
  }

  public void setParamLocation(final int loc) {
    this.paramLocation = loc;
  }

  @Override
  public String toString() {
    final StringBuilder str = new StringBuilder(50);
    str.append(this.getName());
    str.append(" : ");
    str.append(this.scope.name());
    str.append(", ");
    if (this.isInitialized) {
      str.append("Initialized");
      str.append(", ");
    }
    if (this.isReturnVar) {
      str.append("ReturnVar");
      str.append(", ");
    }
    str.append(this.mechMode.name());
    str.append(", ");
    str.append(this.flowMode.name());

    return str.toString();
  }

}