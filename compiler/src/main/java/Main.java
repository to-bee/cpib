import virtualmachineFS2015.IVirtualMachine;
import vm.Compilation;

/**
 * Created by tobi on 27/09/16.
 */
public class Main {
    public static void main(String[] argh) {

        System.out.println("Hello");
        try {
            Compilation comp = new Compilation(1000, 1000);
            comp.generateCode();
        } catch (IVirtualMachine.ExecutionError e) {
            System.out.println("ExecutionError: " + e.getMessage());
        }
    }
}
