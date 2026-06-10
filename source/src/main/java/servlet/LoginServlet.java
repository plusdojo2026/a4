package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.EmployeesDao;
import dto.EmployeesDto;
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	/**
	 * ログイン画面の表示要求（GET）を処理します。
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ログイン用のJSP画面へフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Login.jsp");
		dispatcher.forward(request, response);
	}
	
	/**
	 * ログイン認証の実行要求（POST）を処理します。
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id"); //idを取得
		String pw = request.getParameter("pw"); //パスワードを取得
		
		// ログイン処理を行う
		// データベースアクセスのためのDAOを生成
		EmployeesDao empDao = new EmployeesDao();
		
		EmployeesDto inputData = new EmployeesDto();
		inputData.setLogin_id(id);
		inputData.setPassword(pw);
		//EmployeesDto loginUser = empDao.isLoginOK(new EmployeesDto(id, pw));
		
		EmployeesDto loginUser = empDao.isLoginOK(inputData);
		if (loginUser != null) {//仮置きでtrue 後でログイン成功をDAOから貰う
			// セッションスコープにIDを格納する
						HttpSession session = request.getSession();
						
						session.setAttribute("loginUser",loginUser);
						//session.setAttribute("id", new LoginUser(id));

						// ホームサーブレットにリダイレクトする
						response.sendRedirect("/webappAns/HomeServlet");
		}
		
		else {//ログイン失敗
            request.setAttribute("errorMsg", "ログインに失敗しました");
            
         // ログイン画面（Login.jsp）へフォワードしてエラーを表示する
         			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Login.jsp");
         			dispatcher.forward(request, response);
		}
	}
	
}
