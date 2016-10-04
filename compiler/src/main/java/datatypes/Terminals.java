package datatypes;

import java.util.Arrays;

/**
 * Created by tobi on 27/09/16.
 * More informations about operators: https://docs.oracle.com/javase/tutorial/java/nutsandbolts/opsummary.html
 */
public enum Terminals {
    UNDEFINED(null, "", ""),
    DO("DO", "", ", "),
    WHILE("WHILE", null, ", "),
    ENDWHILE("ENDWHILE", null, ", "),
    START_ROUTINE(null, "[", null),
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
}
