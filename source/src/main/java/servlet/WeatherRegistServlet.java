package servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/WeatherRegistServlet")
public class WeatherRegistServlet extends HttpServlet {
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
		// 天気登録ページにフォワードする
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/WeatherRegist.jsp");
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
		
		
		// 数値から変換してフォーム埋めたい(これはJSP側か？)
		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		String Sday = request.getParameter("day"); //　日付を取得
		String Sweather = request.getParameter("weather"); //　天気を取得
		String Shigh_temperature = request.getParameter("high_temperature"); //　最高気温を取得
		String Slow_temperature = request.getParameter("low_temperature"); //　最低気温を取得
		String Shumidity = request.getParameter("humidity"); //　湿度を取得
		String Sprecipitation = request.getParameter("precipitation"); //　降水量を取得
		String Swindpower = request.getParameter("windpower"); // 風力を取得
		
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd"); //日付変換
        try {
			Date day = sdFormat.parse(Sday);
        } catch (ParseException e) {
            e.printStackTrace();
		}
        int weather = Integer.parseInt(Sweather); //天気コード変換
        BigDecimal high_temperature = new BigDecimal(Shigh_temperature);
        BigDecimal low_temperature = new BigDecimal(Slow_temperature);
        BigDecimal humidity = new BigDecimal(Shumidity);
        BigDecimal precipitation = new BigDecimal(Sprecipitation);
        BigDecimal windpower = new BigDecimal(Swindpower);
		//　登録処理する
			
		// ホームのページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/home.jsp");
		dispatcher.forward(request, response);

}
}
