package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CowsDailyDao;
import dto.CowsDto;

/**
 * Servlet implementation class CowsDailyServlet
 */


@WebServlet("/CowsDailyServlet")
public class CowsDailyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("userList") == null) {
			response.sendRedirect("/a4/LoginServlet");
			return;
		}

		// 検索ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/CowsDaily.jsp");
		dispatcher.forward(request, response);
	}
    

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
        if (session.getAttribute("userList") == null) {
            response.sendRedirect("/a4/LoginServlet");
            return;
        }
        request.setCharacterEncoding("UTF-8");
        
        //JSPから送られてくる値を取得
        //ID
        int id = Integer.parseInt(request.getParameter("id"));
        
        //日付
        String day = request.getParameter("day");
        
        //体温
        String temperature = request.getParameter("temperature");
        
        //食欲
        String appetiteStr = request.getParameter("appetite");
       //〇△✕を数値に変換	
        int appetite = 0;
        if (appetiteStr.equals("〇")) appetite = 1;
        else if (appetiteStr.equals("△")) appetite = 2;
        else if (appetiteStr.equals("✕")) appetite = 3;
        
        //飲水量
        String drinkingStr = request.getParameter("drinking");
        //〇△✕を数値に変換	
        int drinking = 0;
        if (drinkingStr.equals("〇")) drinking = 1;
        else if (drinkingStr.equals("△")) drinking = 2;
        else if (drinkingStr.equals("✕")) drinking = 3;
        
        //排せつ物
        String manureStr = request.getParameter("manure");
        //〇△✕を数値に変換
        int manure = 0;
        if(manureStr.equals("〇")) manure = 1;
        else if (manureStr.equals("△")) manure = 2;
        else if (manureStr.equals("✕")) manure = 3;
        
        //健康状態
        String healthStr = request.getParameter("health");
        //〇△✕を数値に変換
        int health= 0;
        if(healthStr.equals("〇")) health = 1;
        else if (healthStr.equals("△")) health = 2;
        else if (healthStr.equals("✕")) health = 3;
        
        //登録処理
        //DAOを呼び出す
        CowsDailyDao dao = new CowsDailyDao();
        
        //データがなけれインサート、データがあれば登録させない
        if(dao.check(new CowsDto(day,id)) ){
        	request.setAttribute("messega", "今日は登録済みです");
        	RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/CowsDaily.jsp");
            dispatcher.forward(request, response);
        }
       
        if(dao.insert(new CowsDto(id,day,temperature,appetite,drinking,manure,health)))
        {
        	request.setAttribute("message", "日別データを登録しました。");
        
	    } else {

	    request.setAttribute("message", "登録に失敗しました。");

	}
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/CowsDaily.jsp");
        dispatcher.forward(request, response);
        
        
	}

}
