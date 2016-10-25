package scanner.token;

import types.Type;
import types.Terminals;

public class DataTypeToken extends BaseToken {
	Type type;
	public DataTypeToken(Terminals terminal, Type type) {
		super(terminal);
		this.type = type;	
	}
	
	Type getType() {
		return type;
	}
	
	@Override
	public String toString() {
		return "(" + super.toString() + "," + type.toString() + ")";
	}

}
