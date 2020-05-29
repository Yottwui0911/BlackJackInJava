package models;

import enums.Suit;

public class Card {

	// 引数無しのコンストラクタはJocker扱いする
	public Card() {
		this.isJoker = true;
	}

	public Card(Integer num, Suit suit) throws Exception {
		if (num > 13) {
			throw new Exception("13までやで");
		}
		this.isJoker = false;
		this.number = num;
		this.suit = suit;
	}

	// 各Suitでの最高値
	public static int CardMaxNumber = 13;

	@Override
	public String toString() {

		if (this.isJoker) {
			return "Joker";
		}
		return this.suit + "の" + this.number;
	}

	private Integer number;

	public Integer getNumber() {
		return this.number;
	}

	// Suit
	private Suit suit;

	public Suit getSuit() {
		return this.suit;
	}

	// Jokerかどうか
	private Boolean isJoker;

	public Boolean isJocker() {
		return this.isJoker;
	}

	// エースかどうか
	public Boolean isAce() {
		return this.number == 1;
	}

	// ジャックかどうか
	public Boolean isJack() {
		return this.number == 11;
	}

	// クイーンかどうか
	public Boolean isQueen() {
		return this.number == 12;
	}

	// キングかどうか
	public Boolean isKing() {
		return this.number == 13;
	}

	// 絵柄かどうか
	public Boolean isPictureCards() {
		return this.isJack() || this.isQueen() || this.isKing();
	}
}
