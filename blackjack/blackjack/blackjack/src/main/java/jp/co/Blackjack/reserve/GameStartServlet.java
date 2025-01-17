package jp.co.Blackjack.reserve;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.Blackjack.management.GameManagement;

@WebServlet("/GameStart")
public class GameStartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//難易度を受け取る
		String str = request.getParameter("level");
		int level = Integer.parseInt(str);

		GameManagement gameManagement = new GameManagement(level);
		request.setAttribute("gamemanagement", gameManagement);
		request.getRequestDispatcher("BetScreen.jsp").forward(request, response);
			}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			}

}
