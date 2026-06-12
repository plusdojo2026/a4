package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class MoneyMonthlyServlet
 */
@WebServlet("/MoneyMonthlyServlet")
public class MoneyMonthlyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/*ログイン画面の表示要求（GET）を処理する*/
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
//		if (session.getAttribute("loginUser") == null) {
//			response.sendRedirect("LoginServlet");
//			return;
//		}
		
		//絵文字のデータを疑似的に作り出す
				ArrayList<String> kaoList = new ArrayList<String>();
				kaoList.add("20");
				kaoList.add("50");
				kaoList.add("10");
				kaoList.add("15");
				kaoList.add("10");
				//とりあえずリクエストスコープへセットする
				request.setAttribute("kaoList", kaoList);

				//もう一個
				ArrayList<String> xxList = new ArrayList<String>();
				xxList.add("15");
				xxList.add("20");
				xxList.add("35");
				xxList.add("20");
				xxList.add("10");
				//とりあえずリクエストスコープへセットする
				request.setAttribute("xxList", xxList);

				//MoneyMonthly.jspに遷移させる
				String path="/WEB-INF/jsp/MoneyMonthly.jsp";
				RequestDispatcher dispatcher = request.getRequestDispatcher(path);
				dispatcher.forward(request, response);
	}

	/*ログイン認証の実行要求（POST）を処理する*/
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		if (session.getAttribute("loginUser") == null) {
			response.sendRedirect("LoginServlet");
			return;
		}
		
		//リクエストパラメータを収支の取得する
		
		//餌管理の処理をする
		
		
		//月別収支の表示jspにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/MoneyMonthly.jsp");
		dispatcher.forward(request, response);
		
		
	}

}
