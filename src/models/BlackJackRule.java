package models;

import java.util.List;
import java.util.stream.Collectors;

public class BlackJackRule {

	public static int BurstNum = 21;

	public static int getCardsNumberSum(List<Card> cards) {
		// 高々21なのでParseしても問題は起きないはず
		int val = (int)cards.stream().map(x->getBlackJackSum(x)).collect(Collectors.summarizingInt(x -> x)).getSum();

		int aceCount = (int)cards.stream().filter(x->x.isAce()).count();

		return calcConsiderAceValue(val, aceCount);
	}

	// BlackJackにおける数字の数え方
	public static int getBlackJackSum(Card card) {
		if (card == null || card.isJocker()) {
			return 0;
		}

		// 絵柄のカードは10として扱う
		if (card.isPictureCards()) {
			return 10;
		}

		// エースは11として扱う
		if (card.isAce()) {
			return 11;
		}

		// それ以外は自分の数字
		return card.getNumber();
	}

	// Aceの枚数により減算を行う
	private static int calcConsiderAceValue(int val,int count ) {
        for (int i = 0; i < count; i++)
        {
            if (val <= BurstNum)
            {
                break;
            }

            val = val - 10;
        }
        return val;
	}
}
