package models;

import java.util.List;
import java.util.stream.Collectors;

public class Dealer extends PlayerBase {

	Dealer() {
		this.name = "Dealer";
	}

	@Override
	public String pointStr(Boolean isEnd) {
		int point = isEnd ? this.getCardsNumberSum()
				: BlackJackRule.getBlackJackSum(this.hands.stream().findFirst().orElseGet(null));
		return this.name + "の得点: " + point;
	}

	@Override
	public String showHand(Boolean isEnd) {
		if (isEnd) {
			// すべてを開示
			List<String> hands = this.hands.stream().map(x -> x.toString()).collect(Collectors.toList());
			return this.pointStr(isEnd) + "\n" + String.join("\n", hands) + "\n";
		}

		String hand = this.hands.stream().map(x -> x.toString()).findFirst().orElseGet(null);
		return this.pointStr(isEnd) + "\n" + hand + "\n";
	}
}
