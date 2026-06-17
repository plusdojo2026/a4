package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AllMoneyDao;
import dto.AllMoneyDto;

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
		
		//もしもログインしていなかったらログインサーブレットにリダイレクトする
		if (session.getAttribute("userList") == null) {
			response.sendRedirect("LoginServlet");
			return;
		}
		
		AllMoneyDao dao = new AllMoneyDao();
		List<AllMoneyDto> List = dao.select(new AllMoneyDto());
		
		//絵文字のデータを疑似的に作り出す
				ArrayList<String> kaoList = new ArrayList<String>();
				kaoList.add("20");
				kaoList.add("50");
				
				//とりあえずリクエストスコープへセットする
				request.setAttribute("kaoList", kaoList);
				
				//もう一個
				//ArrayList<String> xxList = new ArrayList<String>();
			    //xxList.add("15");
				//xxList.add("20");
				
				//とりあえずリクエストスコープへセットする
				//request.setAttribute("xxList", xxList);

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
		if (session.getAttribute("userList") == null) {
			response.sendRedirect("LoginServlet");
			return;
		}
		
		
		
		//月別収支の表示jspにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/MoneyMonthly.jsp");
		dispatcher.forward(request, response);
		
		
	}

}
