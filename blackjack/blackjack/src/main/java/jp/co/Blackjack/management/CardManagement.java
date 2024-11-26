package jp.co.Blackjack.management;

import java.util.ArrayList;
import java.util.List;

import jp.co.Blackjack.modl.Trump;

public class CardManagement {
	private List<Trump> useDeck;
	private List<Trump> dealerHand;
	private List<Trump> playerHand;

	/**
	 * コンストラクタ
	 */
	public CardManagement() {
		this.useDeck = Trump.createAndShuffleDecks();
		this.dealerHand = new ArrayList<>();
		this.playerHand = new ArrayList<>();
	}

	/**
	 * デッキを新しいものに変える
	 */
	public void resetDeck() {
		this.useDeck = Trump.createAndShuffleDecks();
	}

	/**
	 * デッキからカードを配る
	 *
	 * @return 配られたカード
	 */
	public Trump dealCard() {
		return Trump.drawCard(this.useDeck);
	}

	/**
	 * ゲームの初めに各2枚ずつカードを配る
	 */
	public void dealCards() {
		for (int i = 0; i < 2; i++) {
			this.playerHand.add(dealCard());
			this.dealerHand.add(dealCard());
		}
	}

	/**
	 * プレイヤーがHitしたときカードを1枚手札に加える
	 *
	 */
	public void playerAddCards() {
		this.playerHand.add(dealCard());
	}

	/**
	 * ディーラーにカードを1枚手札に加える
	 *
	 */
	public void dealerAddCards() {
		this.dealerHand.add(dealCard());
	}

	/**
	 * 手札の計算。１０以上は10として扱う。１は１１として扱い、合計が２１を超えていた場合１を１として扱う
	 *
	 * @param hand 計算する手札
	 * @return 手札の合計点数
	 */
	public int calculateHandValue(List<Trump> hand) {
		int score = 0;
		int countA = 0;
		for (Trump card : hand) {
			int cardValue = card.getNumber();
			if (cardValue > 10) {
				cardValue = 10;
			}
			if (cardValue == 1) {
				countA++;
				score += 11;
			} else {
				score += cardValue;
			}
		}
		while (score > 21 && countA > 0) {
			score -= 10;
			countA--;
		}
		return score;
	}

	/**
	 * デッキが25％以下かどうかを判定
	 *
	 * @return デッキが25％以下の場合はtrue、そうでない場合はfalse
	 */
	public boolean isDeckLowOnCards() {
		return this.useDeck.size() < Trump.MULTI_DECK * 52 * 0.25;
	}

	/**
	 * ディーラーの手札を取得
	 *
	 * @return ディーラーの手札
	 */
	public List<Trump> getDealerHand() {
		return this.dealerHand;
	}

	/**
	 * プレイヤーの手札を取得する
	 *
	 * @return プレイヤーの手札
	 */
	public List<Trump> getPlayerHand() {
		return this.playerHand;
	}

	/**
	 * それぞれの手札を空にする
	 */
	public void clearHands() {
		dealerHand.clear();
		playerHand.clear();
	}

	// 勝敗を判断する
	public int determineWinner(CardManagement cardManagement) {
		int playerScore = calculateHandValue(cardManagement.getPlayerHand());
		int dealerScore = calculateHandValue(cardManagement.getDealerHand());

		// プレイヤーがバーストしている場合
		if (playerScore > 21) {
			return -1; // ディーラーの勝ち
		}

		// ディーラーがバーストしている場合
		if (dealerScore > 21) {
			return 1; // プレイヤーの勝ち
		}

		// どちらもバーストしていない場合、スコアを比較
		if (playerScore > dealerScore) {
			return 1; // プレイヤーの勝ち
		} else if (playerScore < dealerScore) {
			return -1; // ディーラーの勝ち
		} else {
			return 0; // 引き分け
		}
	}

	/**
	 * デバッグ情報を文字列として返すメソッド
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("=== CardManagement Debug Info ===<br>");
		sb.append(getDeckStatusDebugInfo()).append("<br>");
		sb.append(getDealerHandStatusDebugInfo()).append("<br>");
		sb.append(getPlayerHandStatusDebugInfo()).append("<br>");
		sb.append("================================<br>");
		return sb.toString();
	}

	private String getDeckStatusDebugInfo() {
		return String.format("Deck size: %d, Low on cards: %b", this.useDeck.size(), this.isDeckLowOnCards());
	}

	private String getPlayerHandStatusDebugInfo() {
		return String.format("Player hand: %s, Score: %d", this.playerHand, this.calculateHandValue(this.playerHand));
	}

	private String getDealerHandStatusDebugInfo() {
		return String.format("Dealer hand: %s, Score: %d", this.dealerHand, this.calculateHandValue(this.dealerHand));
	}
}