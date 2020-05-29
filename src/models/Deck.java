package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import enums.Suit;

public class Deck {

	public Deck(Boolean hasJoker) throws Exception {
		this.Initialize(hasJoker);
	}

	private void Initialize(Boolean hasJoker) throws Exception {
		List<Card> cards = new ArrayList<Card>();

		for (Suit suit : Suit.values()) {
			for (Integer i = 1; i <= Card.CardMaxNumber; i++) {
				// それぞれのスートのカードを1～13まで用意する
				cards.add(new Card(i, suit));
			}
		}

		if (hasJoker) {
			// Jokerを山札に混ぜる
			cards.add(new Card());
		}

		this.cards = cards;
		this.shuffle();
	}

	public void shuffle() {
		Collections.shuffle(this.cards);
	}

	// 山札のカード
	private List<Card> cards = new ArrayList<Card>();

	public List<Card> getCards(){
		return this.cards;
	}

}
