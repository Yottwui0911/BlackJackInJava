package models;

import enums.Commands;

public class BlackJackController {

	public BlackJackController(String playerName) {
		dealer = new Dealer();
		player = new Player(playerName);
		try {
			deck = new Deck(false);
		} catch (Exception e) {
			System.out.println(e);
		}

		this.Initialize();
	}

	private Dealer dealer;
	private Player player;

	private PlayerBase winner;

	public void WinningMsg() {
		if(this.getWinner() == null) {
			System.out.println("引き分けです");
			return;
		}


		System.out.println(this.getWinner().name + "の勝利！");
	}

	public PlayerBase getWinner() {
		return this.winner;
	}

	private void setWinner(PlayerBase actor) {
		this.winner = actor;
		this.setIsFinish(true);
	}

	private Deck deck;

	// 初めにカードを引く枚数
	private int firstCard = 2;

	// 初期化処理
	private void Initialize() {
		this.dealer.draw(this.deck, firstCard);

		this.player.draw(this.deck, firstCard);
	}

	// 終了したかどうか
	private Boolean isFinish = false;

	public Boolean isFinish() {
		return this.isFinish;
	}

	private void setIsFinish(Boolean isFinish) {
		this.isFinish = isFinish;
	}

	// 外から操作できるコマンド
	public void Command(String value) {
		try {
			Commands command = Commands.of(value);
			action(command);
		} catch (IllegalArgumentException e) {
			System.out.println(value + "は存在しないコマンドです。\n使えるコマンドはhelpからご確認ください。");
		}
	}

	private void action(Commands command) {
		switch (command) {
		case PlayerHand:
			this.showPlayerHand();
			return;
		case DealerHand:
			this.showDealerHand();
			return;
		case Help:
			this.doHelp();
			return;
		case Draw:
			this.doDraw();
			return;
		case End:
			this.doEnd();
			return;
		default:
			System.out.println("登録されていなコマンドが入力されました。\n使えるコマンドはhelpからご確認ください。");
			return;

		}
	}

	// Hand処理
	private void showPlayerHand() {
		System.out.println(this.player.showHand(false));
	}

	// DHand処理
	private void showDealerHand() {
		System.out.println(this.dealer.showHand(false));
	}

	// Help処理
	private void doHelp() {
		String message = "■使い方\n" +
				"以下のキーを入力してください。\n" +
				"・" + Commands.PlayerHand + ":自分の手札を確認できます\n" +
				"・" + Commands.DealerHand + ":ディーラーの手札を確認できます\n" +
				"・" + Commands.Draw + ":手札を山札から引きます\n" +
				"・" + Commands.End + ":終了して、ディーラーと勝負します\n";
		System.out.println(message);
	}

	private String drawMsg(PlayerBase actor, Card card) {
		return actor.getName() + "が" + card + "を引きました\n";
	}

	private String burstMsg(PlayerBase actor) {
		return actor.getName() + "がBurstしました。\n" + this.player.pointStr(true) + "\n" + this.dealer.pointStr(true)
				+ "\n";
	}

	// Draw処理
	private void doDraw() {
		// プレイヤーが一枚ドロー
		this.player.draw(this.deck, 1);
		String msg = drawMsg(this.player, this.player.getHands().get(this.player.getHands().size() - 1));

		// ドローしたカード表示
		System.out.println(msg + this.player.pointStr(false) + "\n");
		if (this.player.getCardsNumberSum() <= BlackJackRule.BurstNum) {
			return;
		}

		// バーストしたらディーラーの勝利
		this.setWinner(this.dealer);
		System.out.println(this.burstMsg(this.player));
	}

	// End処理

	// ディーラーが引く数字の閾値
	private final int threshold = 17;

	private void doEnd() {
		StringBuilder msg = new StringBuilder();
		while (this.dealer.getCardsNumberSum() < threshold) {
			this.dealer.draw(this.deck, 1);
			msg.append(drawMsg(this.dealer, this.dealer.getHands().get(this.dealer.getHands().size() - 1)));
		}

		if (this.dealer.getCardsNumberSum() > BlackJackRule.BurstNum) {
			// バーストしたらプレイヤーの勝利
			this.setWinner(this.player);
			msg.append(this.burstMsg(this.dealer));
			System.out.println(msg.toString());
			return;
		}

		// どちらもバーストしていないので、手札の数値で勝負
		if (this.player.getCardsNumberSum() == this.dealer.getCardsNumberSum()) {
			this.setIsFinish(true);
			System.out.println(msg.toString());
			return;
		}

		if (this.player.getCardsNumberSum() > this.dealer.getCardsNumberSum()) {
			this.setWinner(this.player);
		} else {
			this.setWinner(this.dealer);
		}

		msg.append(this.player.pointStr(true) + "\n" + this.dealer.pointStr(true) + "\n");
		System.out.println(msg.toString());
		return;
	}
}
