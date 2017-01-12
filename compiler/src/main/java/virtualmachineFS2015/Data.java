// Virtual Machine Java 2015, V01
// Edgar F.A. Lederer, FHNW and Uni Basel, 2015

package virtualmachineFS2015;

public class Data
{
    static interface IBaseData
    {
        IBaseData copy();
    }

    static class IntData implements IBaseData
    {
        private int i;
        IntData(int i) { this.i= i; }
        int getData() { return i; }
        public IntData copy() { return intCopy(this); }
    }

    static IntData intNew(int i)
    {
        return new IntData(i);
    }

    static int intGet(IBaseData a)
    {
        return ((IntData)a).getData();
    }

    static IntData intCopy(IBaseData a)
    {
        return intNew(intGet(a));
    }

    // coding booleans as integers
    static IntData boolNew(boolean b)
    {
        return intNew(b ? 1 : 0);
    }

    // coding booleans as integers
    static boolean boolGet(IBaseData a)
    {
        return ((IntData)a).getData() != 0;
    }

    static class FloatData implements IBaseData
    {
        private float f;
        FloatData(float f) { this.f= f; }
        float getData() { return f; }
        public FloatData copy() { return floatCopy(this); }
    }

    static FloatData floatNew(float f)
    {
        return new FloatData(f);
    }

    static float floatGet(IBaseData a)
    {
        return ((FloatData)a).getData();
    }

    static FloatData floatCopy(IBaseData a)
    {
        return floatNew(floatGet(a));
    }

    static IntData intInv(IBaseData a)
    {
        return intNew(-intGet(a));
    }

    static FloatData floatInv(IBaseData a)
    {
        return floatNew(-floatGet(a));
    }

    static IntData intAdd(IBaseData a, IBaseData b)
    {
        return intNew(intGet(a) + intGet(b));
    }

    static IntData intSub(IBaseData a, IBaseData b)
    {
        return intNew(intGet(a) - intGet(b));
    }

    static IntData intMult(IBaseData a, IBaseData b)
    {
        return intNew(intGet(a) * intGet(b));
    }

    static IntData intDivTrunc(IBaseData a, IBaseData b) throws IVirtualMachine.ExecutionError
    {
        try
        {
            return intNew(intGet(a) / intGet(b));
        }
        catch (ArithmeticException e)
        {
            throw new VirtualMachine.ExecutionError("Integer division by zero.");
        }
    }

    static IntData intModTrunc(IBaseData a, IBaseData b) throws IVirtualMachine.ExecutionError
    {
        try
        {
            return intNew(intGet(a) % intGet(b));
        }
        catch (ArithmeticException e)
        {
            throw new VirtualMachine.ExecutionError("Integer remainder by zero.");
        }
    }

    static IntData intEQ(IBaseData a, IBaseData b)
    {
        return boolNew(intGet(a) == intGet(b));
    }

    static IntData intNE(IBaseData a, IBaseData b)
    {
        return boolNew(intGet(a) != intGet(b));
    }

    static IntData intGT(IBaseData a, IBaseData b)
    {
        return boolNew(intGet(a) > intGet(b));
    }

    static IntData intLT(IBaseData a, IBaseData b)
    {
        return boolNew(intGet(a) < intGet(b));
    }

    static IntData intGE(IBaseData a, IBaseData b)
    {
        return boolNew(intGet(a) >= intGet(b));
    }

    static IntData intLE(IBaseData a, IBaseData b)
    {
        return boolNew(intGet(a) <= intGet(b));
    }

    static class ComplData implements IBaseData
    {
        private int I;
        private int r;
        ComplData(int I, int r) { this.I= I; this.r = r; }
        int getReal() { return r; }
        int getImag() { return I; }
        public ComplData copy() { return complCopy(this); }
    }

    static ComplData complNew(int I, int r)
    {
        return new ComplData(I,r);
    }

    static int getImag(IBaseData a)
    {
        return ((ComplData)a).getImag();
    }

    static int getReal(IBaseData a)
    {
        return ((ComplData)a).getReal();
    }

    static ComplData complCopy(IBaseData a)
    {
        return complNew(getImag(a), getReal(a));
    }

    static ComplData complAdd(IBaseData a, IBaseData b)
    {
        return complNew(getImag(a) + getImag(b), getReal(a) + getReal(b));
    }

    static ComplData complSub(IBaseData a, IBaseData b)
    {
        return complNew(getImag(a) - getImag(b), getReal(a) - getReal(b));
    }

    static ComplData complMult(IBaseData a, IBaseData b)
    {
        return complNew(getReal(a)*getReal(b) - getImag(a)*getImag(b), getReal(a)*getImag(b) + getReal(b)*getImag(a));
    }

    static ComplData complModTrunc(IBaseData a, IBaseData b)
    {
        return complNew(0,0);
    }

    static IntData complEQ(IBaseData a, IBaseData b)
    {
        return boolNew(getReal(a) == getReal(b) && getImag(a) == getImag(b));
    }

    static IntData complNE(IBaseData a, IBaseData b)
    {
        return boolNew(getReal(a) != getReal(b) || getImag(a) != getImag(b));
    }

}
