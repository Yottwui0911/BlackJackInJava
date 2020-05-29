package enums;

public enum Suit {
	Spade("♠"),
	Hart("♥"),
	Diamond("♦"),
	Club("♣");

	Suit(String value) {
		this.value = value;
	}

	private String value;

	@Override
	public String toString() {
		return this.value;
	}
}
