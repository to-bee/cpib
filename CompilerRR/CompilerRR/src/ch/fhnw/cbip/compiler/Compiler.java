package ch.fhnw.cbip.compiler;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;

import ch.fhnw.cbip.compiler.scanner.Scanner;
import ch.fhnw.cbip.compiler.scanner.Token;


public class Compiler {
	
	

	public static void main(String[] args) {
		try {
			InputStreamReader isr = new InputStreamReader(new FileInputStream("res/code.iml"));
			Compiler compiler = new Compiler();
			//compiler.compile(new BufferedReader(isr));
			Scanner s = new Scanner();
			LinkedList<Token> list = s.scan(new BufferedReader(isr));
			
			System.out.println(list.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
