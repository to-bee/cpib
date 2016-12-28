package absSyn;

import scanner.errors.ContextError;
import virtualmachineFS2015.ICodeArray;

public interface IAbsSyn {
    void check() throws ContextError;

    int code(final int location) throws ICodeArray.CodeTooSmallError;
}

//    /** Check the Context and Types of the Node. */
//    void check() throws ContextException;
//
//    /**
//     * Most relevant Token of this Node (preferably an identifier or operator).
//     * Nodes that consist only of other nodes will simply delegate this call to
//     * the first node.
//     * No Token but <tt>null</tt> is returned when no token exists (see
//     * {@link Empty}).
//     * This is used for exception handling, so the compiler knows what Token
//     * caused the exception.
//     */
//    Token<?> getToken();
//
//    /**
//     * Creates the code in the VM and returns the new location.
//     *
//     * @throws CodeTooSmallError
//     *           Thrown when the code segment of the VM is full.
//     * @throws HeapTooSmallError
//     *           Some of the statements need storage on the heap. This is thrown
//     *           when the heap is full at compile time.
//     */
//    int code(int location) throws CodeTooSmallError, HeapTooSmallError;
//  }
//
//  public interface IProgram extends IAbsSynNode {
//
//  }
//
//  public interface IDecl extends IAbsSynNode, ITyped {
//    @Override
//    Token<String> getToken();
//
//    /** Type used in the declaration. Null for procedures. */
//    @Override
//    public Type getType();
//  }
//
//
//  public interface IParameter extends IAbsSynNode, ITyped {
//    @Override
//    public Type getType();
//
//    public void setLocationInParamList(final int location);
//
//    public FlowMode getFlowMode();
//
//    public MechMode getMechMode();
//  }
//
//  public interface IIdent extends IAbsSynNode, ITyped {
//    @Override
//    public Token<String> getToken();
//
//    @Override
//    public Type getType();
//  }
//
//  public interface IGlobalImport extends IAbsSynNode {
//    @Override
//    public Token<String> getToken();
//  }
//
//  public interface ICmd extends IAbsSynNode {
//
//  }
//
//  public interface IExpr extends IAbsSynNode, ITyped {
//    @Override
//    public void check();
//
//    /**
//     * The Type of the ExpressionConcSyn. For some expressions the actual Type is
//     * determined in {@link #check()} and is therefore not available prior to a
//     * call of {@link #check()}.
//     */
//    @Override
//    public Type getType();
//  }
//
//  public interface IRoutineCall extends IAbsSynNode {
//    @Override
//    public Token<String> getToken();
//
//    public IList<IExpr> getParamCallList();
//
//    public void setFunc(boolean isFunc);
//  }
//
//  /**
//   * Linked List in which each element links to another IList.
//   * Each IList contains one Item of generic Type.
//   *
//   * <p>
//   * Generic Types and Implementations:
//   * <dl>
//   * <dt>IList&lt;{@link ICmd}&gt;</dt>
//   * <dd>{@link CmdList}</dd>
//   * <dt>IList&lt;{@link IDecl}&gt;</dt>
//   * <dd>{@link DeclarationList}</dd>
//   * <dt>IList&lt;{@link IGlobalImport}&gt;</dt>
//   * <dd>{@link GlobalImportList}</dd>
//   * <dt>IList&lt;{@link Token}&lt;String&gt;&gt;</dt>
//   * <dd>{@link IdentList}</dd>
//   * <dt>IList&lt;{@link IExpr}&gt;</dt>
//   * <dd>{@link ParamCallList}</dd>
//   * <dt>IList&lt;{@link IParameter}&gt;</dt>
//   * <dd>{@link ParameterListConcSyn}</dd>
//   * <dt>IList&lt;?&gt;</dt>
//   * <dd>{@link Empty}</dd>
//   * </dl>
//   */
//  public interface IList<V> extends IAbsSynNode {
//    /** The Item of this List. */
//    public V getItem();
//
//    /** Next IList that this one links to. */
//    public IList<V> next();
//  }
//
//  public interface ITyped {
//    /**
//     * The Type used in this Node.
//     * It can be a return type, type of a variable or parameter.
//     */
//    public Type getType();
//  }
