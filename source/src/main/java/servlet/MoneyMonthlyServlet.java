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
		
				//収入と支出の円グラフのデータ
				ArrayList<String> moneyList = new ArrayList<String>();
				moneyList.add("20");
				moneyList.add("50");
				
				//とりあえずリクエストスコープへセットする
				request.setAttribute("moneyList", moneyList);
				
				//収入の円グラフのデータ 　7項目
				ArrayList<String> incomeList = new ArrayList<String>();
			    incomeList.add("15");
				incomeList.add("20");
				incomeList.add("10");
				incomeList.add("10");
				incomeList.add("15");
				incomeList.add("15");
				incomeList.add("15");
				
				
				//とりあえずリクエストスコープへセットする
				request.setAttribute("incomeList", incomeList);
				
				//支出の円グラフのデータ　10項目
				ArrayList<String> expenceList = new ArrayList<String>();
			    expenceList.add("15");
				expenceList.add("20");
				expenceList.add("20");
				expenceList.add("20");
				expenceList.add("20");
				expenceList.add("20");
				expenceList.add("20");
				expenceList.add("20");
				expenceList.add("20");
				expenceList.add("20");
				
				
				//とりあえずリクエストスコープへセットする
				request.setAttribute("expenceList", expenceList);
				
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
