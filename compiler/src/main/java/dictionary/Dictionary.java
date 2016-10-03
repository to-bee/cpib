package dictionary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * To check if a lexeme is a terminal without attribute
 * @author Roman
 *
 */
public class Dictionary {
	
	private final Set<String> DICTIONARY = new HashSet<>(Arrays.asList(new String[]{"while", "sentinel"}));
	
	public boolean isLexemeWithoutAttribute(String toCheck){
		return DICTIONARY.contains(toCheck.toLowerCase());
	}
}
