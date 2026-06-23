package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CowsDailyDao;
import dao.WeatherDao;
import dto.WeatherDto;

@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// 1. セッション情報の取得
		HttpSession session = request.getSession();
		
		// 2. ログイン状態のチェック（未ログインならログイン画面へ）
		if (session.getAttribute("userList") == null) {
			response.sendRedirect("LoginServlet");
			return;//処理終了
		}
		//アルゴリズム部分
		//  3. 異常のある牛を DB から取得
		CowsDailyDao dao = new CowsDailyDao();
		//リスト作製、今日の日付取得
		List<String>badCowNames = dao.badCowNames(LocalDate.now());
		//JSPに返す
		request.setAttribute("badCowNames", badCowNames);
		
		//	天気部分
		WeatherDao wdao = new WeatherDao();
		List<WeatherDto> weatherList = wdao.select(new WeatherDto());
		// Listの中の1行目を持ってくるよん
		if (weatherList != null && !weatherList.isEmpty()) {
		WeatherDto w = weatherList.get(0);
		//$air
		if (w.getHigh_temperature().doubleValue() >=25) {
			request.setAttribute("air1", "大変熱いので冷房で管理しましょう");
		}else if(w.getLow_temperature().doubleValue() <10) {
			request.setAttribute("air1", "冷え込むので暖房で管理しましょう");
		}else {
			request.setAttribute("air1", "エアコンはいらないです");
		}
		//$window
		if (w.getWindpower().doubleValue() <3 && w.getPrecipitation().doubleValue() <0) {
			request.setAttribute("window", "窓を開けて喚起しましょう");
		}else {
			request.setAttribute("window", "風力や降水のせいで開けない方がいいです");
		}
		//$drink
		if (w.getHigh_temperature().doubleValue() >25 || w.getHumidity() >60) {
			request.setAttribute("drink", "いつもよりたくさん水をあげて下さい");
		}else {
			request.setAttribute("drink", "水の量はいつも通りにしてください");
		}
		}
		// 3. ログイン済みの場合はホーム画面（JSP）へフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Home.jsp");
		dispatcher.forward(request, response);
	}
	
}