package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CowsDailyDao;

@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// 1. セッション情報の取得
		HttpSession session = request.getSession();
		
		// 2. ログイン状態のチェック（未ログインならログイン画面へ）
		if (session.getAttribute("userList") == null) {
			response.sendRedirect("LoginServlet");
			return;//処理終了
		}
		//アルゴリズム部分
		//  3. 異常のある牛を DB から取得
		CowsDailyDao dao = new CowsDailyDao();
		//リスト作製、今日の日付取得
		List<String>badCowNames = dao.badCowNames(LocalDate.now());
		//JSPに返す
		request.setAttribute("badCowNames", badCowNames);
		
		// 3. ログイン済みの場合はホーム画面（JSP）へフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Home.jsp");
		dispatcher.forward(request, response);
	}
	
}