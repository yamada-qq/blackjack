package com.management;

public class GameManagement {
	private int level;
	private int round;
	private int ownMedal;
	private int minBet = 2;
	private int betMedal;
	private int winCount;
	private int maxOwnMedal = 0;

	private final int LEVEL_EASY_MEDAL = 30;
	private final int LEVEL_NORMAL_MEDAL = 20;
	private final int LEVEL_HERD_MEDAL = 10;

	/**
	 * レベルを受け取りオブジェクトを作成する
	 *
	 * @param level
	 */
	public GameManagement(int level) {
		this.level = level;
		this.round = 0;
		this.ownMedal = initialMedal(level);
		this.betMedal = 0;
		this.winCount = 0;
	}

	/**
	 * 入力されたレベルによって初期メダルを設定する
	 *
	 * @param level
	 * @return
	 */
	public int initialMedal(int level) {
		if (level == 1) {
			return LEVEL_EASY_MEDAL;
		} else if (level == 2) {
			return LEVEL_NORMAL_MEDAL;
		} else {
			return LEVEL_HERD_MEDAL;
		}
	}

	public int getLevel() {
		return this.level;
	}

	public void setRound(int round) {
		this.round = round;
	}

	public int getRound() {
		return this.round;
	}

	public int getminBet() {
		return this.minBet;
	}

	public void setOwnMedal(int ownMedal) {
		this.ownMedal = ownMedal;
	}

	public int getOwnMedal() {
		return this.ownMedal;
	}

	public void setBetMedal(int betMedal) {
		this.betMedal = betMedal;
	}

	public int getBetMedal() {
		return this.betMedal;
	}

	public void setWinCount(int winCount) {
		this.winCount = winCount;
	}

	public int getWinCount() {
		return this.winCount;
	}

	public int getMaxMedal() {
		return this.maxOwnMedal;
	}

	/**
	 * betした金額を受け取り、その分の所持メダルを減らす
	 *
	 * @param betMedal
	 * @return
	 */
	public boolean BetAfterMedal(int betMedal) {
		if (this.ownMedal >= this.betMedal) {
			this.setOwnMedal(this.ownMedal - this.betMedal);
			return true;
		}
		return false;
	}

	/**
	 * 勝敗の結果を受け取り、結果を返す
	 *
	 * @param result
	 * @return
	 */
	public boolean resultGameManagement(boolean result) {
		// 勝った場合の処理
		if (result == true) {
			this.setOwnMedal(this.getOwnMedal() + this.getBetMedal() * 2);
			this.winCount++;
			checkMaxMedal();
		}
		if (checkminBet() == false) {
			return false;
		}
		this.round++;
		this.setBetMedal(0);
		calcuminBet();
		return true;
	}

	/**
	 * 最高所持メダルを記録する
	 */
	private void checkMaxMedal() {
		if (this.maxOwnMedal < ownMedal) {
			this.maxOwnMedal = ownMedal;
		}
	}

	// ラウンド毎に参加費を計算する
	private void calcuminBet() {
		this.minBet = this.round / 5 + 2;
	}

	// 参加費以上に所持メダルがあるかの判定
	public boolean checkminBet() {
		if (this.ownMedal - this.minBet > 0) {
			return true;
		} else {
			return false;
		}
	}

}
