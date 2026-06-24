package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.EmployeesDao;
import dto.EmployeesDto;

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
  		if (session.getAttribute("userList") == null) {
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
  		// idはオートインクリメントで取得
		String name = request.getParameter("name"); //　名前を取得
		String Sage = request.getParameter("age"); //　年齢を取得
		String Strgender = request.getParameter("gender"); //　性別(0男性1女性2その他)を取得
		String phone = request.getParameter("phone"); //　電話番号を取得
		String address = request.getParameter("address"); // 住所を取得
		String admin = request.getParameter("admin");	
		int age = Integer.parseInt(Sage); //ageをintへ
		int gender = Integer.parseInt(Strgender); //genderをintへ
		
		EmployeesDto emp = new EmployeesDto();
		System.out.println(emp.getGender());
		System.out.println(emp.getStrGender());
			if (emp.getStrGender() =="男性") {
				emp.setGender(1);
			}else if (emp.getStrGender() =="女性") {
				emp.setGender(2);
			}else if (emp.getStrGender() == "どちらでもない") {
				emp.setGender(3);
			}

		// 登録処理を行う
				EmployeesDao eDao = new EmployeesDao();
				if (eDao.insert(new EmployeesDto(0,name,age,gender,phone,admin,address))) { // 登録成功
					request.setAttribute("msg","登録できたよお");
				} else { // 登録失敗
					request.setAttribute("msg","従業員登録できませんでした。");
				}	
  		// ホームのページにフォワードする
  		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/EmployeesList.jsp");
  		dispatcher.forward(request, response);

  }

}
