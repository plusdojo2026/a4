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

		// ログインしていない場合はログイン画面へ
		if (session.getAttribute("userList") == null) {
			response.sendRedirect("LoginServlet");
			return;
		}

		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String day = request.getParameter("day");

		// JSPに渡すための変数
		dto.EmployeesDto employee = null;
		ShiftDto currentShift = null;

		// id が送られてきている場合のみ処理を行う
		if (id != null && !id.isEmpty()) {
			try {
				int searchId = Integer.parseInt(id);

				// 従業員情報を取得する
				// 従業員一覧を取得
				dao.EmployeesDao empDao = new dao.EmployeesDao();
				List<dto.EmployeesDto> employeesList = empDao.select2(null);

				// 指定されたIDの従業員を探す
				for (dto.EmployeesDto emp : employeesList) {
					if (emp != null && emp.getId() == searchId) {
						employee = emp; // 一致した従業員情報を保存
						break;
					}
				}

				// 現在登録されているシフトを取得する
				// ShiftDaoを生成
				ShiftDao shiftDao = new ShiftDao();

				// 検索条件をセット
				// 従業員IDと日付で対象シフトを検索
				ShiftDto searchDto = new ShiftDto();
				searchDto.setId(searchId);
				searchDto.setDate(day);

				// DBからシフト情報を取得
				List<ShiftDto> shiftList = shiftDao.select(searchDto);

				// シフトが見つかった場合
				if (shiftList != null && !shiftList.isEmpty()) {

					// 検索結果の先頭データを取得
					// （IDと日付は重複しない前提）
					currentShift = shiftList.get(0);
				}

			} catch (NumberFormatException e) {

				// IDが数値でない場合
				e.printStackTrace();
			}
		}

		// JSPへデータを渡す

		// 選択された日付
		request.setAttribute("day", day);

		// 従業員情報
		request.setAttribute("employee", employee);

		// 現在登録されているシフト情報
		request.setAttribute("currentShift", currentShift);

		// ShiftUpdateDelete.jspへ遷移
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/ShiftUpdateDelete.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. セッション情報の取得
		HttpSession session = request.getSession();
		
		// ログインしていない場合はログイン画面へ
		if (session.getAttribute("userList") == null) {
			response.sendRedirect("LoginServlet");
			return;
		}

		// 2. リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");

		String idStr = request.getParameter("id"); // 従業員ID
		String day = request.getParameter("day"); // 対象日付
		String timeStr = request.getParameter("shiftTime"); // シフト時間

		// 押されたボタンを取得
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

				// 選択されたシフト時間を取得
				int intime = Integer.parseInt(timeStr);
				// DTOへセット
				dto.setIntime(intime);

				// DB更新
				if (dao.update(dto)) {
					session.setAttribute("msg", "シフトを更新しました。");
				} else {
					session.setAttribute("errorMsg", "シフトの更新に失敗しました。");
				}

				// 一覧画面へ戻る
				response.sendRedirect("ShiftDisplayServlet");
				return;
			}

			// 削除
			if (deleteBtn != null) {

				// DB削除
				if (dao.delete(dto)) {
					session.setAttribute("msg", "シフトを削除しました。");
				} else {
					session.setAttribute("errorMsg", "シフトの削除に失敗しました。");
				}

				// 一覧画面へ戻る
				response.sendRedirect("ShiftDisplayServlet");
				return;
			}
		} catch (NumberFormatException e) {
			session.setAttribute("errorMsg", "入力値が不正です。");
			response.sendRedirect("ShiftDisplayServlet");
			return;
		}

		// どのボタンも押されていない場合
		response.sendRedirect("ShiftDisplayServlet");
	}
}
