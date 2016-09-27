package ch.fhnw.cpib.iml.vm;

/* Dr. Edgar Lederer, Fachhochschule Nordwestschweiz */

class Data {
  static interface IBaseData {
  }

  static class IntData implements IBaseData {
    private final int i;

    IntData(final int i) {
      this.i = i;
    }

    int getData() {
      return this.i;
    }
  }

  static IntData intNew(final int i) {
    return new IntData(i);
  }

  // pre: a has type IntData
  static int intGet(final IBaseData a) {
    return ((IntData) a).getData();
  }

  // coding booleans as integers
  static IntData boolNew(final boolean b) {
    return intNew(b ? 1 : 0);
  }

  // coding booleans as integers
  static boolean boolGet(final IBaseData a) {
    return ((IntData) a).getData() != 0;
  }

  static class FloatData implements IBaseData {
    private final float f;

    FloatData(final float f) {
      this.f = f;
    }

    float getData() {
      return this.f;
    }
  }

  static FloatData floatNew(final float f) {
    return new FloatData(f);
  }

  // pre: a has type FloatData
  static float floatGet(final IBaseData a) {
    return ((FloatData) a).getData();
  }

  static IntData intInv(final IBaseData a) {
    return intNew(-intGet(a));
  }

  static FloatData floatInv(final IBaseData a) {
    return floatNew(-floatGet(a));
  }

  static IntData intAdd(final IBaseData a, final IBaseData b) {
    return intNew(intGet(a) + intGet(b));
  }

  static IntData intSub(final IBaseData a, final IBaseData b) {
    return intNew(intGet(a) - intGet(b));
  }

  static IntData intMult(final IBaseData a, final IBaseData b) {
    return intNew(intGet(a) * intGet(b));
  }

  static IntData intDiv(final IBaseData a, final IBaseData b)
      throws IVirtualMachine.ExecutionError {
    try {
      return intNew(intGet(a) / intGet(b));
    } catch (final ArithmeticException e) {
      throw new IVirtualMachine.ExecutionError("Integer division by zero.");
    }
  }

  static IntData intMod(final IBaseData a, final IBaseData b)
      throws IVirtualMachine.ExecutionError {
    try {
      return intNew(intGet(a) % intGet(b));
    } catch (final ArithmeticException e) {
      throw new IVirtualMachine.ExecutionError("Integer remainder by zero.");
    }
  }

  static IntData intEQ(final IBaseData a, final IBaseData b) {
    return boolNew(intGet(a) == intGet(b));
  }

  static IntData intNE(final IBaseData a, final IBaseData b) {
    return boolNew(intGet(a) != intGet(b));
  }

  static IntData intGT(final IBaseData a, final IBaseData b) {
    return boolNew(intGet(a) > intGet(b));
  }

  static IntData intLT(final IBaseData a, final IBaseData b) {
    return boolNew(intGet(a) < intGet(b));
  }

  static IntData intGE(final IBaseData a, final IBaseData b) {
    return boolNew(intGet(a) >= intGet(b));
  }

  static IntData intLE(final IBaseData a, final IBaseData b) {
    return boolNew(intGet(a) <= intGet(b));
  }
}
