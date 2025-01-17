package com.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Trump {
    private int number;
    private String suit;

    private static final String HEARTS = "hearts";
    private static final String DIAMONDS = "diamonds";
    private static final String CLUBS = "clubs";
    private static final String SPADES = "spades";

    private static final String[] suits = { HEARTS, DIAMONDS, CLUBS, SPADES };

    private static final int MULTI_DECK = 6; // 指定した数のデッキを混ぜて使用します

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
    public static List<Trump> nDeckGame() {
        List<Trump> deck = new ArrayList<>();
        for (int c = 0; c < MULTI_DECK; c++) {
            for (int i = 1; i <= 13; i++) {
                for (String suit : suits) {
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
    public static Trump deal(List<Trump> list) {
        if (list.isEmpty()) {
            return null;
        }
        Trump tmp = list.get(0);
        list.remove(0);
        return tmp;
    }

    //デッキが75％を下回ったかのチェック
    public boolean isDeckLowOnCards(List<Trump> deck) {
    	if(deck.size() < MULTI_DECK * 52 * 0.25) {
    		return true;
    	}
    	return false;
    }



    //デバック用
    @Override
    public String toString() {
        return suit + "の" + number;
    }
}