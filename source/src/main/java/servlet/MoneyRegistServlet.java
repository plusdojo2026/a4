package servlet;

import java.io.IOException;

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
 * Servlet implementation class MoneyRegistServlet
 */
@WebServlet("/MoneyRegistServlet")
public class MoneyRegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/*ログイン画面の表示要求（GET）を処理する*/
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		if (session.getAttribute("userList") == null) {
			response.sendRedirect("LoginServlet");
			return;
		}
		
		// 収支の登録jspにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/MoneyRegist.jsp");
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
		
		//リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		// idはオートインクリメントで取得
		Int income = request.getParameter("income"); //　収入を取得
		Int expense = request.getParameter("expense"); //　支出を取得
		String date = request.getParameter("date"); //　日付を取得
		String reason = request.getParameter("reason"); //　理由を取得
		// 登録処理を行う
				AllMoneyDao moneyDao = new AllMoneyDao();
				if (moneyDao.insert(new AllMoneyDto(0,income,expense,date,reason))) { // 登録成功
					request.setAttribute("msg","登録できたよお");
				} else { // 登録失敗
					request.setAttribute("msg","従業員登録できませんでした。");
				}	
		//収支の処理をする
		
		
		//収支の登録jspにフォワードする（戻る）
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/MoneyRegist.jsp");
		dispatcher.forward(request, response);
		
		
	}

}
	
