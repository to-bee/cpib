package datatypes;

import java.util.*;

/**
 * Created by tobi on 27/09/16.
 */
public enum Operators {
    UNDEFINED(""),
    NOT("!"),
    TIMES(""),
    DIV("/"),
    MOD("%"),
    PLUS("+"),
    MINUS("-"),
    EQ("=="),
    NE("!="),
    GT(">"),
    LT("<"),
    GE(">="),
    LE("<="),
    CAND("&&"),
    COR("||");

    String identifier;

    Operators(String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }

    /**
     * Sorts Operators by identifier length
     * Longer identifiers have to be parsed first
     * <= must be parsed before <
     * @return
     */
    public static List<Operators> getAll() {
        List<Operators> list = Arrays.asList(Operators.values());
        list.sort(new Comparator<Operators>() {
            @Override
            public int compare(Operators o1, Operators o2) {
                return o1.getIdentifier().length() - o2.getIdentifier().length();
            }
        });
        return list;
    }

    public static Operators getOperatorFromString(String value) {
        try {
            return getAll().stream().filter(o -> o.getIdentifier().equals(value.toLowerCase()) || o.toString().toLowerCase().equals(value.toLowerCase())).findFirst().get();
        }
        catch(Exception e) {
            return Operators.UNDEFINED;
        }
    }
}
