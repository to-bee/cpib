package token;

import enums.Type;
import terminal.Terminals;

public class DataType extends BaseToken {
	private Type value;
	
	public DataType(Terminals terminal, Type value) {
		super(terminal);
		this.value = value;
	}
	
	public Type getValue() {
		return value;
	}
	
	@Override
	public String toString() {
		return "(" + super.toString() + "," + value.toString() + ")";
	}
	


}
