package model.datatypes;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by tobi on 27/09/16.
 * More informations about operators: https://docs.oracle.com/javase/tutorial/java/nutsandbolts/opsummary.html
 */
public enum Operators {
    UNDEFINED("UNDEFINED", Terminals.UNDEFINED),
    NOT("!", Terminals.UNARYOPR),
    DIV("/", Terminals.ARITHMOPR),
    MOD("%", Terminals.ARITHMOPR),
    PLUS("+", Terminals.ARITHMOPR, Terminals.UNARYOPR),
    MINUS("-", Terminals.RELOPR, Terminals.UNARYOPR),
    PLUSPLUS("++", Terminals.UNARYOPR),
    MINUSMINUS("--", Terminals.UNARYOPR),
    COMPLEMENT("!", Terminals.UNARYOPR),
    BECOMES(":=", Terminals.ASSIGNOPR),
    EQ("==", Terminals.RELOPR),
    NE("!=", Terminals.RELOPR),
    GT(">", Terminals.RELOPR),
    LT("<", Terminals.RELOPR),
    GE(">=", Terminals.RELOPR),
    LE("<=", Terminals.RELOPR),
    CAND("&&", Terminals.CONDOPR),
    COR("||", Terminals.CONDOPR),
    CONST("const", Terminals.CHANGEMODE),
    VAR("var", Terminals.CHANGEMODE),
    IN("in", Terminals.FLOWMODE),
    INOUT("inout", Terminals.FLOWMODE),
    OUT("out", Terminals.FLOWMODE),
    COPY("copy", Terminals.MECHMODE),
    REF("ref", Terminals.MECHMODE);

    private final List<Terminals> operatorTypes;
    String identifier;
    Operators(String identifier, Terminals... operatorTypes) {
        this.identifier = identifier;
        this.operatorTypes = Arrays.asList(operatorTypes);
    }

    public static Operators getOperatorFromString(String value) {
        try {
            return getAllSorted().stream().filter(o -> o.getIdentifier().equals(value.toLowerCase()) || o.toString().toLowerCase().equals(value.toLowerCase())).findFirst().get();
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
                return o2.getIdentifier().length() - o1.getIdentifier().length();
            }
        });
        return list;
    }

    public String getIdentifier() {
        return identifier;
    }

    public static Set<Terminals> getAllOperatorTerminals() {
        return getAllSorted().stream().flatMap(o -> o.getOperatorTypes().stream()).distinct().collect(Collectors.toSet());
    }

    public List<Terminals> getOperatorTypes() {
        return operatorTypes;
    }
}
