package ch.fhnw.cpib.iml.parser;

import java.util.Iterator;

import ch.fhnw.cpib.iml.compiler.Context;
import ch.fhnw.cpib.iml.compiler.Variable;
import ch.fhnw.cpib.iml.lib.enums.Type;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.Empty;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IDecl;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IExpr;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IGlobalImport;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IList;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IParameter;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.ITyped;
import ch.fhnw.cpib.iml.parser.exception.ContextException;
import ch.fhnw.cpib.iml.scanner.IPosition;
import ch.fhnw.cpib.iml.scanner.Token;

/**
 * The Lists used by the parser are very simple linked lists, where each Node in
 * the List implements the {@link IList}-Interface and links to another Node.
 * <p>
 * It is always assumed that the lists have no cycles and end in an
 * {@link Empty}-Node, and that always the first element of the list (the
 * <i>head</i>) is passed.
 */
public final class ListUtils {
  /** No need for an object, all is static. */
  private ListUtils() {
  }

  public static int length(final IList<?> list) {
    if (list == null) return 0;
    int len = 0;
    IList<?> l = list;
    while (l.getItem() != null) {
      len++;
      l = l.next();
      assert l != null : "List does not end in Empty-Node.";
    }
    assert l instanceof Empty : "List " + list.toString()
        + " contains element with no item (but is not Empty).";
    return len;
  }

  /** Checks the length of two lists (null is length 0). */
  public static void checkLength(final IList<?> list1, final IList<?> list2,
      final IPosition pos) throws ContextException {
    assert list1 != list2;
    final int len1 = length(list1);
    final int len2 = length(list2);
    if (len1 != len2)
      throw new ContextException("Length of parameter list is incorrect: "
          + len1 + " ≠ " + len2, pos);
  }

  /**
   * Check types of all elements in two lists. The elements hold {@link ITyped
   * typed} items that are either {@link IExpr expressions}, {@link IParameter
   * parameters} or {@link IDecl declarations}.
   * <p>
   * Precondition: Same length (see
   * {@link #checkLength(IList, IList, IPosition)}). <br>
   * Null: If one is null it is assumed that the other is also null or empty.
   */
  public static void checkTypes(final IList<? extends ITyped> list1,
      final IList<? extends ITyped> list2, final IPosition pos)
      throws ContextException {
    if (list1 == null || list2 == null) return;
    IList<? extends ITyped> l1 = list1;
    IList<? extends ITyped> l2 = list2;
    while (l1.getItem() != null && l2.getItem() != null) {
      final Type t1 = l1.getItem().getType();
      final Type t2 = l2.getItem().getType();
      if (t1 != t2)
        throw new ContextException("Wrong type for parameter: " + t1 + " ≠ "
            + t2, pos);
      l1 = l1.next();
      l2 = l2.next();
      assert l1 != null && l2 != null : "List does not end in Empty-Node.";
    }
    assert l1.getClass() == Empty.class && l2.getClass() == Empty.class : "Compared Lists are not of the same length.";
  }

  /**
   * Check if all imported variables from global scope are initialized before
   * the call is made.
   */
  public static void checkInitialized(
      final IList<IGlobalImport> globalImportList, final Context global)
      throws ContextException {
    IList<IGlobalImport> l = globalImportList;
    while (l.getItem() != null) {
      final Token<String> token = l.getItem().getToken();
      final Variable var = global.getVariable(token);
      if (var == null)
        throw new ContextException("Imported variable " + token
            + " does not exist.", token);
      if (!var.isInitialized())
        throw new ContextException("Imported variable " + token
            + " was not initialized before call.", token);
      l = globalImportList.next();
      assert l != null : "List does not end in Empty-Node.";
    }
  }

  public static <E> Iterable<E> iterator(final IList<E> list) {
    return new Itr<E>(list);
  }

  private static class Itr<E> implements Iterator<E>, Iterable<E> {

    IList<E> next = null;

    public Itr(final IList<E> first) {
      this.next = first;
    }

    @Override
    public boolean hasNext() {
      return this.next.getItem() != null; // it's null when it is of type Empty
    }

    @Override
    public E next() {
      final IList<E> n = this.next;
      this.next = this.next.next();
      return n.getItem();
    }

    @Override
    public void remove() {
      throw new RuntimeException("Cannot remove Item from IList!!!");
    }

    @Override
    public Iterator<E> iterator() {
      return this;
    }
  }

  public static String toString(final IList<?> list) {
    final StringBuilder sb = new StringBuilder("List: [");
    for (final Object o : iterator(list)) {
      if (o == null || o instanceof Empty) sb.append(o);
      sb.append(o).append(",");
    }
    sb.append("]");
    return sb.toString();
  }
}
