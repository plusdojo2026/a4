package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.FeedsDao;
import dto.FeedsDto;

/**
 * Servlet implementation class FeedsManagementServlet
 */
@WebServlet("/FeedsManagementServlet")
public class FeedsManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/*ログイン画面の表示要求（GET）を処理する*/
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		//もしもログインしていなかったらログインサーブレットにリダイレクトする
		if (session.getAttribute("userList") == null) {
			response.sendRedirect("LoginServlet");
			return;
		}
		FeedsDao dao = new FeedsDao();
		ArrayList<FeedsDto> feedsList = dao.select(new FeedsDto());
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
		request.setAttribute("totalIncrease", totalIncrease);//総購入量
		request.setAttribute("totalDecrease", totalDecrease);//総消費量
		session.setAttribute("total", total);//総量
		
		// エサの管理jspにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/FeedsManagement.jsp");
		dispatcher.forward(request, response);
	}

	/*ログイン認証の実行要求（POST）を処理する*/
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		//リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		if (session.getAttribute("userList") == null) {
			response.sendRedirect("LoginServlet");
			return;
		}
		String record = request.getParameter("record");
		int money = Integer.parseInt(request.getParameter("money"));
		
		//feedsdaoのselect
		FeedsDao dao = new FeedsDao();
		boolean ans = dao.insert(record, money);
		if(ans) {
			System.out.println("登録できたよ！");
		}
		ArrayList<FeedsDto> feedsList = dao.select(new FeedsDto());
		
		
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
		request.setAttribute("totalIncrease", totalIncrease);//総購入量
		request.setAttribute("totalDecrease", totalDecrease);//総消費量
		session.setAttribute("total", total);//総量
		
		//エサの管理jspにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/FeedsManagement.jsp");
		dispatcher.forward(request, response);
		
		
	}

}
