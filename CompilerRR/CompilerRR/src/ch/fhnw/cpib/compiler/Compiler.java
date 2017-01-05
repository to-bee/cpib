package ch.fhnw.cpib.compiler;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;

import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn;
import ch.fhnw.cpib.compiler.context.Compilation;
import ch.fhnw.cpib.compiler.context.CompilerE;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.parser.ProgramParser;
import ch.fhnw.cpib.compiler.scanner.Scanner;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.vm.VirtualMachine;

public class Compiler {
	

	public static void main(String[] args) {
		try {
			
//			InputStreamReader isr = new InputStreamReader(new FileInputStream("res/IntDiv.iml"));
			InputStreamReader isr = new InputStreamReader(new FileInputStream("res/test.iml"));
			
			
			Compiler compiler = new Compiler();
			//compiler.compile(new BufferedReader(isr));
			Scanner s = new Scanner();
			LinkedList<Token> list = s.scan(new BufferedReader(isr));
			System.out.println(list.toString());
			
			ProgramParser p = new ProgramParser(list);
			IConcSyn.IProgram cst = p.parse();
			
			IAbsSyn.IProgram ast = cst.toAbs();
			
			CompilerE.COMPILER.compile(ast);
			
			VirtualMachine vm = new VirtualMachine(CompilerE.COMPILER.getCodeArray(), 1000);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
