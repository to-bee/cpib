package scanner.token;

import types.Datatypes;
import types.Terminals;

public class DataTypeToken extends BaseToken {
	Datatypes type;
	public DataTypeToken(Terminals terminal, Datatypes type) {
		super(terminal);
		this.type = type;	
	}
	
	Datatypes getType() {
		return type;
	}
	
	@Override
	public String toString() {
		return "(" + super.toString() + "," + type.toString() + ")";
	}

}
