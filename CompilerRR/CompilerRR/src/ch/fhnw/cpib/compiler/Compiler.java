package ch.fhnw.cpib.compiler;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;

import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn;
import ch.fhnw.cpib.compiler.context.CompilerE;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.parser.ProgramParser;
import ch.fhnw.cpib.compiler.scanner.Scanner;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.vm.VirtualMachine;

public class Compiler {
	

	public static void main(String[] args) {
		try {
			
//			InputStreamReader isr = new InputStreamReader(new FileInputStream("res/1-Basic-Switch.iml"));
//			InputStreamReader isr = new InputStreamReader(new FileInputStream("res/2-Switch-Without-Default.iml"));
//			InputStreamReader isr = new InputStreamReader(new FileInputStream("res/3-Switchception.iml"));
			InputStreamReader isr = new InputStreamReader(new FileInputStream("res/4-Errors.iml"));
//			InputStreamReader isr = new InputStreamReader(new FileInputStream("res/1-Basic-Switch.iml"));
			
			
			Compiler compiler = new Compiler();
			//compiler.compile(new BufferedReader(isr));
			Scanner s = new Scanner();
			LinkedList<Token> list = s.scan(new BufferedReader(isr));

			System.out.println("-------------------------");
			System.out.println("       TOKENLIST         ");
			System.out.println("-------------------------");
			System.out.println(list.toString());
			
			ProgramParser p = new ProgramParser(list);
			IConcSyn.IProgram cst = p.parse();
			IAbsSyn.IProgram ast = cst.toAbs();
			System.out.println("-------------------------");
			System.out.println("          AST            ");
			System.out.println("-------------------------");
			ast.print("");
			
			System.out.println("-------------------------");
			System.out.println("         Code            ");
			System.out.println("-------------------------");
			CompilerE.COMPILER.compile(ast);
			CompilerE.COMPILER.getCodeArray().resize();
			
			for(int i = 0; i < CompilerE.COMPILER.getCodeArray().getSize(); i++){
				System.out.println(CompilerE.COMPILER.getCodeArray().get(i));
			}
			
			
			System.out.println("-------------------------");
			System.out.println("     Program Output      ");
			System.out.println("-------------------------");
			VirtualMachine vm = new VirtualMachine(CompilerE.COMPILER.getCodeArray(), 1000);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
