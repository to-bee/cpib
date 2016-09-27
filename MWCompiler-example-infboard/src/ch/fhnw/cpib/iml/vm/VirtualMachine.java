package ch.fhnw.cpib.iml.vm;

/* Dr. Edgar Lederer, Fachhochschule Nordwestschweiz */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

import ch.fhnw.cpib.iml.scanner.IPosition;
import ch.fhnw.cpib.iml.scanner.IToken;
import ch.fhnw.cpib.iml.scanner.Terminal;
import ch.fhnw.cpib.iml.scanner.attr.Attribute;
import ch.fhnw.cpib.iml.vm.Data.IBaseData;

/**
 * VirtualMachive by Dr. Edgar Lederer, with some changes for Strings and
 * Debugging.
 *
 * @author Dr. Edgar Lederer
 * @author Claude Martin
 * @author Patrick Walther
 *
 */
public class VirtualMachine implements IVirtualMachine {
  private static final String  SP_GT_HP    = "Stack pointer greater than heap pointer.";

  /** Let Debug-Info-Instructions print the into. */
  private static boolean      printDebug = false;
  /** Print all instructions at runtime. */
  private static boolean      printInstr = false;
  /** Print Stack before each instruction. */
  private static boolean      printStack = false;
  /** Print Heap before each instruction. */
  private static boolean      printHeap  = false;

  @Override
  public void setDebug(final boolean debug, final boolean instr,
      final boolean stack, final boolean heap) {
    printDebug = debug;
    printInstr = instr;
    printStack = stack;
    printHeap = heap;
  }

// @formatter:off
  // Actual console, if available. System.in in IDE.
  private final BufferedReader reader      = new BufferedReader(
                                                  System.console() != null
                                                ? System.console().reader()
                                                : new InputStreamReader(System.in)
                                              );
// @formatter:on

  @Override
  public boolean readYesNo() throws ExecutionError {
    String s;
    try {
      s = this.reader.readLine();
    } catch (final IOException e) {
      throw new ExecutionError("Input failed.");
    }
    if (s.equals("no")) {
      return false;
    } else if (s.equals("yes")) {
      return true;
    } else {
      throw new ExecutionError("Not 'yes' or 'no'.");
    }
  }

  boolean readBool() throws ExecutionError {
    String s;
    try {
      s = this.reader.readLine();
    } catch (final IOException e) {
      throw new ExecutionError("Input failed.");
    }
    if (s.equals("false")) {
      return false;
    } else if (s.equals("true")) {
      return true;
    } else {
      throw new ExecutionError("Not a boolean.");
    }
  }

  int readInt() throws ExecutionError {
    String s;
    try {
      s = this.reader.readLine();
    } catch (final IOException e) {
      throw new ExecutionError("Input failed.");
    }
    try {
      return Integer.parseInt(s);
    } catch (final NumberFormatException e) {
      throw new ExecutionError("Not an integer.");
    }
  }

  int[/* max */] readString(final int max) throws ExecutionError {
    try {
      final String s = this.reader.readLine();
      final int length = s.length();
      final int[] utf32 = new int[max];
      int index = 0;
      int offset = 0;
      int codepoint;
      while (offset < length && index < max) {
        codepoint = s.codePointAt(offset);
        utf32[index++] = codepoint;
        offset += Character.charCount(codepoint);
        // if (index > max) throw new ExecutionError("String was too long.");
      }
      return utf32;
    } catch (final IOException e) {
      throw new ExecutionError("Input failed.");
    }
  }

  // stores the program
  final IInstruction[]   code;

  // stores the data
  // - stack: index 0 upto sp-1
  // - heap: index store.length - 1 downto hp+1
  final Data.IBaseData[] store;

  // program counter
  int                    pc;

  // stack pointer
  // - points to the first free location on the stack
  // - stack grows from 0 upwards
  int                    sp;

  // heap pointer
  // - points to the first free location on the heap
  // - heap grows from store.length - 1 downwards
  int                    hp;

  // frame pointer
  // - provides a reference for each routine incarnation
  int                    fp;

  public VirtualMachine(final int codeSize, final int storeSize) {
    this.code = new IInstruction[codeSize];
    this.store = new Data.IBaseData[storeSize];
    this.sp = 0;
    this.hp = this.store.length - 1;
    this.fp = 0;
  }

  @Override
  public int BoolInitHeapCell() throws HeapTooSmallError {
    if (this.hp < this.sp) { throw new HeapTooSmallError(); }
    this.store[this.hp] = Data.boolNew(false);
    return this.hp--;
  }

  @Override
  public int IntInitHeapCell() throws HeapTooSmallError {
    if (this.hp < this.sp) { throw new HeapTooSmallError(); }
    this.store[this.hp] = Data.intNew(0);
    return this.hp--;
  }

  private class IntArrayInitHeap implements IInstruction {
    IntArrayInitHeap() {
    }

    @Override
    public void execute() throws ExecutionError {
      final int size = Data
          .intGet(VirtualMachine.this.store[VirtualMachine.this.sp - 1]);
      for (int i = 0; i < size; i++)
        VirtualMachine.this.store[VirtualMachine.this.hp--] = Data.intNew(0);
      VirtualMachine.this.store[VirtualMachine.this.sp - 1] = Data
          .intNew(VirtualMachine.this.hp + 1);
      VirtualMachine.this.pc = VirtualMachine.this.pc + 1;
    }

    @Override
    public String toString() {
      return "IntArrayInitHeap";
    }
  }

  @Override
  public void IntArrayInitHeap(final int loc) throws CodeTooSmallError {
    if (loc >= this.code.length) throw new CodeTooSmallError();
    this.code[loc] = new IntArrayInitHeap();
  }

  private class StoreIntArray implements IInstruction {
    StoreIntArray() {
    }

    @Override
    public void execute() throws ExecutionError {
      final VirtualMachine vm = VirtualMachine.this;
      final int address = Data.intGet(vm.store[vm.sp - 1]);
      final int size = Data.intGet(vm.store[vm.sp - 2]);
      vm.sp -= 2 + size; // sp is now on first cell of array!
      for (int i = 0; i < size; i++)
        vm.store[address + i] = vm.store[vm.sp + i];
      // leave the address back on the stack:
      vm.store[vm.sp++] = Data.intNew(address);
      vm.pc = vm.pc + 1;
      // hp does not change! This does only store, not allocate.
    }

    @Override
    public String toString() {
      return "StoreIntArray";
    }
  }

  @Override
  public void StoreIntArray(final int loc) throws CodeTooSmallError {
    if (loc >= this.code.length) throw new CodeTooSmallError();
    this.code[loc] = new StoreIntArray();
  }

  @Override
  public int FloatInitHeapCell() throws HeapTooSmallError {
    if (this.hp < this.sp) { throw new HeapTooSmallError(); }
    this.store[this.hp] = Data.floatNew((float) 0.0);
    return this.hp--;
  }

  @Override
  public void execute() throws ExecutionError {
    this.pc = 0;
    while (this.pc > -1) {
      final IInstruction i = this.code[this.pc];
      if (i.getClass() != DebugInfo.class) {
        final StringBuilder sb = new StringBuilder("FP->");
        if (printStack) {
          for (int x = VirtualMachine.this.fp; x <= VirtualMachine.this.sp; x++) {
            final IBaseData a = VirtualMachine.this.store[x];
            final String hexString;
            if (a == null) hexString = "NL";
            else hexString = Integer.toHexString(Data.intGet(a));
            for (int j = 0; j < 2 - hexString.length(); j++)
              sb.append('0');
            sb.append(hexString);
            if (x < VirtualMachine.this.sp) sb.append(':');
          }
          sb.append("<-SP");
          System.out.println(sb);
        }
        if (printHeap) {
          sb.setLength(0);
          sb.append("HP(");
          sb.append(Integer.toHexString(VirtualMachine.this.hp));
          sb.append(")->");
          for (int x = VirtualMachine.this.hp; x < VirtualMachine.this.store.length; x++) {
            final IBaseData a = VirtualMachine.this.store[x];
            final String hexString;
            if (a == null) hexString = "NL";
            else hexString = Integer.toHexString(Data.intGet(a));
            for (int j = 0; j < 2 - hexString.length(); j++)
              sb.append('0');
            sb.append(hexString);
            sb.append(':');
          }
          System.out.println(sb);
        }
        if (printInstr) System.out.println(i.toString());
      }
      i.execute();
    }
  }

  @Override
  public String toString() {
    final StringBuffer b = new StringBuffer();
    for (int i = 0; i < this.code.length; i++) {
      if (this.code[i] != null) {
        b.append(i + ": " + this.code[i] + "\n");
      }
    }
    return b.toString();
  }

  private interface IInstruction {
    void execute() throws ExecutionError;
  }

  // stop instruction
  private class Stop implements IInstruction {
    Stop() {
    }

    @Override
    public void execute() {
      VirtualMachine.this.pc = -1;
    }

    @Override
    public String toString() {
      return "Stop";
    }
  }

  @Override
  public void Stop(final int loc) throws CodeTooSmallError {
    if (loc >= this.code.length) { throw new CodeTooSmallError(); }
    this.code[loc] = new Stop();
  }

  // routine operations

  private class Alloc implements IInstruction {
    private final int size;

    Alloc(final int size) {
      this.size = size;
    }

    @Override
    public void execute() throws ExecutionError {
      VirtualMachine.this.sp = VirtualMachine.this.sp + this.size;
      if (VirtualMachine.this.sp > VirtualMachine.this.hp + 1) { throw new ExecutionError(
          SP_GT_HP); }
      VirtualMachine.this.pc = VirtualMachine.this.pc + 1;
    }

    @Override
    public String toString() {
      return "Alloc(" + this.size + ")";
    }
  }

  @Override
  public void Alloc(final int loc, final int size) throws CodeTooSmallError {
    if (loc >= this.code.length) { throw new CodeTooSmallError(); }
    this.code[loc] = new Alloc(size);
  }

  private class Call implements IInstruction {
    private final int address;

    Call(final int address) {
      this.address = address;
    }

    @Override
    public void execute() throws ExecutionError {
      if (VirtualMachine.this.sp + 2 > VirtualMachine.this.hp) { throw new ExecutionError(
          SP_GT_HP); }
      VirtualMachine.this.store[VirtualMachine.this.sp] = Data
          .intNew(VirtualMachine.this.fp);
      VirtualMachine.this.store[VirtualMachine.this.sp + 1] = Data.intNew(0);
      VirtualMachine.this.store[VirtualMachine.this.sp + 2] = Data
          .intNew(VirtualMachine.this.pc);
      VirtualMachine.this.fp = VirtualMachine.this.sp;
      VirtualMachine.this.pc = this.address;
    }

    @Override
    public String toString() {
      return "Call(" + this.address + ")";
    }
  }

  @Override
  public void Call(final int loc, final int address) throws CodeTooSmallError {
    if (loc >= this.code.length) { throw new CodeTooSmallError(); }
    this.code[loc] = new Call(address);
  }

  private class Return implements IInstruction {
    private final int size;

    Return(final int size) {
      this.size = size;
    }

    @Override
    public void execute() throws ExecutionError {
      VirtualMachine.this.sp = VirtualMachine.this.fp - this.size;
      VirtualMachine.this.pc = Data
          .intGet(VirtualMachine.this.store[VirtualMachine.this.fp + 2]) + 1;
      VirtualMachine.this.fp = Data
          .intGet(VirtualMachine.this.store[VirtualMachine.this.fp]);
    }

    @Override
    public String toString() {
      return "Return(" + this.size + ")";
    }
  }

  @Override
  public void Return(final int loc, final int size) throws CodeTooSmallError {
    if (loc >= this.code.length) { throw new CodeTooSmallError(); }
    this.code[loc] = new Return(size);
  }

  private class CopyIn implements IInstruction {
    private final int fromAddr;
    private final int toAddr;

    CopyIn(final int fromAddr, final int toAddr) {
      this.fromAddr = fromAddr;
      this.toAddr = toAddr;
    }

    @Override
    public void execute() throws ExecutionError {
      final int address = Data
          .intGet(VirtualMachine.this.store[VirtualMachine.this.fp
              + this.fromAddr]);
      VirtualMachine.this.store[VirtualMachine.this.fp + this.toAddr] = VirtualMachine.this.store[address];
      VirtualMachine.this.pc = VirtualMachine.this.pc + 1;
    }

    @Override
    public String toString() {
      return "CopyIn(" + this.fromAddr + "," + this.toAddr + ")";
    }
  }

  @Override
  public void CopyIn(final int loc, final int fromAddr, final int toAddr)
      throws CodeTooSmallError {
    if (loc >= this.code.length) { throw new CodeTooSmallError(); }
    this.code[loc] = new CopyIn(fromAddr, toAddr);
  }

  private class CopyOut implements IInstruction {
    private final int fromAddr;
    private final int toAddr;

    CopyOut(final int fromAddr, final int toAddr) {
      this.fromAddr = fromAddr;
      this.toAddr = toAddr;
    }

    @Override
    public void execute() throws ExecutionError {
      final int address = Data
          .intGet(VirtualMachine.this.store[VirtualMachine.this.fp
              + this.toAddr]);
      VirtualMachine.this.store[address] = VirtualMachine.this.store[VirtualMachine.this.fp
          + this.fromAddr];
      VirtualMachine.this.pc = VirtualMachine.this.pc + 1;
    }

    @Override
    public String toString() {
      return "CopyOut(" + this.fromAddr + "," + this.toAddr + ")";
    }
  }

  @Override
  public void CopyOut(final int loc, final int fromAddr, final int toAddr)
      throws CodeTooSmallError {
    if (loc >= this.code.length) { throw new CodeTooSmallError(); }
    this.code[loc] = new CopyOut(fromAddr, toAddr);
  }

  private class Enter implements IInstruction {
    private final int size;
    private final int extreme;

    Enter(final int size, final int extreme) {
      this.size = size;
      this.extreme = extreme;
    }

    @Override
    public void execute() throws ExecutionError {
      VirtualMachine.this.sp = VirtualMachine.this.fp + 3 + this.size;
      if (VirtualMachine.this.sp > VirtualMachine.this.hp + 1) { throw new ExecutionError(
          SP_GT_HP); }
      VirtualMachine.this.pc = VirtualMachine.this.pc + 1;
    }

    @Override
    public String toString() {
      return "Enter(" + this.size + "," + this.extreme + ")";
    }
  }

  @Override
  public void Enter(final int loc, final int size, final int extreme)
      throws CodeTooSmallError {
    if (loc >= this.code.length) { throw new CodeTooSmallError(); }
    this.code[loc] = new Enter(size, extreme);
  }

  // load values (value -> stack)

  private class IntLoad implements IInstruction {
    private final int value;

    IntLoad(final int value) {
      this.value = value;
    }

    @Override
    public void execute() throws ExecutionError {
      if (VirtualMachine.this.sp > VirtualMachine.this.hp) { throw new ExecutionError(
          SP_GT_HP); }
      VirtualMachine.this.store[VirtualMachine.this.sp] = Data
          .intNew(this.value);
      VirtualMachine.this.sp = VirtualMachine.this.sp + 1;
      VirtualMachine.this.pc = VirtualMachine.this.pc + 1;
    }

    @Override
    public String toString() {
      return "IntLoad(" + this.value + ")";
    }
  }

  @Override
  public void IntLoad(final int loc, final int value) throws CodeTooSmallError {
    if (loc >= this.code.length) { throw new CodeTooSmallError(); }
    this.code[loc] = new IntLoad(value);
  }

  private class FloatLoad implements IInstruction {
    private final float value;

    FloatLoad(final float value) {
      this.value = value;
    }

    @Override
    public void execute() throws ExecutionError {
      if (VirtualMachine.this.sp > VirtualMachine.this.hp) { throw new ExecutionError(
          SP_GT_HP); }
      VirtualMachine.this.store[VirtualMachine.this.sp] = Data
          .floatNew(this.value);
      VirtualMachine.this.sp = VirtualMachine.this.sp + 1;
      VirtualMachine.this.pc = VirtualMachine.this.pc + 1;
    }

    @Override
    public String toString() {
      return "FloatLoad(" + this.value + ")";
    }
  }

  @Override
  public void FloatLoad(final int loc, final float value)
      throws CodeTooSmallError {
    if (loc >= this.code.length) { throw new CodeTooSmallError(); }
    this.code[loc] = new FloatLoad(value);
  }

  // load address relative to frame pointer (address -> stack)
  private class LoadRel implements IInstruction {
    private final int address;

    LoadRel(final int address) {
      this.address = address;
    }

    @Override
    public void execute() throws ExecutionError {
      if (VirtualMachine.this.sp > VirtualMachine.this.hp) { throw new ExecutionError(
          SP_GT_HP); }
      VirtualMachine.this.store[VirtualMachine.this.sp] = Data
          .intNew(this.address + VirtualMachine.this.fp);
      VirtualMachine.this.sp = VirtualMachine.this.sp + 1;
      VirtualMachine.this.pc = VirtualMachine.this.pc + 1;
    }

    @Override
    public String toString() {
      return "LoadRel(" + this.address + ")";
    }
  }

  @Override
  public void LoadRel(final int loc, final int address)
      throws CodeTooSmallError {
    if (loc >= this.code.length) { throw new CodeTooSmallError(); }
    this.code[loc] = new LoadRel(address);
  }

  // load instruction with address on stack
  // load (inside stack -> top of stack) operation
  private class Deref implements IInstruction {
    Deref() {
    }

    @Override
    public void execute() throws ExecutionError {
      final int address = Data
          .intGet(VirtualMachine.this.store[VirtualMachine.this.sp - 1]);
      VirtualMachine.this.store[VirtualMachine.this.sp - 1] = VirtualMachine.this.store[address];
      VirtualMachine.this.pc = VirtualMachine.this.pc + 1;
    }

    @Override
    public String toString() {
      return "Deref";
    }
  }

  @Override
  public void Deref(final int loc) throws CodeTooSmallError {
    if (loc >= this.code.length) { throw new CodeTooSmallError(); }
    this.code[loc] = new Deref();
  }

  // store instruction with address on stack
  // store (top of stack -> inside stack) operation
  private class Store implements IInstruction {
    Store() {
    }

    @Override
    public void execute() throws ExecutionError {
      final int address = Data
          .intGet(VirtualMachine.this.store[VirtualMachine.this.sp - 1]);
      VirtualMachine.this.store[address] = VirtualMachine.this.store[VirtualMachine.this.sp - 2];
      VirtualMachine.this.sp = VirtualMachine.this.sp - 2;
      VirtualMachine.this.pc = VirtualMachine.this.pc + 1;
    }

    @Override
    public String toString() {
      return "Store";
    }
  }

  @Override
  public void Store(final int loc) throws CodeTooSmallError {
    if (loc >= this.code.length) { throw new CodeTooSmallError(); }
    this.code[loc] = new Store();
  }

  // monadic instructions

  private class IntInv implements IInstruction {
    IntInv() {
    }

    @Override
    public void execute() {
      VirtualMachine.this.store[VirtualMachine.this.sp - 1] = Data
          .intInv(VirtualMachine.this.store[VirtualMachine.this.sp - 1]);
      VirtualMachine.this.pc = VirtualMachine.this.pc + 1;
    }

    @Override
    public String toString() {
      return "IntInv";
    }
  }

  @Override
  public void IntInv(final int loc) throws CodeTooSmallError {
    if (loc >= this.code.length) { throw new CodeTooSmallError(); }
    this.code[loc] = new IntInv();
  }

  private class FloatInv implements IInstruction {
    FloatInv() {
    }

    @Override
    public void execute() {
      VirtualMachine.this.store[VirtualMachine.this.sp - 1] = Data
          .floatInv(VirtualMachine.this.store[VirtualMachine.this.sp - 1]);
      VirtualMachine.this.pc = VirtualMachine.this.pc + 1;
    }

    @Override
    public String toString() {
      return "FloatInv";
    }
  }

  @Override
  public void FloatInv(final int loc) throws CodeTooSmallError {
    if (loc >= this.code.length) { throw new CodeTooSmallError(); }
    this.code[loc] = new FloatInv();
  }

  // dyadic instructions

  private class IntAdd implements IInstruction {
    IntAdd() {
    }

    @Override
    public void execute() {
      VirtualMachine.this.sp = VirtualMachine.this.sp - 1;
      VirtualMachine.this.store[VirtualMachine.this.sp - 1] = Data.intAdd(
          VirtualMachine.this.store[VirtualMachine.this.sp - 1],
          VirtualMachine.this.store[VirtualMachine.this.sp]);
      VirtualMachine.this.pc = VirtualMachine.this.pc + 1;
    }

    @Override
    public String toString() {
      return "IntAdd";
    }
  }

  @Override
  public void IntAdd(final int loc) throws CodeTooSmallError {
    if (loc >= this.code.length) { throw new CodeTooSmallError(); }
    this.code[loc] = new IntAdd();
  }

  private class IntSub implements IInstruction {
    IntSub() {
    }

    @Override
    public void execute() {
      VirtualMachine.this.sp = VirtualMachine.this.sp - 1;
      VirtualMachine.this.store[VirtualMachine.this.sp - 1] = Data.intSub(
          VirtualMachine.this.store[VirtualMachine.this.sp - 1],
          VirtualMachine.this.store[VirtualMachine.this.sp]);
      VirtualMachine.this.pc = VirtualMachine.this.pc + 1;
    }

    @Override
    public String toString() {
      return "IntSub";
    }
  }

  @Override
  public void IntSub(final int loc) throws CodeTooSmallError {
    if (loc >= this.code.length) { throw new CodeTooSmallError(); }
    this.code[loc] = new IntSub();
  }

  private class IntMult implements IInstruction {
    IntMult() {
    }

    @Override
    public void execute() {
      VirtualMachine.this.sp = VirtualMachine.this.sp - 1;
      VirtualMachine.this.store[VirtualMachine.this.sp - 1] = Data.intMult(
          VirtualMachine.this.store[VirtualMachine.this.sp - 1],
          VirtualMachine.this.store[VirtualMachine.this.sp]);
      VirtualMachine.this.pc = VirtualMachine.this.pc + 1;
    }

    @Override
    public String toString() {
      return "IntMult";
    }
  }

  @Override
  public void IntMult(final int loc) throws CodeTooSmallError {
    if (loc >= this.code.length) { throw new CodeTooSmallError(); }
    this.code[loc] = new IntMult();
  }

  private class IntDiv implements IInstruction {
    IntDiv() {
    }

    @Override
    public void execute() throws ExecutionError {
      VirtualMachine.this.sp = VirtualMachine.this.sp - 1;
      VirtualMachine.this.store[VirtualMachine.this.sp - 1] = Data.intDiv(
          VirtualMachine.this.store[VirtualMachine.this.sp - 1],
          VirtualMachine.this.store[VirtualMachine.this.sp]);
      VirtualMachine.this.pc = VirtualMachine.this.pc + 1;
    }

    @Override
    public String toString() {
      return "IntDiv";
    }
  }

  @Override
  public void IntDiv(final int loc) throws CodeTooSmallError {
    if (loc >= this.code.length) { throw new CodeTooSmallError(); }
    this.code[loc] = new IntDiv();
  }

  private class IntMod implements IInstruction {
    IntMod() {
    }

    @Override
    public void execute() throws ExecutionError {
      VirtualMachine.this.sp = VirtualMachine.this.sp - 1;
      VirtualMachine.this.store[VirtualMachine.this.sp - 1] = Data.intMod(
          VirtualMachine.this.store[VirtualMachine.this.sp - 1],
          VirtualMachine.this.store[VirtualMachine.this.sp]);
      VirtualMachine.this.pc = VirtualMachine.this.pc + 1;
    }

    @Override
    public String toString() {
      return "IntMod";
    }
  }

  @Override
  public void IntMod(final int loc) throws CodeTooSmallError {
    if (loc >= this.code.length) { throw new CodeTooSmallError(); }
    this.code[loc] = new IntMod();
  }

  private class IntEQ implements IInstruction {
    IntEQ() {
    }

    @Override
    public void execute() {
      VirtualMachine.this.sp = VirtualMachine.this.sp - 1;
      VirtualMachine.this.store[VirtualMachine.this.sp - 1] = Data.intEQ(
          VirtualMachine.this.store[VirtualMachine.this.sp - 1],
          VirtualMachine.this.store[VirtualMachine.this.sp]);
      VirtualMachine.this.pc = VirtualMachine.this.pc + 1;
    }

    @Override
    public String toString() {
      return "IntEQ";
    }
  }

  @Override
  public void IntEQ(final int loc) throws CodeTooSmallError {
    if (loc >= this.code.length) { throw new CodeTooSmallError(); }
    this.code[loc] = new IntEQ();
  }

  private class IntNE implements IInstruction {
    IntNE() {
    }

    @Override
    public void execute() {
      VirtualMachine.this.sp = VirtualMachine.this.sp - 1;
      VirtualMachine.this.store[VirtualMachine.this.sp - 1] = Data.intNE(
          VirtualMachine.this.store[VirtualMachine.this.sp - 1],
          VirtualMachine.this.store[VirtualMachine.this.sp]);
      VirtualMachine.this.pc = VirtualMachine.this.pc + 1;
    }

    @Override
    public String toString() {
      return "IntNE";
    }
  }

  @Override
  public void IntNE(final int loc) throws CodeTooSmallError {
    if (loc >= this.code.length) { throw new CodeTooSmallError(); }
    this.code[loc] = new IntNE();
  }

  private class IntGT implements IInstruction {
    IntGT() {
    }

    @Override
    public void execute() {
      VirtualMachine.this.sp = VirtualMachine.this.sp - 1;
      VirtualMachine.this.store[VirtualMachine.this.sp - 1] = Data.intGT(
          VirtualMachine.this.store[VirtualMachine.this.sp - 1],
          VirtualMachine.this.store[VirtualMachine.this.sp]);
      VirtualMachine.this.pc = VirtualMachine.this.pc + 1;
    }

    @Override
    public String toString() {
      return "IntGT";
    }
  }

  @Override
  public void IntGT(final int loc) throws CodeTooSmallError {
    if (loc >= this.code.length) { throw new CodeTooSmallError(); }
    this.code[loc] = new IntGT();
  }

  private class IntLT implements IInstruction {
    IntLT() {
    }

    @Override
    public void execute() {
      VirtualMachine.this.sp = VirtualMachine.this.sp - 1;
      VirtualMachine.this.store[VirtualMachine.this.sp - 1] = Data.intLT(
          VirtualMachine.this.store[VirtualMachine.this.sp - 1],
          VirtualMachine.this.store[VirtualMachine.this.sp]);
      VirtualMachine.this.pc = VirtualMachine.this.pc + 1;
    }

    @Override
    public String toString() {
      return "IntLT";
    }
  }

  @Override
  public void IntLT(final int loc) throws CodeTooSmallError {
    if (loc >= this.code.length) { throw new CodeTooSmallError(); }
    this.code[loc] = new IntLT();
  }

  private class IntGE implements IInstruction {
    IntGE() {
    }

    @Override
    public void execute() {
      VirtualMachine.this.sp = VirtualMachine.this.sp - 1;
      VirtualMachine.this.store[VirtualMachine.this.sp - 1] = Data.intGE(
          VirtualMachine.this.store[VirtualMachine.this.sp - 1],
          VirtualMachine.this.store[VirtualMachine.this.sp]);
      VirtualMachine.this.pc = VirtualMachine.this.pc + 1;
    }

    @Override
    public String toString() {
      return "IntGE";
    }
  }

  @Override
  public void IntGE(final int loc) throws CodeTooSmallError {
    if (loc >= this.code.length) { throw new CodeTooSmallError(); }
    this.code[loc] = new IntGE();
  }

  private class IntLE implements IInstruction {
    IntLE() {
    }

    @Override
    public void execute() {
      VirtualMachine.this.sp = VirtualMachine.this.sp - 1;
      VirtualMachine.this.store[VirtualMachine.this.sp - 1] = Data.intLE(
          VirtualMachine.this.store[VirtualMachine.this.sp - 1],
          VirtualMachine.this.store[VirtualMachine.this.sp]);
      VirtualMachine.this.pc = VirtualMachine.this.pc + 1;
    }

    @Override
    public String toString() {
      return "IntLE";
    }
  }

  @Override
  public void IntLE(final int loc) throws CodeTooSmallError {
    if (loc >= this.code.length) { throw new CodeTooSmallError(); }
    this.code[loc] = new IntLE();
  }

  // jump instructions

  private class UncondJump implements IInstruction {
    private final int jumpLoc;

    UncondJump(final int jumpLoc) {
      this.jumpLoc = jumpLoc;
    }

    @Override
    public void execute() {
      VirtualMachine.this.pc = this.jumpLoc;
    }

    @Override
    public String toString() {
      return "UncondJump(" + this.jumpLoc + ")";
    }
  }

  @Override
  public void UncondJump(final int loc, final int jumpLoc)
      throws CodeTooSmallError {
    if (loc >= this.code.length) { throw new CodeTooSmallError(); }
    this.code[loc] = new UncondJump(jumpLoc);
  }

  private class CondJump implements IInstruction {
    private final int jumpLoc;

    CondJump(final int jumpLoc) {
      this.jumpLoc = jumpLoc;
    }

    @Override
    public void execute() {
      VirtualMachine.this.sp = VirtualMachine.this.sp - 1;
      VirtualMachine.this.pc = (Data
          .boolGet(VirtualMachine.this.store[VirtualMachine.this.sp])) ? VirtualMachine.this.pc + 1
          : this.jumpLoc;
    }

    @Override
    public String toString() {
      return "CondJump(" + this.jumpLoc + ")";
    }
  }

  @Override
  public void CondJump(final int loc, final int jumpLoc)
      throws CodeTooSmallError {
    if (loc >= this.code.length) { throw new CodeTooSmallError(); }
    this.code[loc] = new CondJump(jumpLoc);
  }

  // input (input -> stack) and output (stack -> output) instructions

  private class BoolInput implements IInstruction {
    private final String indicator;

    BoolInput(final String indicator) {
      this.indicator = indicator;
    }

    @Override
    public void execute() throws ExecutionError {
      System.out.print("?" + this.indicator + " : bool = ");
      final boolean input = VirtualMachine.this.readBool();
      final int address = Data
          .intGet(VirtualMachine.this.store[VirtualMachine.this.sp - 1]);
      VirtualMachine.this.store[address] = Data.boolNew(input);
      VirtualMachine.this.sp = VirtualMachine.this.sp - 1;
      VirtualMachine.this.pc = VirtualMachine.this.pc + 1;
    }

    @Override
    public String toString() {
      return "BoolInput(\"" + this.indicator + "\")";
    }
  }

  @Override
  public void BoolInput(final int loc, final String indicator)
      throws CodeTooSmallError {
    if (loc >= this.code.length) { throw new CodeTooSmallError(); }
    this.code[loc] = new BoolInput(indicator);
  }

  private class IntInput implements IInstruction {
    private final String indicator;

    IntInput(final String indicator) {
      this.indicator = indicator;
    }

    @Override
    public void execute() throws ExecutionError {
      System.out.print("?" + this.indicator + " : int = ");
      final int input = VirtualMachine.this.readInt();
      final int address = Data
          .intGet(VirtualMachine.this.store[VirtualMachine.this.sp - 1]);
      VirtualMachine.this.store[address] = Data.intNew(input);
      VirtualMachine.this.sp = VirtualMachine.this.sp - 1;
      VirtualMachine.this.pc = VirtualMachine.this.pc + 1;
    }

    @Override
    public String toString() {
      return "IntInput(\"" + this.indicator + "\")";
    }
  }

  @Override
  public void IntInput(final int loc, final String indicator)
      throws CodeTooSmallError {
    if (loc >= this.code.length) { throw new CodeTooSmallError(); }
    this.code[loc] = new IntInput(indicator);
  }

  private class StringInput implements IInstruction {
    private final String indicator;

    StringInput(final String indicator) {
      this.indicator = indicator;
    }

    @Override
    public void execute() throws ExecutionError {
      System.out.print("?" + this.indicator + " : string = ");
      final VirtualMachine vm = VirtualMachine.this;
      int address = Data.intGet(vm.store[vm.sp - 1]);
      final int max = Data.intGet(vm.store[address]);
      final int[] input = vm.readString(max);
      for (final int i : input)
        vm.store[++address] = Data.intNew(i);

      vm.sp = vm.sp - 1;
      vm.pc = vm.pc + 1;
    }

    @Override
    public String toString() {
      return "StringInput(\"" + this.indicator + "\")";
    }
  }

  @Override
  public void StringInput(final int loc, final String indicator)
      throws CodeTooSmallError {
    if (loc >= this.code.length) { throw new CodeTooSmallError(); }
    this.code[loc] = new StringInput(indicator);
  }

  private class BoolOutput implements IInstruction {
    private final String indicator;

    BoolOutput(final String indicator) {
      this.indicator = indicator;
    }

    @Override
    public void execute() {
      VirtualMachine.this.sp = VirtualMachine.this.sp - 1;
      final boolean output = Data
          .boolGet(VirtualMachine.this.store[VirtualMachine.this.sp]);
      System.out.println("!" + this.indicator + " : bool = " + output);
      VirtualMachine.this.pc = VirtualMachine.this.pc + 1;
    }

    @Override
    public String toString() {
      return "BoolOutput(\"" + this.indicator + "\")";
    }
  }

  @Override
  public void BoolOutput(final int loc, final String indicator)
      throws CodeTooSmallError {
    if (loc >= this.code.length) { throw new CodeTooSmallError(); }
    this.code[loc] = new BoolOutput(indicator);
  }

  private class IntOutput implements IInstruction {
    private final String indicator;

    IntOutput(final String indicator) {
      this.indicator = indicator;
    }

    @Override
    public void execute() {
      VirtualMachine.this.sp = VirtualMachine.this.sp - 1;
      final int output = Data
          .intGet(VirtualMachine.this.store[VirtualMachine.this.sp]);
      System.out.println("!" + this.indicator + " : int = " + output);
      VirtualMachine.this.pc = VirtualMachine.this.pc + 1;
    }

    @Override
    public String toString() {
      return "IntOutput(\"" + this.indicator + "\")";
    }
  }

  @Override
  public void IntOutput(final int loc, final String indicator)
      throws CodeTooSmallError {
    if (loc >= this.code.length) { throw new CodeTooSmallError(); }
    this.code[loc] = new IntOutput(indicator);
  }

  private class StringOutput implements IInstruction {
    private final String indicator;

    StringOutput(final String indicator) {
      this.indicator = indicator;
    }

    @Override
    public void execute() {
      final VirtualMachine vm = VirtualMachine.this;
      vm.sp = vm.sp - 1;
      final int address = Data.intGet(vm.store[vm.sp]);
      final int maxlen = Data.intGet(vm.store[address]);
      final StringBuilder sb = new StringBuilder();
      for (int i = address + 1; i < address + 1 + maxlen; i++) {
        final int codePoint = Data.intGet(vm.store[i]);
        if (codePoint == 0) break;
        sb.appendCodePoint(codePoint);
      }
      System.out.println("!" + this.indicator + " : string = " + sb.toString());
      vm.pc = vm.pc + 1;
    }

    @Override
    public String toString() {
      return "StringOutput(\"" + this.indicator + "\")";
    }
  }

  @Override
  public void StringOutput(final int loc, final String indicator)
      throws CodeTooSmallError {
    if (loc >= this.code.length) { throw new CodeTooSmallError(); }
    this.code[loc] = new StringOutput(indicator);
  }

  private class DebugInfo implements IInstruction {
    @SuppressWarnings("unused")
    // This would be the location of the Debug-Info, but we don't use it.
    final int       loc;
    final String    msg;
    final IPosition pos;

    public DebugInfo(final int loc, final String msg, final IPosition pos) {
      this.loc = loc;
      this.msg = msg;
      this.pos = pos;
    }

    @Override
    public void execute() throws ExecutionError {
      if (printDebug)
        System.out.println(this.toString());
      VirtualMachine.this.pc++;
    }

    @Override
    public String toString() {
      if (this.pos == null) return this.msg;
      final String token;
      if (this.pos instanceof IToken<?>) {
        final IToken<?> t = (IToken<?>) this.pos;
        final Attribute<?> a = t.getAttribute();
        if (t.getTerminal() == Terminal.IDENT) token = a.getLexem();
        else if (t.getTerminal() == Terminal.LITERAL) token = a.getLexem();
        else if (a != null) token = String.valueOf(a.getValue());
        else token = String.valueOf(this.pos);
      } else token = String.valueOf(this.pos);
      return String.format("%-12s @%3d:%-3d- %s", token, this.pos
          .getLineNumber(), this.pos.getColumn(), this.msg);
    }
  }

  @Override
  public void DebugInfo(final int loc, final String msg, final IPosition pos)
      throws CodeTooSmallError {
    if (loc >= this.code.length) { throw new CodeTooSmallError(); }
    this.code[loc] = new DebugInfo(loc, msg, pos);
  }

  public List<String> getCode() {
    final List<String> rv = new LinkedList<>();
    for (final IInstruction i : this.code) {
      if (i != null) rv.add(i.toString());
      if (i instanceof Stop) break;
    }
    return rv;
  }

}
