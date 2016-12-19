package scanner.datatypes;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Created by tobi on 27/09/16.
 * More informations about operators: https://docs.oracle.com/javase/tutorial/java/nutsandbolts/opsummary.html
 * TODO: Extend this with func, proc, ..
 * TODO: Only terminals according to iml_v2
 */
public enum Terminal {
    UNDEFINED("UNDEFINED", null),
    DO("DO", null),
    INT("int", null),
    WHILE("WHILE", null),
    ENDWHILE("ENDWHILE", null),
    PROGRAM("PROGRAM", null),
    SENTINEL("SENTINEL", null),
    IDENT("IDENT", null),
    LITERAL("LITERAL", null),
    LPAREN("(", null),
    RPAREN(")", null),
    LSBRACKET("[", null),
    RSBRACKET("]", null),
    SEMICOLON(";", null), // ;
    COLON(":", null), // :
    COMMA(",", null),
    GLOBAL("GLOBAL", null),
    PROC("PROC", null),
    FUN("FUN", null),
    RETURNS("RETURNS", null),
    ENDFUN("ENDFUN", null),
    ENDPROC("ENDPROC", null),
    LOCAL("LOCAL", null),
    ENDPROGRAM("ENDPROGRAM", null),
    DEBUGOUT("DEBUGOUT", null),
    DEBUGIN("DEBUGIN", null),
    CALL("CALL", null),
    IF("IF", null),
    REAL("REAL", null),
    IMAG("IMAG", null),
    ADDOPR("+", TerminalType.ARITHMOPR),
    MINOPR("-", TerminalType.ARITHMOPR),
    MULTOPR("*", TerminalType.ARITHMOPR),
    DIVOPR("/", TerminalType.ARITHMOPR),
    SKIP("SKIP", null),
    THEN("THEN", null),
    ELSE("ELSE", null),
    ENDIF("ENDIF", null),
    INIT("INIT", null),
    NOT("!", TerminalType.UNARYOPR),
    DIV("/", TerminalType.ARITHMOPR),
    MOD("%", TerminalType.ARITHMOPR),
    COMPLEMENT("!", TerminalType.UNARYOPR),
    BECOMES(":=", TerminalType.ASSIGNOPR),
    EQ("==", TerminalType.RELOPR),
    NE("!=", TerminalType.RELOPR),
    GT(">", TerminalType.RELOPR),
    LT("<", TerminalType.RELOPR),
    GE(">=", TerminalType.RELOPR),
    LE("<=", TerminalType.RELOPR),
    CAND("&&", TerminalType.BOOLOPR),
    COR("||", TerminalType.BOOLOPR),
    CONST("const", TerminalType.CHANGEMODE),
    VAR("var", TerminalType.CHANGEMODE),
    IN("in", TerminalType.FLOWMODE),
    INOUT("inout", TerminalType.FLOWMODE),
    OUT("out", TerminalType.FLOWMODE),
    COPY("copy", TerminalType.MECHMODE),
    REF("ref", TerminalType.MECHMODE),
    INT32("Int32", TerminalType.TYPE),
    BOOL("Bool", TerminalType.TYPE),
    TrueOPR("true", TerminalType.BOOLOPR),
    FalseOPR("false", TerminalType.BOOLOPR),
    COMPL("Compl", TerminalType.TYPE),
    IMAGINARY_PART("I", TerminalType.TYPE);

    private final String value;
    private final TerminalType type;

    Terminal(String value, TerminalType type) {
        this.value = value;
        this.type = type;
    }

    public static Terminal getTerminalFromString(String value) {
        try {
            return Arrays.asList(Terminal.values()).stream().filter(t -> t.getValue() != null && t.getValue().toLowerCase().equals(value.toLowerCase())).findFirst().get();
        } catch (Exception e) {
            return UNDEFINED;
        }
    }

    /**
     * Sorts Terminal by identifier length
     * Longer identifiers have to be parsed first
     * <= must be parsed before <
     *
     * @return
     */
    public static List<Terminal> getAllSorted() {
        List<Terminal> list = Arrays.asList(Terminal.values());
        list.sort(new Comparator<Terminal>() {
            @Override
            public int compare(Terminal o1, Terminal o2) {
                int value1 = o1.getValue() != null ? o1.getValue().length() : 0;
                int value2 = o2.getValue() != null ? o2.getValue().length() : 0;
                return value1 - value2;
            }
        });
        return list;
    }

    public String getValue() {
        return value;
    }

    public TerminalType getType() {
        return type;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getValue());
        if(getType() != null) {
            sb.append(String.format(" [%s]", type.getIdentifier()));
        }
        return sb.toString();
    }
}
