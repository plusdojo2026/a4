package servlet;

import java.io.IOException;

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
		String Sday = request.getParameter("day"); //　idを取得
		String Sweather = request.getParameter("weather"); //　名前を取得
		String Shigh_temperature = request.getParameter("high_temperature"); //　年齢を取得
		String Slow_temperature = request.getParameter("low_temperature"); //　年齢を取得
		String Shumidity = request.getParameter("humidity"); //　性別(0男性1女性2その他)を取得
		String Sprecipitation = request.getParameter("precipitation"); //　電話番号を取得
		String Swindpower = request.getParameter("windpower"); // 住所を取得
		
		//　登録処理する
			
		// ホームのページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/home.jsp");
		dispatcher.forward(request, response);

}
}
