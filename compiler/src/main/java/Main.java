import scanner.errors.ContextError;
import virtualmachineFS2015.IVirtualMachine;
import vm.Compilation;

/**
 * Created by tobi on 27/09/16.
 */
public class Main {
    public static void main(String[] argh) {

        String testString = "program ComplexTest()\n" +
                "global\n" +
                "fun addVar(param1:Compl) returns s:Int32\n" +
                "local\n" +
                "var bsp1:Compl;\n" +
                "var bsp2:Compl;\n" +
                "var bsp3:Int32;\n" +
                "var bsp4:Bool;\n" +
                "var result:Compl\n" +
                "do\n" +
                "bsp1 := 4+I*5;\n" +
                "bsp2 := 4-I*5;\n" +
                "bsp3 := 3;\n" +
                "bsp4 := true;\n" +
                "result := bsp1 + bsp2\n" +
                "endfun\n" +
                "do\n" +
                "call addVar()\n" +
                "endprogram";
        try {
            Compilation comp = new Compilation(1000, 1000);
            comp.generateCode(testString);
        } catch (IVirtualMachine.ExecutionError e) {
            System.out.println("ExecutionError: " + e.getMessage());
        } catch (ContextError e) {
            System.out.println("ContextError: " + e.getMessage());
        }
    }
}
