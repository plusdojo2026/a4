package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ShiftUpdateDeleteServlet")
public class ShiftUpdateDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ShiftUpdateDeleteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		 HttpSession session = request.getSession();
			
			// もしもログインしていなかったらログインサーブレットにリダイレクトする
			if (session.getAttribute("loginUser") == null) {
				response.sendRedirect("LoginServlet");
				return;
			}
			
			// ShiftUpdateDelete.jspにフォワードする
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/ShiftUpdateDelete.jsp");
			dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		// 1. セッション情報の取得
				HttpSession session = request.getSession();
				
				//2. リクエストパラメータを取得する
						request.setCharacterEncoding("UTF-8");
						String name = request.getParameter("name");
						String day = request.getParameter("day");
						String time = request.getParameter("time");
						
				//3. ログイン状態のチェック（未ログインならログイン画面へ）
						if (session.getAttribute("loginUser") == null) {
							response.sendRedirect("LoginServlet");
							return;
						}
						
				// 4. ログイン済みの場合はShiftUpdateDelete.jspへフォワード
						RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/ShiftUpdateDelete.jsp");
						dispatcher.forward(request, response);
	}

}
