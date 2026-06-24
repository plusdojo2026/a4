package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CowsDailyDao;
import dao.EmployeesDao;
import dao.FeedsDao;
import dao.ShiftDao;
import dto.EmployeesDto;
import dto.FeedsDto;
import dto.ShiftDto;

@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 1. セッション情報の取得
		HttpSession session = request.getSession();

		// 2. ログイン状態のチェック（未ログインならログイン画面へ）
		if (session.getAttribute("userList") == null) {
			response.sendRedirect("LoginServlet");
			return;// 処理終了
		}
		// アルゴリズム部分
		// 3. 異常のある牛を DB から取得
		CowsDailyDao dao = new CowsDailyDao();
		// リスト作製、今日の日付取得
		List<String> badCowNames = dao.badCowNames(LocalDate.now());
		// JSPに返す
		request.setAttribute("badCowNames", badCowNames);

		// 従業員データを持ってくる
		EmployeesDao empDao = new EmployeesDao();
		List<EmployeesDto> employeesList = empDao.select2(null);

		// シフトデータを呼ぶ
		ShiftDao shiftDao = new ShiftDao();
		List<ShiftDto> shiftList = shiftDao.select(null);

		// 今日のシフトで使う日付取得
		String today = LocalDate.now().toString();
		
		//マップの中にシフトと時間を入れる
		Map<EmployeesDto,Integer> todayWorkersMap = new LinkedHashMap<>();


		// シフトデータをループして今日働く人を特定する
		for (ShiftDto shift : shiftList) {
			if (shift == null) {
				continue;
			}
			String date = shift.getDate(); // 日付
			Integer empId = shift.getId(); // 従業員ID
			int intime = shift.getIntime(); // 入る時間

			// 今日の日付とシフトの日付が一致した時
			if (today.equals(date)) {
				//該当する従業員を探す
				for(EmployeesDto emp :employeesList) {
					if (emp != null && emp.getId() == empId) {
						todayWorkersMap.put(emp,intime);
						break; // 見つかったら内側のループを抜ける
					}
				}
			}
		}
		//  JSPに今日のシフトメンバーを渡す
		session.setAttribute("todayWorkersMap", todayWorkersMap);
		
		//エサのデータを取得
		FeedsDao esadao = new FeedsDao();
		ArrayList<FeedsDto> feedsList = esadao.select(new FeedsDto());
		//計算処理
		
		int totalIncrease = 0;
		int totalDecrease = 0;
		
		for (FeedsDto dto:feedsList){
			System.out.println(dto.getIncrease_amount()+"：増量");
			System.out.println(dto.getDecrease_amount()+"：減量");
		}
		
		//拡張for文を使って配列の最後まで計算する
		for (FeedsDto dto:feedsList){
			totalIncrease += dto.getIncrease_amount();
			totalDecrease += dto.getDecrease_amount();
		}
		
		//総量の計算処理
		int total = totalIncrease - totalDecrease;
		
		session.setAttribute("list" , feedsList); //エサのリスト
		session.setAttribute("total", total);//総量
		
		if(total<50) {
			request.setAttribute("feedsmsg", "エサが少なくなっています");
		}

//		//	天気部分
//		WeatherDao wdao = new WeatherDao();
//		List<WeatherDto> weatherList = wdao.select(new WeatherDto());
//		// Listの中の1行目を持ってくるよん
//		if (weatherList != null && !weatherList.isEmpty()) {
//		WeatherDto w = weatherList.get(0);
//		//$air
//		if (w.getHigh_temperature().doubleValue() >=25) {
//			request.setAttribute("air1", "大変熱いので冷房で管理しましょう");
//		}else if(w.getLow_temperature().doubleValue() <10) {
//			request.setAttribute("air1", "冷え込むので暖房で管理しましょう");
//		}else {
//			request.setAttribute("air1", "エアコンはいらないです");
//		}
//		//$window
//		if (w.getWindpower().doubleValue() <3 && w.getPrecipitation().doubleValue() <0) {
//			request.setAttribute("window", "窓を開けて喚起しましょう");
//		}else {
//			request.setAttribute("window", "風力や降水のせいで開けない方がいいです");
//		}
//		//$drink
//		if (w.getHigh_temperature().doubleValue() >25 || w.getHumidity() >60) {
//			request.setAttribute("drink", "いつもよりたくさん水をあげて下さい");
//		}else {
//			request.setAttribute("drink", "水の量はいつも通りにしてください");
//		}
//		}
		// 3. ログイン済みの場合はホーム画面（JSP）へフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Home.jsp");
		dispatcher.forward(request, response);
	}

}