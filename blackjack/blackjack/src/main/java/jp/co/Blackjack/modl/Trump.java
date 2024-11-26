package jp.co.Blackjack.modl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Trump {
    private int number;
    private String suit;

    private static final String HEARTS = "♥";
    private static final String DIAMONDS = "♦";
    private static final String CLUBS = "♣";
    private static final String SPADES = "♠";

    private static final String[] SUITS = { HEARTS, DIAMONDS, CLUBS, SPADES };

    public static final int MULTI_DECK = 6; // 指定した数のデッキを混ぜて使用します

    // 引数なしのコンストラクタ
    public Trump() {
        // 初期化が必要な場合はここで行います
    }

    // 既存のコンストラクタ
    public Trump(int number, String suit) {
        this.number = number;
        this.suit = suit;
    }

    public int getNumber() {
        return this.number;
    }

    public String getSuit() {
        return this.suit;
    }

    /**
     * 指定数のデッキを組み合わせて作成し、シャッフルする
     * @return N個のデッキを作成したリスト型のTrump
     */
    public static List<Trump> createAndShuffleDecks() {
        List<Trump> deck = new ArrayList<>();
        for (int c = 0; c < MULTI_DECK; c++) {
            for (int i = 1; i <= 13; i++) {
                for (String suit : SUITS) {
                    deck.add(new Trump(i, suit));
                }
            }
        }
        Collections.shuffle(deck);
        Collections.shuffle(deck);
        Collections.shuffle(deck);
        return deck;
    }

    /**
     * リストの中からランダムに１つ取得しリストから削除します
     * @param list
     * @return 引数のlistのなかのTrump
     */
    public static Trump drawCard(List<Trump> list) {
        if (list.isEmpty()) {
            return null;
        }
        Trump card = list.get(0);
        list.remove(0);
        return card;
    }

    /**
     * デッキが75％を下回ったかのチェック
     * @param deck
     * @return デッキが75％を下回った場合はtrue、それ以外はfalse
     */
    public boolean isDeckLow(List<Trump> deck) {
        return deck.size() < MULTI_DECK * 52 * 0.25;
    }

    // デバッグ用
    @Override
    public String toString() {
        return suit + number;
    }
}