package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/CowsRegistServlet")
public class CowsRegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
		//Getの方
		protected void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			// もしもログインしていなかったらログインサーブレットにリダイレクトする
			HttpSession session = request.getSession();
			if (session.getAttribute("userList") == null) {
				response.sendRedirect("LoginServlet");
				return;
		}
			// ウシ個体登録ページにフォワードする
					RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/CowRegist.jsp");
					dispatcher.forward(request, response);
				}
		
		
		//Postの方
		protected void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			// もしもログインしていなかったらログインサーブレットにリダイレクトする
			HttpSession session = request.getSession();
			if (session.getAttribute("userList") == null) {
				response.sendRedirect("LoginServlet");
				return;
			}
			
			
			// リクエストパラメータを取得する
			
			//　登録処理する
				
			// ホームのページにフォワードする
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/home.jsp");
			dispatcher.forward(request, response);

	}
	}