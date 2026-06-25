package servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

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
        WeatherDto wt = new WeatherDto();
        // DAOのselectメソッドを呼ぶ
        WeatherDao wdao = new WeatherDao();
        ArrayList<WeatherDto> weatherList = wdao.select(wt);
        if (weatherList != null && !weatherList.isEmpty()) {
            WeatherDto w = weatherList.get(0);
    			if (w.getWeather() ==0) {
    				w.setStrWeather("快晴");
    			}else if (w.getWeather() ==1) {
    				w.setStrWeather("晴れ");
    			}else if (w.getWeather() ==2) {
    				w.setStrWeather("一部曇り");
    			}else if (w.getWeather() ==3) {
    				w.setStrWeather("曇り");
    			}else if (w.getWeather() ==45) {
    				w.setStrWeather("霧");
    			}else if (w.getWeather() ==48) {
    				w.setStrWeather("霧（氷霧)");
    			}else if (w.getWeather() ==51) {
    				w.setStrWeather("薄い霧雨");
    			}else if (w.getWeather() ==53) {
    				w.setStrWeather("霧雨");
    			}else if (w.getWeather() ==55) {
    				w.setStrWeather("濃い霧雨");
    			}else if (w.getWeather() ==56) {
    				w.setStrWeather("微弱な凍る霧雨");
    			}else if (w.getWeather() ==57) {
    				w.setStrWeather("凍る霧雨");
    			}else if (w.getWeather() ==61) {
    				w.setStrWeather("小雨");
    			}else if (w.getWeather() ==63) {
    				w.setStrWeather("雨");
    			}else if (w.getWeather() ==65) {
    				w.setStrWeather("大雨");
    			}else if (w.getWeather() ==66) {
    				w.setStrWeather("微弱な地雨");
    			}else if (w.getWeather() ==67) {
    				w.setStrWeather("強い地雨");
    			}else if (w.getWeather() ==71) {
    				w.setStrWeather("小雪");
    			}else if (w.getWeather() ==73) {
    				w.setStrWeather("雪");
    			}else if (w.getWeather() ==75) {
    				w.setStrWeather("大雪");
    			}else if (w.getWeather() ==77) {
    				w.setStrWeather("細氷・霧雪");
    			}else if (w.getWeather() ==80) {
    				w.setStrWeather("わずかなにわか雨");
    			}else if (w.getWeather() ==81) {
    				w.setStrWeather("にわか雨");
    			}else if (w.getWeather() ==82) {
    				w.setStrWeather("激しいにわか雨");
    			}else if (w.getWeather() ==85) {
    				w.setStrWeather("わずかにわか雪");
    			}else if (w.getWeather() ==86) {
    				w.setStrWeather("にわか雪");
    			}else if (w.getWeather() ==95) {
    				w.setStrWeather("雷雨");
    			}else if (w.getWeather() ==96) {
    				w.setStrWeather("雹を伴う微弱な雷雨");
    			}else if (w.getWeather() ==99) {
    				w.setStrWeather("雹を伴う激しい雷雨");
    			}

            // weatherDataという名前で、wを丸ごとsessionに入れる
            session.setAttribute("weatherData", w);
        }
//    	下天気部分
    		// Listの中の1行目を持ってくるよん
    		if (weatherList != null && !weatherList.isEmpty()) {
    		WeatherDto w = weatherList.get(0);
    		//$air
    		if (w.getHigh_temperature().doubleValue() >=25) {
    			session.setAttribute("air1", "大変熱いので冷房で管理しましょう");
    		}else if(w.getLow_temperature().doubleValue() <10) {
    			session.setAttribute("air1", "冷え込むので暖房で管理しましょう");
    		}else {
    			session.setAttribute("air1", "エアコンはいらないです");
    		}
    		//$window
    		if (w.getWindpower().doubleValue() <3 && w.getPrecipitation().doubleValue() <0) {
    			session.setAttribute("window", "窓を開けて喚起しましょう");
    		}else {
    			session.setAttribute("window", "窓を開けない方がいいです");
    		}
    		//$drink
    		if (w.getHigh_temperature().doubleValue() >25 || w.getHumidity() >60) {
    			session.setAttribute("drink", "いつもよりたくさん水をあげて下さい");
    		}else {
    			session.setAttribute("drink", "水の量はいつも通りにしてください");
    		}
		// ホームのページにフォワードする
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/Home.jsp");
		dispatcher.forward(request, response);
}
	}
}
