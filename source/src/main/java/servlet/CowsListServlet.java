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

	/* ウシ一覧画面の表示要求（GET）を処理する */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		if (session.getAttribute("userList") == null) {
			response.sendRedirect("LoginServlet");
			return;
		}

		// ウシDAO
		CowsDao dao = new CowsDao();
		CowsDto dto = new CowsDto();

		// 全件取得
		List<CowsDto> cowsList = dao.selectAll(dto);

		// リクエストスコープに格納する
		request.setAttribute("cowsList", cowsList);

		// ウシ一覧jspにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/CowsList.jsp");
		dispatcher.forward(request, response);
	}

	/* ウシ詳細・編集画面への遷移要求（POST）を処理する */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		if (session.getAttribute("userList") == null) {
			response.sendRedirect("LoginServlet");
			return;
		}

		// リクエストパラメータの文字コードを設定
		request.setCharacterEncoding("UTF-8");

		// number をリクエストパラメータから取得する
		String numberStr = request.getParameter("number");

		if (numberStr == null || numberStr.isEmpty()) {
			response.sendRedirect("CowsListServlet");
			return;
		}

		// numberを数値（int）に変換
		int number = Integer.parseInt(numberStr);

		// 検索用のDtoを用意し、numberをセット
		CowsDto searchCow = new CowsDto();
		searchCow.setNumber(number);

		// 主キーで1件検索を行うため selectByNum を呼び出す
		CowsDao dao = new CowsDao();
		CowsDto cow = dao.selectByNum(searchCow);

		// リクエストスコープに検索結果のウシデータを格納
		request.setAttribute("cow", cow);

		// 編集からupdatedeleteのページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/CowsUpdateDelete.jsp");
		dispatcher.forward(request, response);
	}
}
