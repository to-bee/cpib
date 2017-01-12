// Virtual Machine Java 2015, V01
// Edgar F.A. Lederer, FHNW and Uni Basel, 2015

package ch.fhnw.cpib.compiler.vm;

import ch.fhnw.cpib.compiler.vm.IVirtualMachine.ExecutionError;

// idea: the compiler should not need a reference to the VM object

public interface IInstructions {

    // non-executable form of instructions
    interface IInstr {
        IExecInstr toExecInstr(VirtualMachine vm);
    }

    // executable form of instructions
    interface IExecInstr extends IInstr {
        void execute() throws ExecutionError;
    }

    // stop instruction
    class Stop implements IInstr {
        @Override
		public String toString() { return "Stop"; }
        @Override
		public IExecInstr toExecInstr(VirtualMachine vm) {
            return vm.new StopExec();
        }
    }

    // stack instruction
    class Dup implements IInstr {
        @Override
		public String toString() { return "Dup"; }
        @Override
		public IExecInstr toExecInstr(VirtualMachine vm) {
            return vm.new DupExec();
        }
    }

    // routine operations

    class AllocBlock implements IInstr {
        protected int size;
        public AllocBlock(int size) { this.size= size; }
        @Override
		public String toString() { return "AllocBlock(" + size + ")"; }
        @Override
		public IExecInstr toExecInstr(VirtualMachine vm) {
            return vm.new AllocBlockExec(size);
        }
    }

    class AllocStack implements IInstr {
        protected int maxSize;
        public AllocStack(int maxSize) { this.maxSize= maxSize; }
        @Override
		public String toString() { return "AllocStack(" + maxSize + ")"; }
        @Override
		public IExecInstr toExecInstr(VirtualMachine vm) {
            return vm.new AllocStackExec(maxSize);
        }
    }

    class Call implements IInstr {
        protected int routAddress;
        public Call(int routAddress) { this.routAddress= routAddress; }
        @Override
		public String toString() { return "Call(" + routAddress + ")"; }
        @Override
		public IExecInstr toExecInstr(VirtualMachine vm) {
            return vm.new CallExec(routAddress);
        }
    }
    
    class Enter implements IInstr {
    	protected final int size;
    	protected final int extreme;

        public Enter(final int size, final int extreme) {
          this.size = size;
          this.extreme = extreme;
        }
    	
		@Override
		public IExecInstr toExecInstr(VirtualMachine vm) {
			return vm.new EnterExec(size, extreme);
		}
    	
    }

    class Return implements IInstr {
        protected int size;
        public Return(int size) { this.size= size; }
        @Override
		public String toString() { return "Return(" + size + ")"; }
        @Override
		public IExecInstr toExecInstr(VirtualMachine vm) {
            return vm.new ReturnExec(size);
        }
    }

    // load immediate value (value -> stack)
    class LoadImInt implements IInstr {
        protected int value;
        public LoadImInt(int value) { this.value= value; }
        @Override
		public String toString() { return "LoadImInt(" + value + ")"; }
        @Override
		public IExecInstr toExecInstr(VirtualMachine vm) {
            return vm.new LoadImIntExec(value);
        }
    }

    // load address relative to frame pointer (address -> stack)
    class LoadAddrRel implements IInstr {
        protected int relAddress;
        public LoadAddrRel(int relAddress) { this.relAddress= relAddress; }
        @Override
		public String toString() { return "LoadAddrRel(" + relAddress + ")"; }
        @Override
		public IExecInstr toExecInstr(VirtualMachine vm) {
            return vm.new LoadAddrRelExec(relAddress);
        }
    }

    // load instruction with address on stack
    // load (inside stack -> top of stack) operation
    class Deref implements IInstr {
        @Override
		public String toString() { return "Deref"; }
        @Override
		public IExecInstr toExecInstr(VirtualMachine vm) {
            return vm.new DerefExec();
        }
    }

    // store instruction with address on stack
    // store (top of stack -> inside stack) operation
    class Store implements IInstr {
        @Override
		public String toString() { return "Store"; }
        @Override
		public IExecInstr toExecInstr(VirtualMachine vm) {
            return vm.new StoreExec();
        }
    }

    // monadic instructions

    class NegInt implements IInstr {
        @Override
		public String toString() { return "NegInt"; }
        @Override
		public IExecInstr toExecInstr(VirtualMachine vm) {
            return vm.new NegIntExec();
        }
    }

    // dyadic instructions

    class AddInt implements IInstr {
        @Override
		public String toString() { return "AddInt"; }
        @Override
		public IExecInstr toExecInstr(VirtualMachine vm) {
            return vm.new AddIntExec();
        }
    }

    class SubInt implements IInstr {
        @Override
		public String toString() { return "SubInt"; }
        @Override
		public IExecInstr toExecInstr(VirtualMachine vm) {
            return vm.new SubIntExec();
        }
    }

    class MultInt implements IInstr {
        @Override
		public String toString() { return "MultInt"; }
        @Override
		public IExecInstr toExecInstr(VirtualMachine vm) {
            return vm.new MultIntExec();
        }
    }

    class DivTruncInt implements IInstr {
        @Override
		public String toString() { return "DivTruncInt"; }
        @Override
		public IExecInstr toExecInstr(VirtualMachine vm) {
            return vm.new DivTruncIntExec();
        }
    }

    class ModTruncInt implements IInstr {
        @Override
		public String toString() { return "ModTruncInt"; }
        @Override
		public IExecInstr toExecInstr(VirtualMachine vm) {
            return vm.new ModTruncIntExec();
        }
    }

    class EqInt implements IInstr {
        @Override
		public String toString() { return "EqInt"; }
        @Override
		public IExecInstr toExecInstr(VirtualMachine vm) {
            return vm.new EqIntExec();
        }
    }

    class NeInt implements IInstr {
        @Override
		public String toString() { return "NeInt"; }
        @Override
		public IExecInstr toExecInstr(VirtualMachine vm) {
            return vm.new NeIntExec();
        }
    }

    class GtInt implements IInstr {
        @Override
		public String toString() { return "GtInt"; }
        @Override
		public IExecInstr toExecInstr(VirtualMachine vm) {
            return vm.new GtIntExec();
        }
    }

    class LtInt implements IInstr {
        @Override
		public String toString() { return "LtInt"; }
        @Override
		public IExecInstr toExecInstr(VirtualMachine vm) {
            return vm.new LtIntExec();
        }
    }

    class GeInt implements IInstr {
        @Override
		public String toString() { return "GeInt"; }
        @Override
		public IExecInstr toExecInstr(VirtualMachine vm) {
            return vm.new GeIntExec();
        }
    }

    class LeInt implements IInstr {
        @Override
		public String toString() { return "LeInt"; }
        @Override
		public IExecInstr toExecInstr(VirtualMachine vm) {
            return vm.new LeIntExec();
        }
    }

    // jump instructions

    class UncondJump implements IInstr {
        protected int jumpAddr;
        public UncondJump(int jumpAddr) { this.jumpAddr= jumpAddr; }
        @Override
		public String toString() { return "UncondJump(" + jumpAddr + ")"; }
        @Override
		public IExecInstr toExecInstr(VirtualMachine vm) {
            return vm.new UncondJumpExec(jumpAddr);
        }
    }

    class CondJump implements IInstr {
        protected int jumpAddr;
        public CondJump(int jumpAddr) { this.jumpAddr= jumpAddr; }
        @Override
		public String toString() { return "CondJump(" + jumpAddr + ")"; }
        @Override
		public IExecInstr toExecInstr(VirtualMachine vm) {
            return vm.new CondJumpExec(jumpAddr);
        }
    }

    // input (input -> stack) and output (stack -> output) instructions

    class InputBool implements IInstr {
        protected String indicator;
        public InputBool(String indicator) { this.indicator= indicator; }
        @Override
		public String toString() { return "InputBool(\"" + indicator + "\")"; }
        @Override
		public IExecInstr toExecInstr(VirtualMachine vm) {
            return vm.new InputBoolExec(indicator);
        }
    }

    class InputInt implements IInstr {
        protected String indicator;
        public InputInt(String indicator) { this.indicator= indicator; }
        @Override
		public String toString() { return "InputInt(\"" + indicator + "\")"; }
        @Override
		public IExecInstr toExecInstr(VirtualMachine vm) {
            return vm.new InputIntExec(indicator);
        }
    }

    class OutputBool implements IInstr {
        protected String indicator;
        public OutputBool(String indicator) { this.indicator= indicator; }
        @Override
		public String toString() { return "OutputBool(\"" + indicator + "\")"; }
        @Override
		public IExecInstr toExecInstr(VirtualMachine vm) {
            return vm.new OutputBoolExec(indicator);
        }
    }

    class OutputInt implements IInstr {
        protected String indicator;
        public OutputInt(String indicator) { this.indicator= indicator; }
        @Override
		public String toString() { return "OutputInt(\"" + indicator + "\")"; }
        @Override
		public IExecInstr toExecInstr(VirtualMachine vm) {
            return vm.new OutputIntExec(indicator);
        }
    }
}
