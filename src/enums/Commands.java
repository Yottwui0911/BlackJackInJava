package enums;

import java.util.Arrays;

public enum Commands {
	PlayerHand("hand"), DealerHand("dhand"), Help("help"), Draw("draw"), End("end");

	Commands(String value) {
		this.value = value;
	}

	private final String value;

	public static Commands of(final String value) {
		
		Commands[] hoge = values();
		return Arrays.stream(values())
				.filter(v -> v.value.equals(value))
				.findFirst()
				.orElseThrow(
						() -> new IllegalArgumentException());
	}

	@Override
	public String toString() {
		return this.value;
	}
}
