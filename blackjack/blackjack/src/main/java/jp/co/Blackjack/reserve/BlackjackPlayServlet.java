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

@WebServlet("/BlackjackPlay")
public class BlackjackPlayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		GameManagement gameManagement = (GameManagement) session.getAttribute("gamemanagement");
		CardManagement cardManagement = (CardManagement) session.getAttribute("cardManagement");

		if (gameManagement == null) {
			// GET リクエストの場合、適切な処理を行う
			response.sendRedirect("menu.jsp");
			return;
		}

		// BETした額を受け取る
		String str = request.getParameter("betValue");
		int bet = Integer.parseInt(str);

		// デッキがない、もしくは25%以下なら新たに作成
		if (cardManagement == null || cardManagement.isDeckLowOnCards()) {
			cardManagement = new CardManagement();
		}
		cardManagement.clearHands();
		cardManagement.dealCards();
		// セッションに格納
		session.setAttribute("cardManagement", cardManagement);

		// BET額を持ちメダルから引く処理
		if (gameManagement.placeBet(bet)) {
			// セッションを更新
			session.setAttribute("gamemanagement", gameManagement);
			// BlackjackPlayScreen.jsp にフォワード
			request.getRequestDispatcher("BlackjackPlayScreen.jsp").forward(request, response);
		} else {
			response.sendRedirect("menu.jsp");
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// GET リクエストの場合、適切な処理を行う
		response.sendRedirect("menu.jsp");
		return;
	}
}