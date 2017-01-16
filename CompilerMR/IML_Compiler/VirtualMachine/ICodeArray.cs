namespace VirtualMachine
{
    public interface ICodeArray
    {    // for the COMPILER:
         // a CodeTooSmallError indicates that the code is too small
         // to hold the complete program
        void put(int loc, IInstructions instr);
        void resize();
        string toString();

        // for the VM:
        int getSize();
        IInstructions get(int loc);
        void fromString();
    }
}