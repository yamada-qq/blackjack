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
        this.useDeck = Trump.nDeckGame();
        this.dealerHand = new ArrayList<>();
        this.playerHand = new ArrayList<>();
    }

    /**
     * デッキを新しいものに変える
     */
    public void resetDeck() {
        this.useDeck = Trump.nDeckGame();
    }

    /**
     * デッキからカードを配る
     * @return
     */
    public Trump dealCard() {
        return Trump.deal(useDeck);
    }

    /**
     * ゲームの初めに各2枚ずつカードを配る
     */
    public void dealInitialCards() {
        for (int i = 0; i < 2; i++) {
            playerHand.add(dealCard());
            dealerHand.add(dealCard());
        }
    }

    /**
     * 手札の計算。１０以上は10として扱う。１は１１としてあつかい、合計が２１を超えていた場合１を１として扱う
     * @param hand
     * @return
     */
    public int calcHand(List<Trump> hand) {
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
     * @return
     */
    public boolean isDeckLowOnCards() {
        return useDeck.size() < Trump.MULTI_DECK * 52 * 0.25;
    }

    /**
     * ディーラーの手札を取得
     * @return
     */
    public List<Trump> getDealerHand() {
        return new ArrayList<>(dealerHand);
    }

    /**
     * プレイヤーの手札を取得する
     * @return
     */
    public List<Trump> getPlayerHand() {
        return new ArrayList<>(playerHand);
    }

    /**
     * それぞれの手札を空にする
     */
    public void clearHands() {
        dealerHand.clear();
        playerHand.clear();
    }
}