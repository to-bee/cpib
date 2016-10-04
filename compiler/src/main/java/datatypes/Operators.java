package datatypes;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Created by tobi on 27/09/16.
 */
public enum Operators {
    UNDEFINED("", Terminals.RELOPR),
    NOT("!", Terminals.UNARYOPR),
    TIMES("", Terminals.UNDEFINED), // ???
    DIV("/", Terminals.ARITHMOPR),
    MOD("%", Terminals.ARITHMOPR),
    PLUS("+", Terminals.ARITHMOPR),
    MINUS("-", Terminals.RELOPR),
    BECOMES(":=", Terminals.ASSIGNOPR),
    EQ("==", Terminals.RELOPR),
    NE("!=", Terminals.RELOPR),
    GT(">", Terminals.RELOPR),
    LT("<", Terminals.RELOPR),
    GE(">=", Terminals.RELOPR),
    LE("<=", Terminals.RELOPR),
    CAND("&&", Terminals.CONDOPR),
    COR("||", Terminals.CONDOPR),;

    private final Terminals operatorType;
    String identifier;
    Operators(String identifier, Terminals operatorType) {
        this.identifier = identifier;
        this.operatorType = operatorType;
    }

    public static Operators getOperatorFromString(String value) {
        try {
            return getAllSorted().stream().filter(o -> o.getIdentifier().contains(value.toLowerCase()) || o.toString().toLowerCase().contains(value.toLowerCase())).findFirst().get();
        } catch (Exception e) {
            return Operators.UNDEFINED;
        }
    }

    /**
     * Sorts Operators by identifier length
     * Longer identifiers have to be parsed first
     * <= must be parsed before <
     *
     * @return
     */
    public static List<Operators> getAllSorted() {
        List<Operators> list = Arrays.asList(Operators.values());
        list.sort(new Comparator<Operators>() {
            @Override
            public int compare(Operators o1, Operators o2) {
                return o1.getIdentifier().length() - o2.getIdentifier().length();
            }
        });
        return list;
    }

    public String getIdentifier() {
        return identifier;
    }

    public Terminals getOperatorType() {
        return operatorType;
    }
}
