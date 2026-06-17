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

import dao.CowsDailyDao;
import dto.CowsDto;

/**
 * Servlet implementation class CowsMonthlyServlet
 */
@WebServlet("/CowsMonthlyServlet")
public class CowsMonthlyServlet extends HttpServlet {
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
		        
		       //体重 BigDecimalgal型に直します
		        String weightStr = request.getParameter("weight");
		       
		        //nullチェック
		        BigDecimal weight = null;
		        if(weightStr != null && !weightStr.isEmpty() ) {
		        	weight=new BigDecimal(weightStr);
			       }
		       
		        //牛乳の質 String型を 
		        String milkqualityStr = request.getParameter("milkquality");
		       //〇△✕を数値に変換	
		        int milkquality = 0;
		        if (milkqualityStr.equals("〇")) milkquality = 1;
		        else if (milkqualityStr.equals("△")) milkquality = 2;
		        else if (milkqualityStr.equals("✕")) milkquality = 3;
		        
		        //細菌数 String型を BigDecimalgal型に直します
		       String bacterialCountStr = request.getParameter("bacterialCount");
		     
		       //nullチェック
		       BigDecimal bacterialCount = null;
		       if(bacterialCountStr != null && !bacterialCountStr.isEmpty() ) {
		    	   bacterialCount=new BigDecimal(bacterialCountStr);
		       }
		        //乳脂肪分 BigDecimalgal型に直します
		        String milk_fat_contentStr = request.getParameter("milk_fat_content");
		        
		        //nullチェック
		        BigDecimal milk_fat_content = null;
		        if(milk_fat_contentStr != null && ! milk_fat_contentStr.isEmpty() ) {
		        	milk_fat_content = new BigDecimal(milk_fat_contentStr);
		        }
		        		
		        //体細胞数 Integer型に直します
		        String somatic_cell_countStr = request.getParameter("somatic_cell_count");
		        
		        //nullチェック
		        Integer somatic_cell_count = null;
		        if(somatic_cell_countStr != null && !somatic_cell_countStr.isEmpty()) {
		        	somatic_cell_count =  Integer.parseInt(somatic_cell_countStr);
		        }
		        
		      //日付
		        String day = request.getParameter("day");
		        
		        //登録処理
		        CowsDailyDao dao = new CowsDailyDao();
		        
		        if(dao.insert(new CowsDto(id,weight,milkquality,bacterialCount,milk_fat_content,somatic_cell_count,day)))
		        {
		        	request.setAttribute("message", "日別データを登録しました。");
		        
			    } else {

			    request.setAttribute("message", "登録に失敗しました。");

			}
		        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/CowsDaily.jsp");
		        dispatcher.forward(request, response);
	}

}
