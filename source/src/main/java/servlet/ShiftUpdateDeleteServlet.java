package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ShiftDao;
import dto.ShiftDto;

@WebServlet("/ShiftUpdateDeleteServlet")
public class ShiftUpdateDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ShiftUpdateDeleteServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		if (session.getAttribute("userList") == null) {
			response.sendRedirect("LoginServlet");
			return;
		}

		// 2. リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String day = request.getParameter("day");

		// JSPに送る
		dto.EmployeesDto employee = null;

		if (id != null || !id.isEmpty()) {
			try {
				int searchId = Integer.parseInt(id);

				// 全件持ってきて探す
				dao.EmployeesDao empDao = new dao.EmployeesDao();
				java.util.List<dto.EmployeesDto> employeesList = empDao.select2(null);

				for (dto.EmployeesDto emp : employeesList) {
					if (emp != null && emp.getId() == searchId) {
						employee = emp; // 一致する従業員データを格納
						break;
					}
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}

		// リクエストスコープに格納
		request.setAttribute("day", day);
		request.setAttribute("employee", employee);

		// ShiftUpdateDelete.jspにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/ShiftUpdateDelete.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. セッション情報の取得
		HttpSession session = request.getSession();

		// 2. リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");

		String idStr = request.getParameter("id");
		String day = request.getParameter("day");
		String timeStr = request.getParameter("shiftTime");

		String updateBtn = request.getParameter("shift_update");
		String deleteBtn = request.getParameter("shift_delete");

		ShiftDao dao = new ShiftDao();

		try {
			int id = Integer.parseInt(idStr);

			ShiftDto dto = new ShiftDto();
			dto.setId(id);
			dto.setDate(day);

			// 更新する
			if (updateBtn != null) {

				// Dtoにシフト入りの時間をセット
				int intime = Integer.parseInt(timeStr);
				dto.setIntime(intime);

				if (dao.update(dto)) {
					session.setAttribute("msg", "シフトを更新しました。");
				} else {
					session.setAttribute("errorMsg", "シフトの更新に失敗しました。");
				}
				response.sendRedirect("ShiftDisplayServlet");
				return;
			}

			// 削除
			if (deleteBtn != null) {

				if (dao.delete(dto)) {
					session.setAttribute("msg", "シフトを削除しました。");
				} else {
					session.setAttribute("errorMsg", "シフトの削除に失敗しました。");
				}

				response.sendRedirect("ShiftDisplayServlet");
				return;
			}
		} catch (NumberFormatException e) {
			session.setAttribute("errorMsg", "入力値が不正です。");
			response.sendRedirect("ShiftDisplayServlet");
			return;
		}

		// リクエストスコープに格納
		request.setAttribute("id", idStr);
		request.setAttribute("day", day);

		// 3. ログイン状態のチェック（未ログインならログイン画面へ）
		if (session.getAttribute("userList") == null) {
			response.sendRedirect("LoginServlet");
			return;
		}

		// 4. ログイン済みの場合はShiftUpdateDelete.jspへフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/ShiftUpdateDelete.jsp");
		dispatcher.forward(request, response);
	}

}
