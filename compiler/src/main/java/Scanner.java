import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by tobi on 27/09/16.
 */
public class Scanner {

    public Scanner() {

    }

    public List<String> createTokens(String text) {
        List<String> wordList = new ArrayList<String>();

        String delim = " \n\r\t,.;"; //insert here all delimitators
        StringTokenizer st = new StringTokenizer(text, delim);
        while (st.hasMoreTokens()) {
            wordList.add(st.nextToken());
        }

        return wordList;
    }
}