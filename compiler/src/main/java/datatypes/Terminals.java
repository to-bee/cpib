package datatypes;

import java.util.Arrays;
import java.util.List;

/**
 * Created by tobi on 27/09/16.
 * More informations about operators: https://docs.oracle.com/javase/tutorial/java/nutsandbolts/opsummary.html
 */
public enum Terminals {
    UNDEFINED("", ""),
    DO("", ""),
    WHILE("[", null),
    ENDWHILE(null, "]"),
    SENTINEL(null, "]"),
    IDENT("(", ")"),
    LITERAL("(", ")"),
    /**
     * Relational Operator
     * (==, !=, >, >=, <, <=)
     */
    RELOPR("(", ")"),
    /**
     * Conditional Operator
     * (&&, ||, ?:)
     */
    CONDOPR("(", ")"),
    /**
     * Arithmetic Operator
     * (+, -, *, /, %)
     */
    ARITHMOPR("(", ")"),
    /**
     * Assignment Operator
     * (=)
     */
    ASSIGNOPR("(", ")"),
    /**
     * Unary Operator
     * (+, -, ++, --, !)
     */
    UNARYOPR("(", ")");

    private final String suffix;
    private final String praeffix;

    Terminals(String praeffix, String suffix) {
        this.praeffix = praeffix;
        this.suffix = suffix;
    }

    public String getSuffix() {
        return suffix;
    }

    public String getPraeffix() {
        return praeffix;
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
