package ch.fhnw.cpib.iml.vm;

import ch.fhnw.cpib.iml.scanner.IPosition;

/**
 * Dr. Edgar Lederer, Fachhochschule Nordwestschweiz.
 *
 * With necessary adjustments for Strings.
 * */

public interface IVirtualMachine {

  public void setDebug(boolean debug, boolean instr, boolean stack, boolean heap);

  boolean readYesNo() throws ExecutionError;

  // an InternalError indicates that the compiler itself is in error
  public static class InternalError extends RuntimeException {
    private static final long serialVersionUID = -1403162622556246662L;

    InternalError(final String errorMessage) {
      super("Internal error: " + errorMessage);
    }
  }

  // an ExecutionError indicates that execution of the program is in error
  // - example: division by zero
  public static class ExecutionError extends Exception {
    private static final long serialVersionUID = 7417525652698303797L;

    ExecutionError(final String errorMessage) {
      super("Execution error: " + errorMessage);
    }
  }

  // a HeapTooSmallError indicates that the heap (and thus the store)
  // is too small to hold all stores allocated during program execution
  public static class HeapTooSmallError extends Exception {
    private static final long serialVersionUID = 653956411472287999L;
  }

  // a CodeTooSmallError indicates that the code is too small
  // to hold the complete program
  public static class CodeTooSmallError extends Exception {
    private static final long serialVersionUID = -1271565425503066142L;
  }

  // executes the program
  void execute() throws ExecutionError;

  // each of the following methods initializes the heap cell
  // for the current value of the heap pointer with a default data object;
  // then decreases the heap pointer
  // - returns the not yet decreased value of the heap pointer
  int BoolInitHeapCell() throws HeapTooSmallError;

  int IntInitHeapCell() throws HeapTooSmallError;

  /**
   * Gets the size from stack and replaces it with the address of the allocated
   * array.
   */
  void IntArrayInitHeap(int loc) throws CodeTooSmallError;

  /**
   * Reads the destination address, size and data from the stack to store it.
   * afterwards only the adress is left on the stack.
   * The address must be initialized already. Use IntArrayInitHeap for that!
   */
  void StoreIntArray(int loc) throws CodeTooSmallError;

  int FloatInitHeapCell() throws HeapTooSmallError;

  // each of the following methods creates an instruction and
  // stores the instruction at position loc of code

  // stop operation
  void Stop(int loc) throws CodeTooSmallError;

  // routine operations
  void Alloc(int loc, int size) throws CodeTooSmallError;

  void Call(int loc, int address) throws CodeTooSmallError;

  void Return(int loc, int size) throws CodeTooSmallError;

  void CopyIn(int loc, int fromAddr, int toAddr) throws CodeTooSmallError;

  void CopyOut(int loc, int fromAddr, int toAddr) throws CodeTooSmallError;

  void Enter(int loc, int size, int extreme) throws CodeTooSmallError;

  // load values (value -> stack)
  void IntLoad(int loc, int value) throws CodeTooSmallError;

  void FloatLoad(int loc, float value) throws CodeTooSmallError;

  // load address relative to frame pointer (address -> stack)
  void LoadRel(int loc, int address) throws CodeTooSmallError;

  // load (inside stack -> top of stack) operation
  void Deref(int loc) throws CodeTooSmallError;

  // store (top of stack -> inside stack) operation
  void Store(int loc) throws CodeTooSmallError;

  // monadic operations
  void IntInv(int loc) throws CodeTooSmallError;

  void FloatInv(int loc) throws CodeTooSmallError;

  // dyadic operations
  void IntAdd(int loc) throws CodeTooSmallError;

  void IntSub(int loc) throws CodeTooSmallError;

  void IntMult(int loc) throws CodeTooSmallError;

  void IntDiv(int loc) throws CodeTooSmallError;

  void IntMod(int loc) throws CodeTooSmallError;

  void IntEQ(int loc) throws CodeTooSmallError;

  void IntNE(int loc) throws CodeTooSmallError;

  void IntGT(int loc) throws CodeTooSmallError;

  void IntLT(int loc) throws CodeTooSmallError;

  void IntGE(int loc) throws CodeTooSmallError;

  void IntLE(int loc) throws CodeTooSmallError;

  // jump operations
  void UncondJump(int loc, int jumpLoc) throws CodeTooSmallError;

  void CondJump(int loc, int jumpLoc) throws CodeTooSmallError;

  // input (input -> stack) and output (stack -> output) operations
  void BoolInput(int loc, String indicator) throws CodeTooSmallError;

  void IntInput(int loc, String indicator) throws CodeTooSmallError;

  void StringInput(int loc, String indicator) throws CodeTooSmallError;

  void BoolOutput(int loc, String indicator) throws CodeTooSmallError;

  void IntOutput(int loc, String indicator) throws CodeTooSmallError;

  void StringOutput(int loc, String indicator) throws CodeTooSmallError;

  void DebugInfo(final int loc, final String msg, final IPosition pos)
      throws CodeTooSmallError;

  @Override
  String toString();
}
