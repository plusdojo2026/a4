package servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.EmployeesDao;
import dao.ShiftDao;
import dto.EmployeesDto;
import dto.ShiftDto;

@WebServlet("/ShiftDisplayServlet")
public class ShiftDisplayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
//    public ShiftDisplayServlet() {
//        super();
//    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		if (session.getAttribute("userList") == null) {
			response.sendRedirect("LoginServlet");
			return;
		}
		// 初期表示用にシフトデータを呼ぶ
		ShiftDao dao = new ShiftDao();
		List<ShiftDto> shiftList = dao.select(null);

		// メッセージ、エラーメッセージ用
		String msg = (String) session.getAttribute("msg");
		String errorMsg = (String) session.getAttribute("errorMsg");

		request.setAttribute("msg", msg);
		request.setAttribute("errorMsg", errorMsg);

		session.removeAttribute("msg");
		session.removeAttribute("errorMsg");

		// MapにシフトDTOをidごとに格納しそのマップを更に格納するカレンダーマップ
		Map<String, Map<Integer, ShiftDto>> calendarMap = new TreeMap<>();
		for (ShiftDto shift : shiftList) {

			if (shift == null) {
				continue;
			}

			String date = shift.getDate(); // 日付
			Integer empId = shift.getId(); // 従業員ID

			// date がなかった場合データを追加・保存する
			if (!calendarMap.containsKey(date)) {
				calendarMap.put(date, new HashMap<>());
			}
			// 指定した日付のMapを取り出して、その中に従業員とシフトを保存する
			calendarMap.get(date).put(empId, shift);
		}

		// 日曜日始まりで「今週」と「来週」の2週間分の日付リストを作成
		java.util.List<String> dateList = new java.util.ArrayList<>();// dateList：システム（DBやMap）の検索で使うための、2026-06-21
																		// のような英数字だけのデータ
		java.util.List<String> displayDateList = new java.util.ArrayList<>(); // JSP表示用のリスト 6/21(日) のような日本語の曜日が付いたデータ。

		java.time.LocalDate today = java.time.LocalDate.now(); // 今日の日付

		// 今日の「直近の日曜日」を取得する
		// 今日から見て、「過去にさかのぼって一番近い日曜日（previous）」または「今日が日曜日なら今日そのもの（same）」
		java.time.LocalDate startSunday = today
				.with(java.time.temporal.TemporalAdjusters.previousOrSame(java.time.DayOfWeek.SUNDAY));

		// 「月/日(曜日)」の日本語形式に変換する
		java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("M/d(E)",
				java.util.Locale.JAPANESE);

		// 日曜日からスタートして14日間（2週間）分の日付を格納
		for (int i = 0; i < 14; i++) {
			java.time.LocalDate targetDate = startSunday.plusDays(i);

			dateList.add(targetDate.toString()); // 例: "2026-06-23" (Map検索用)
			displayDateList.add(targetDate.format(formatter));
		}

		// 従業員データを持ってくる
		EmployeesDao empDao = new EmployeesDao();
		List<EmployeesDto> employeesList = empDao.select2(null);

		// 入っているか確認
		System.out.println("シフト従業員の確認" + employeesList.size());

		// リクエストスコープに格納
		request.setAttribute("employeesList", employeesList);// 従業員の名前リスト
		request.setAttribute("calendarMap", calendarMap);// DBから持ってきたすべてのシフト情報
		request.setAttribute("dateList", dateList); // 2週間分のシステム用日付（Mapを検索するキー用）
		request.setAttribute("displayDateList", displayDateList); // 週間分の曜日付き日付（上のヘッダー用）

		// ShiftDisplay.jspにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/ShiftDisplay.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. セッション情報の取得
		HttpSession session = request.getSession();

		// 2. ログイン状態のチェック（未ログインならログイン画面へ）
		if (session.getAttribute("userList") == null) {
			response.sendRedirect("LoginServlet");
			return;
		}

		// 3. リクエストパラメータを取得
		request.setCharacterEncoding("UTF-8");

		ShiftDto dto = new ShiftDto();
		ShiftDao dao = new ShiftDao();

		String idStr = request.getParameter("id"); // 従業員ID
		String day = request.getParameter("day"); // 日付
		String intime = request.getParameter("intime");// 画面のシフトに入る時間選択
		int time_number = 4; 
		if (intime != null && !intime.trim().isEmpty()) {
			try {
				time_number = Integer.parseInt(intime);
				request.setAttribute("currentInTime", time_number);
			} catch (NumberFormatException e) {
				session.setAttribute("errorMsg", "時間の入力値が不正です。");
				response.sendRedirect("ShiftDisplayServlet");
				return;
			}
		}

		// どのボタンが押されたか
		String updateBtn = request.getParameter("shift_updatebutton");
		String submitBtn = request.getParameter("shift_submitbutton");

//----------------------------------------------------------------------------------
		// 更新削除ボタンが押されたら
				if (updateBtn != null) {
					// 未入力の場合のチェック
					if (idStr == null || idStr.isEmpty() || day == null || day.isEmpty()) {
						session.setAttribute("errorMsg", "従業員と日付を選択してください。");
						response.sendRedirect("ShiftDisplayServlet");
						return;
					}
					
					try {
						// キャスト
						int id = Integer.parseInt(idStr);
						
						// 存在チェック用のDtoを作成
						ShiftDto checkDto = new ShiftDto();
						checkDto.setId(id);
						checkDto.setDate(day);
						// Dtoにシフト入りの時間をセット
						dto.setIntime(time_number);
						
						// DBに該当するシフトがあるか検索する
						List<ShiftDto> existCheckList = dao.select(checkDto);
						
						// もしデータが見つからない場合は遷移させない
						if (existCheckList == null || existCheckList.isEmpty()) {
							session.setAttribute("errorMsg", "選択された日付のシフトデータが存在しないため、更新・削除はできません。");
							response.sendRedirect("ShiftDisplayServlet");
							return;
						}
						
					} catch (NumberFormatException e) {
						session.setAttribute("errorMsg", "入力された値が不正です。");
						response.sendRedirect("ShiftDisplayServlet");
						return;
					}
					
					// シフトが存在する場合のみ、idと日付を更新削除サーブレットに送る
					response.sendRedirect("ShiftUpdateDeleteServlet?id=" + idStr + "&day=" + day);
					return;
				}

//----------------------------------------------------------------------------------
//----------------------------------------------------------------------------------
		// 登録ボタンが押されたら
		if (submitBtn != null) {
			// リクエストパラメータを取得

			if (idStr != null && !idStr.isEmpty() && day != null && !day.isEmpty() && intime != null
					&& !intime.isEmpty()) {
				try {
					// キャスト
					int id = Integer.parseInt(idStr);
					

					// ShiftDtoに入れる
					dto.setId(id);
					dto.setDate(day);
					dto.setIntime(time_number);

					// selectメソッドを使って重複チェック
					// id と day がセットされた dto を渡すことで、条件に一致するレコードをDBから探す
					List<ShiftDto> existCheckList = dao.select(dto);

					// もしデータが見つかった（リストがnullではなく、かつサイズが0より大きい）場合は重複
					if (existCheckList != null && existCheckList.size() > 0) {
						session.setAttribute("errorMsg", "指定された従業員は、その日にすでにシフトが登録されています。");
						response.sendRedirect("ShiftDisplayServlet");
						return;
					}

					// 登録実行（重複がなければここに進む）
					if (dao.insert(dto)) {
						session.setAttribute("msg", "シフトを登録しました。");
						response.sendRedirect("ShiftDisplayServlet");
						return;
					} else {
						session.setAttribute("errorMsg", "シフトの登録に失敗しました。");
						response.sendRedirect("ShiftDisplayServlet");
						return;
					}
				} catch (NumberFormatException e) {
					session.setAttribute("errorMsg", "入力された値が不正です。");
					response.sendRedirect("ShiftDisplayServlet");
					return;
				}
			} else {
				session.setAttribute("errorMsg", "登録に必要な情報をすべて入力してください。");
				response.sendRedirect("ShiftDisplayServlet");
				return;
			}
		}
//-----------------------------------------------------------------------------------------------------------------
		response.sendRedirect("ShiftDisplayServlet");
		return;
	}
}
