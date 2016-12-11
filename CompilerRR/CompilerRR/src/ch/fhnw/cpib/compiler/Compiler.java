package ch.fhnw.cpib.compiler;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

import ch.fhnw.cpib.compiler.cst.CSTNode;
import ch.fhnw.cpib.compiler.cst.Pair;
import ch.fhnw.cpib.compiler.scanner.Scanner;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.parser.ProgramParser;

public class Compiler {
	

	public static void main(String[] args) {
		try {
			
			InputStreamReader isr = new InputStreamReader(new FileInputStream("res/test.iml"));
//			InputStreamReader isr = new InputStreamReader(new FileInputStream("res/IntDiv.iml"));
//			InputStreamReader isr = new InputStreamReader(new FileInputStream("res/code.iml"));
			
			
			Compiler compiler = new Compiler();
			//compiler.compile(new BufferedReader(isr));
			Scanner s = new Scanner();
			LinkedList<Token> list = s.scan(new BufferedReader(isr));
			System.out.println(list.toString());
			
			ProgramParser p = new ProgramParser(list);
			Pair<String, List<CSTNode>> cst = new Pair<>("Program", p.parse());
			
			System.out.println(cst.getSecond().toString());
			System.out.println(list.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}