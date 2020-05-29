package models;

import java.util.ArrayList;
import java.util.List;

public abstract class PlayerBase {

	// Player名
	protected String name = "Player";

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// 手札
	protected List<Card> hands = new ArrayList<Card>();

	public List<Card> getHands() {
		return this.hands;
	}

	public void setHands(List<Card> cards) {
		this.hands = cards;
	}

	// 自分のポイントを公開
	abstract public String pointStr(Boolean isEnd);

	// 手札を文字列として公開
	abstract public String showHand(Boolean isEnd);

	// 手札の合計値を返却
	public int getCardsNumberSum() {
		return BlackJackRule.getCardsNumberSum(this.hands);
	}

	// 山札から手札を補充する
    public void draw(Deck deck, int count)
    {
        for (int i = 0; i < count; i++)
        {
            Card card = deck.getCards().stream().findFirst().orElseGet(null);
            this.hands.add(card);
            deck.getCards().remove(card);
        }
    }

    // 手札がBurstしているかどうか
    public Boolean isBurst() {
    	return this.getCardsNumberSum() > 21;
    }
}
