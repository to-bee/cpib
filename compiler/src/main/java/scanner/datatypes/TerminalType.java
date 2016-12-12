package scanner.datatypes;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by tobi on 27/09/16.
 * More informations about operators: https://docs.oracle.com/javase/tutorial/java/nutsandbolts/opsummary.html
 */
public enum TerminalType {
    CHANGEMODE("CHANGEMODE"),
    FLOWMODE("FLOWMODE"),
    MECHMODE("MECHMODE"),
    /**
     * Relational Operator
     * (==, !=, >, >=, <, <=)
     */
    RELOPR("RELOPR"),
    /**
     * Conditional Operator
     * (&&, ||, ?:)
     */
    CONDOPR("CONDOPR"),
    /**
     * Arithmetic Operator
     * (+, -, *, /, %)
     */
    ARITHMOPR("ARITHMOPR"),
    /**
     * Assignment Operator
     * (=)
     */
    ASSIGNOPR("ASSIGNOPR"),
    /**
     * Unary Operator
     * (+, -, ++, --, !)
     */
    UNARYOPR("UNARYOPR");

    private final List<Terminal> operatorTypes;
    String identifier;

    TerminalType(String identifier, Terminal... operatorTypes) {
        this.identifier = identifier;
        this.operatorTypes = Arrays.asList(operatorTypes);
    }

    public static TerminalType getOperatorFromString(String value) {
        try {
            return getAllSorted().stream().filter(o -> o.getIdentifier().equals(value.toLowerCase()) || o.toString().toLowerCase().equals(value.toLowerCase())).findFirst().get();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Sorts TerminalType by identifier length
     * Longer identifiers have to be parsed first
     * <= must be parsed before <
     *
     * @return
     */
    public static List<TerminalType> getAllSorted() {
        List<TerminalType> list = Arrays.asList(TerminalType.values());
        list.sort(new Comparator<TerminalType>() {
            @Override
            public int compare(TerminalType o1, TerminalType o2) {
                return o2.getIdentifier().length() - o1.getIdentifier().length();
            }
        });
        return list;
    }

    public String getIdentifier() {
        return identifier;
    }

    public static Set<Terminal> getAllOperatorTerminals() {
        return getAllSorted().stream().flatMap(o -> o.getOperatorTypes().stream()).distinct().collect(Collectors.toSet());
    }

    public List<Terminal> getOperatorTypes() {
        return operatorTypes;
    }
}
