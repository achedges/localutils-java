package localutils.threading;

public enum ThreadWaitMultiplier {

	Fixed(1),
	Exponential(2);

	private final int _value;

	ThreadWaitMultiplier(int value) {
		this._value = value;
	}

	public int getValue() {
		return _value;
	}

}
