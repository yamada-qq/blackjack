package jp.co.Blackjack.reserve;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.Blackjack.management.GameManagement;

@WebServlet("/BlackjackPlay")
public class BlackjackPlayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		GameManagement gameManagement = (GameManagement) request.getAttribute("gamemanagement");
		//BETした額を受け取り
		String str = request.getParameter("betValue");
		int bet = Integer.parseInt(str);
		//BET額を持ちメダルから引く処理
		if(gameManagement.BetAfterMedal(bet) == false) {
			response.sendRedirect("menu.jsp");
			return;
		}

		request.setAttribute("gamemanagement", gameManagement);
		request.getRequestDispatcher("BlackjackPlayScreen").forward(request, response);

	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			}

}
