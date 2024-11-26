package jp.co.Blackjack.management;

public class GameManagement {
	private int level;
	private int round;
	private int minBet = 2;
	private int ownMedal;
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
		this.round = 1;
		this.ownMedal = calculateInitialMedal(level);
		this.betMedal = 0;
		this.winCount = 0;
		this.minBet = 2;
	}

	/**
	 * 入力されたレベルによって初期メダルを設定する
	 *
	 * @param level
	 * @return
	 */
	public int calculateInitialMedal(int level) {
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

	public void nextRound() {
		this.round++;
	}

	public int getRound() {
		return this.round;
	}

	public int getMinBet() {
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

	public int getMaxOwnMedal() {
		return this.maxOwnMedal;
	}

	/**
	 * betした金額を受け取り、その分の所持メダルを減らす
	 *
	 * @param betMedal
	 * @return
	 */
	public boolean placeBet(int betMedal) {
		if (this.ownMedal >= betMedal) {
			this.ownMedal -= betMedal;
			this.betMedal = betMedal;
			return true;
		}
		return false;
	}

	/**
	 * 勝敗の結果を受け取り、メダル清算などの処理を行う
	 *
	 * @param result
	 * @return
	 */
	public void processGameResult(int i) {
		// 勝った場合の処理
		if (i == 1) {
			this.setOwnMedal(this.getOwnMedal() + this.getBetMedal() * 2);
			this.winCount++;
			updateMaxOwnMedal();
		} else if (i == 0) {
			this.setOwnMedal(this.getOwnMedal() + this.getBetMedal());
		}

		this.round++;
		this.setBetMedal(0);
		updateMinBet();

	}

	/**
	 * 最高所持メダルを記録する
	 */
	private void updateMaxOwnMedal() {
		if (this.maxOwnMedal < ownMedal) {
			this.maxOwnMedal = ownMedal;
		}
	}

	// ラウンド毎に参加費を計算する
	private void updateMinBet() {
		this.minBet = this.round / (6 - level) + 2;
	}

	// 次のラウンドの最小Bet費を計算する
	public int nextRoundMinBet() {
		return (this.round + 1) / (6 - level) + 2;
	}

	// 次のラウンドの最小Bet費以上に所持メダルがあるかの判定
	public boolean canContinueWithMinBet() {
		return this.ownMedal > nextRoundMinBet();
	}

	@Override
	public String toString() {
		return "<br>GameManagement" + "<br>level=" + level + ", <br>round=" + round + ", <br>minBet=" + minBet
				+ ", <br>ownMedal=" + ownMedal +  ", <br>betMedal=" + betMedal + ", <br>winCount=" + winCount
				+ ", <br>maxOwnMedal=" + maxOwnMedal + "<br>";
	}
}