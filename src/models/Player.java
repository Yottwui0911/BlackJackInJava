package models;

import java.util.List;
import java.util.stream.Collectors;

public class Player extends PlayerBase {

	Player() {
		this.name = "Player";
	}

	Player(String playerName) {
		this.name = playerName;
	}

	@Override
	public String pointStr(Boolean isEnd) {
		return this.name + "の得点: " + getCardsNumberSum();
	}

	@Override
	public String showHand(Boolean isEnd) {
		List<String> hands = this.hands.stream().map(x -> x.toString()).collect(Collectors.toList());
		return this.pointStr(isEnd) + "\n" +  String.join("\n", hands)+ "\n";
	}
}
