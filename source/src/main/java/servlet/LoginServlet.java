package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.EmployeesDao;

public class LoginServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ログインページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Login.jsp");
		dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id"); //idを取得
		String pw = request.getParameter("pw"); //パスワードを取得
		
		int managerFlag = 0; //管理者フラグ　1で管理者
		
		// ログイン処理を行う
		EmployeesDao EmpDao = new EmployeesDao();
		
		if(true) {//仮置きでtrue 後でログイン成功をDAOから貰う
			// セッションスコープにIDを格納する
						HttpSession session = request.getSession();
						//session.setAttribute("id", new LoginUser(id));

						// ホームサーブレットにリダイレクトする
						response.sendRedirect("/webappAns/HomeServlet");
		}
		
		else {//ログイン失敗
            request.setAttribute("errorMsg", "ログインに失敗しました");
		}
	}
	
}
