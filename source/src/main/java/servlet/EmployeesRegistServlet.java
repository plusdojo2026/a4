package servlet;

import java.io.IOException;
import java.util.List;

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
  		// JSP(リクエスト)から文字列としてパラメータを取得
  		String name = request.getParameter("name");
  		String Sage = request.getParameter("age");
  		String Strgender = request.getParameter("gender"); 
  		String phone = request.getParameter("phone");
  		String address = request.getParameter("address");
  		String admin = request.getParameter("admin");
  		String login_id = request.getParameter("login_id");
  		String password = request.getParameter("password");
  		// 数値への変換処理
  		int age = Integer.parseInt(Sage);
  		// 性別の文字列を数値に変換する
  		int gender = 1; 
  		if ("男性".equals(Strgender)) {
  		    gender = 1;
  		} else if ("女性".equals(Strgender)) {
  		    gender = 2;
  		} else if ("どちらでもない".equals(Strgender)) {
  		    gender = 3;
  		}


  		EmployeesDao eDao = new EmployeesDao();
  		EmployeesDto emp = new EmployeesDto();
  		emp.setName(name);
  		emp.setAge(age);
  		emp.setGender(gender);
  		emp.setPhone(phone);
  		emp.setAddress(address);
  		emp.setAdmin(admin);
  		emp.setLogin_id(login_id);
  		emp.setPassword(password);
  		
  		if (eDao.insert(new EmployeesDto(0, name, age, gender, phone, address, admin,login_id,password))) { // 登録成功
  		    request.setAttribute("msg", "登録できたよお");
  		  EmployeesDao empDao = new EmployeesDao();
  		List<EmployeesDto> empList = empDao.select2(new EmployeesDto());		
  		// empList内のgenderに入ってる数字(1,2,3)をを文字(男、女、他)に置き換えたい
  		// 教えてもらって完成
  		for (EmployeesDto e: empList) {
  			if (e.getGender() ==1) {
  				e.setStrGender("男性");
  			}else if (e.getGender() ==2) {
  				e.setStrGender("女性");
  			}else if (e.getGender() == 3) {
  				e.setStrGender("どちらでもない");
  			}
  		}

  		//リクエストスコープに格納
  		request.setAttribute("empList",empList);
  		} else { // 登録失敗
  		    request.setAttribute("msg", "従業員登録できませんでした。");
  		}   

  		// ホームのページにフォワードする
  		
  		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/EmployeesList.jsp");
  		dispatcher.forward(request, response);
  }

}
