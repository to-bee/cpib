package types;

public enum Value {
	BoolVal(Type.BOOL),
	Int32Val(Type.INT32),
	Int64Val(Type.INT64);
	
	private final Type value;
	
	Value(Type value){
		this.value = value;
	}

	public Type getValue() {
		return this.value;
	}

	@Override
	public String toString() {
		return "(" + super.toString() + "," + value + ")";
	}
}
