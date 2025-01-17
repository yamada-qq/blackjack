package jp.co.Blackjack.reserve;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.Blackjack.management.GameManagement;
import jp.co.Blackjack.management.CardManagement;

@WebServlet("/PlayerAction")
public class PlayerActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		GameManagement gameManagement = (GameManagement) session.getAttribute("gamemanagement");
		CardManagement cardManagement = (CardManagement) session.getAttribute("cardManagement");

		if (gameManagement == null || cardManagement == null) {
			response.sendRedirect("menu.jsp");
			return;
		}
		// フォームからの値を受け取る
		String action = request.getParameter("action");
		String nextPage = "ResultScreen.jsp";

		switch (action) {

		case "hit":
			cardManagement.playerAddCards();
			 //プレイヤーの手札が21以下ならプレイ画面へ
	        if(cardManagement.calculateHandValue(cardManagement.getPlayerHand()) < 22) {
			nextPage = "BlackjackPlayScreen.jsp";
	        }
			break;

		case "stand":
			dealerDrawCards(cardManagement);
			break;

		case "doubledown":
			gameManagement.placeBet(gameManagement.getBetMedal());
			gameManagement.setBetMedal(gameManagement.getBetMedal()*2);
			cardManagement.playerAddCards();
			dealerDrawCards(cardManagement);
			break;

		case "surrender":
			gameManagement.setOwnMedal(gameManagement.getOwnMedal() + gameManagement.getBetMedal()/2);
			gameManagement.setBetMedal(0);
			break;

		default:
			// 無効なアクションの場合の処理
			response.sendRedirect("menu.jsp");
			return;
		}

		// ゲーム状態の更新
		session.setAttribute("gamemanagement", gameManagement);
		session.setAttribute("cardManagement", cardManagement);

		request.getRequestDispatcher(nextPage).forward(request, response);
	}

	/**
	 *ディーラがカードを引く
	 * @param cardManagement
	 */
	public void dealerDrawCards(CardManagement cardManagement) {
	    while (cardManagement.calculateHandValue(cardManagement.getDealerHand()) < 17) {
	        cardManagement.dealerAddCards();
	    }
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("menu.jsp");
	}
}