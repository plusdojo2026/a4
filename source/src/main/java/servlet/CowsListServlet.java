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
		String name = request.getParameter("name");
		String genderStr = request.getParameter("gender");
		String birth_day = request.getParameter("birth_day");
		String status = request.getParameter("status");
		
		CowsDto dto = new CowsDto();
		
		// 数値変換時のエラー（nullや空文字）を防止
				if (idStr != null && !idStr.isEmpty()) {
					dto.setId(Integer.parseInt(idStr));
				}
				if (genderStr != null && !genderStr.isEmpty()) {
					dto.setGender(Integer.parseInt(genderStr));
				}
				
				dto.setName(name);
				dto.setBirth_day(birth_day);
				dto.setStatus(status);
				
				//リクエストスコープに格納する
				request.setAttribute("selectedCow", dto);
		
		//編集からupdatedeleteのページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/CowsUpdateDelete.jsp");
		dispatcher.forward(request, response);
	
	}	
}
