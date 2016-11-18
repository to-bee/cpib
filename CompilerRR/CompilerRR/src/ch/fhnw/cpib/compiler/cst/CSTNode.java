package ch.fhnw.cpib.compiler.cst;

import java.util.List;

import ch.fhnw.cpib.compiler.scanner.Token;

public class CSTNode {
	private Token token = null;
	private Pair<String, List<CSTNode>> nts_cstNodeList_Pair = null;
	
	
	public CSTNode(Token token) {
		this.token = token;
	}
	
	public CSTNode(String nts, List<CSTNode> cstNodeList) {
		this.nts_cstNodeList_Pair = new Pair<>(nts, cstNodeList);
	}
	
	public Token getToken() {
		return token;
	}
	
	public Pair<String, List<CSTNode>> getCstNodeList() {
		return nts_cstNodeList_Pair;
	}
	
	@Override
	public String toString() {
		if(token != null) return token.toString();
		else {
			String s = nts_cstNodeList_Pair.getFirst() + "\n";
			for(CSTNode n : nts_cstNodeList_Pair.getSecond()) {
				s += n.toString() + " - ";
			}
			return s;
		}
	}
}
