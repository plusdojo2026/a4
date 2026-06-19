package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CowsDao;
import dto.CowsDto;

@WebServlet("/CowsListServlet")
public class CowsListServlet extends HttpServlet {
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
		
		//ウシDAO
		CowsDao dao = new CowsDao();
		CowsDto dto = new CowsDto();
		List<CowsDto> cowsList = dao.select2(dto);
		//リクエストスコープに格納する
		request.setAttribute("cowsList", cowsList);
		
		
		// ウシ一覧jspにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/CowsList.jsp");
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
		
		//リクエストパラメータを取得する　ウシ一覧
		String idStr = request.getParameter("id");

		if (idStr == null || idStr.isEmpty()) {
		    response.sendRedirect("CowsListServlet");
		    return;
		}
		
		int id = Integer.parseInt(idStr);

		CowsDao dao = new CowsDao();
		CowsDto cow = dao.findById(id);

		request.setAttribute("cow", cow);
		
		//編集からupdatedeleteのページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/CowsUpdateDelete.jsp");
		dispatcher.forward(request, response);
	
	}	
}
