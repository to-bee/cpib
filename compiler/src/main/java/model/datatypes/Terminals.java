package model.datatypes;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Created by tobi on 27/09/16.
 * More informations about operators: https://docs.oracle.com/javase/tutorial/java/nutsandbolts/opsummary.html
 * TODO: Extend this with func, proc, ..
 * TODO: Only terminals according to iml_v2
 */
public enum Terminals {
    UNDEFINED(null, "", ""),
    DO("DO", "", ", "),
    WHILE("WHILE", null, ", "),
    ENDWHILE("ENDWHILE", null, ", "),
    PROGRAM(null, "[", null),
    SENTINEL("SENTINEL", null, "]"),
    IDENT("IDENT", "(", "), "),
    LITERAL("LITERAL", "(", "), "),
    /**
     * Relational Operator
     * (==, !=, >, >=, <, <=)
     */
    RELOPR("RELOPR", "(", "), "),
    /**
     * Conditional Operator
     * (&&, ||, ?:)
     */
    CONDOPR("CONDOPR", "(", "), "),
    /**
     * Arithmetic Operator
     * (+, -, *, /, %)
     */
    ARITHMOPR("ARITHMOPR", "(", "), "),
    /**
     * Assignment Operator
     * (=)
     */
    ASSIGNOPR("ASSIGNOPR", "(", "), "),
    /**
     * Unary Operator
     * (+, -, ++, --, !)
     */
    UNARYOPR("UNARYOPR", "(", "), ");

    private final String postfix;
    private final String prefix;
    private final String value;

    Terminals(String value, String prefix, String postfix) {
        this.value = value;
        this.prefix = prefix;
        this.postfix = postfix;
    }

    public String getPostfix() {
        return postfix;
    }

    public String getValue() {
        return value;
    }

    public String getPrefix() {
        return prefix;
    }

    public static Terminals getTerminalFromString(String value) {
        try {
            return Arrays.asList(Terminals.values()).stream().filter(t -> t.toString().toLowerCase().equals(value.toLowerCase())).findFirst().get();
        }
        catch(Exception e) {
            return Terminals.UNDEFINED;
        }
    }

    /**
     * Sorts Terminals by identifier length
     * Longer identifiers have to be parsed first
     * <= must be parsed before <
     *
     * @return
     */
    public static List<Terminals> getAllSorted() {
        List<Terminals> list = Arrays.asList(Terminals.values());
        list.sort(new Comparator<Terminals>() {
            @Override
            public int compare(Terminals o1, Terminals o2) {
                int value1 = o1.getValue() != null ? o1.getValue().length() : 0;
                int value2 = o2.getValue() != null ? o2.getValue().length() : 0;
                return value1 - value2;
            }
        });
        return list;
    }
}
