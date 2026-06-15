package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class EmployeesRegistServlet
 */
@WebServlet("/EmployeesRegistServlet")
public class EmployeesRegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public EmployeesRegistServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

  //Getの方
  	protected void doGet(HttpServletRequest request, HttpServletResponse response)
  			throws ServletException, IOException {
  		// もしもログインしていなかったらログインサーブレットにリダイレクトする
  		HttpSession session = request.getSession();
  		if (session.getAttribute("loginUser") == null) {
  			response.sendRedirect("LoginServlet");
  			return;
  	}
  		// 従業員登録ページにフォワードする
  				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/EmployeesRegist.jsp");
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
		String Sid = request.getParameter("id"); //　idを取得
		String name = request.getParameter("name"); //　名前を取得
		String Sage = request.getParameter("age"); //　年齢を取得
		String Sgender = request.getParameter("gender"); //　性別(0男性1女性2その他)を取得
		String phone = request.getParameter("phone"); //　電話番号を取得
		String address = request.getParameter("address"); // 住所を取得
		int id = Integer.parseInt(Sid); //idをintへ
		int age = Integer.parseInt(Sage); //ageをintへ
		int gender = Integer.parseInt(Sgender); //genderをintへ
  		//　登録処理する
  			
  		// ホームのページにフォワードする
  		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/home.jsp");
  		dispatcher.forward(request, response);

  }

}
