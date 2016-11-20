package token;

import terminal.Terminals;
import types.Type;

public class DataType extends BaseToken {
	Type type;
	public DataType(Terminals terminal, Type type) {
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
