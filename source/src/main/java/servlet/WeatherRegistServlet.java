package servlet;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.WeatherDao;
import dto.WeatherDto;

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
		String day = request.getParameter("day"); //　日付を取得
		String Sweather = request.getParameter("weatherCode"); //　天気を取得
		String Shigh_temperature = request.getParameter("high_kion"); //　最高気温を取得
		String Slow_temperature = request.getParameter("low_kion"); //　最低気温を取得
		String Shumidity = request.getParameter("humidity"); //　湿度を取得
		String Sprecipitation = request.getParameter("pre"); //　降水量を取得
		String Swindpower = request.getParameter("windpower"); // 風力を取得
		
        int weather = Integer.parseInt(Sweather); //天気コード変換
        BigDecimal high_temperature = new BigDecimal(Shigh_temperature);
        BigDecimal low_temperature = new BigDecimal(Slow_temperature);
        int humidity = Integer.parseInt(Shumidity);
        BigDecimal precipitation = new BigDecimal(Sprecipitation);
        BigDecimal windpower = new BigDecimal(Swindpower);
		//　登録処理する
        WeatherDao wDao = new WeatherDao();
        
        if(wDao.check(new WeatherDto(day)) ){
        	request.setAttribute("message", "今日は登録済みです");
        	RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/WeatherRegist.jsp");
            dispatcher.forward(request, response);
            return;
        }
        
        if (wDao.insert(new WeatherDto(day,weather,high_temperature, low_temperature, humidity, precipitation,
				windpower))) { // 更新成功
			request.setAttribute("msg", "登録成功よ～ん");
		}else {
			request.setAttribute("msg", "登録失敗ですよー");
		}
		// ホームのページにフォワードする
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/Home.jsp");
		dispatcher.forward(request, response);

}
}
