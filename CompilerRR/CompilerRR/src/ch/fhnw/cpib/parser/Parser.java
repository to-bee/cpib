package ch.fhnw.cpib.parser;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import ch.fhnw.cpib.compiler.scanner.Token;

public class Parser {
	
	private LinkedList<Token> tokenlist;
	private Set<String> nonTerminals = new HashSet<String>();
	
	public Parser(LinkedList<Token> tokenlist) {
		this.tokenlist = tokenlist;
	}
	
	
	public void parse(){
		
		
	}
	
	public void consume(){
		
	}
	
	
	
	
}
