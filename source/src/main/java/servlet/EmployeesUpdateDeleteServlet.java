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


@WebServlet("/EmployeesUpdateDeleteServlet")
public class EmployeesUpdateDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EmployeesUpdateDeleteServlet() {
        super();
    }
    // GETの方
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
  			throws ServletException, IOException {
  		// もしもログインしていなかったらログインサーブレットにリダイレクトする
  		HttpSession session = request.getSession();
  		if (session.getAttribute("userList") == null) {
  			response.sendRedirect("LoginServlet");
  			return;
  	}
  		// 従業員変更ページにフォワードする
  		String list_id = request.getParameter("list_id");
  		int listId = Integer.parseInt(list_id);
  		System.out.println(listId);
  		EmployeesDao empDao = new EmployeesDao();
		List<EmployeesDto> empUdList = empDao.select3(new EmployeesDto(listId));
		
		for (EmployeesDto emp: empUdList) {
			if (emp.getGender() ==1) {
				emp.setStrGender("男性");
			}else if (emp.getGender() ==2) {
				emp.setStrGender("女性");
			}else if (emp.getGender() == 3) {
				emp.setStrGender("その他の性別");
			}
		}

		// 検索結果をリクエストスコープに格納する
		request.setAttribute("empUdList", empUdList);
  		
  		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/EmployeesUpdateDelete.jsp");
  		dispatcher.forward(request, response);
  	}
    //　POSTの方
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
		String Sid = request.getParameter("id");
		String name = request.getParameter("name");
		String Sage = request.getParameter("age");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String admin = request.getParameter("admin");
		String login_id = request.getParameter("login_id");
		String password = request.getParameter("password");
		int id = Integer.parseInt(Sid);
		int age = Integer.parseInt(Sage);
		
		//性別の文字を数字に変換
        String strGender = request.getParameter("strGender");
        System.out.println(strGender);
        int gender = 0;
        if (strGender.equals("男性")) gender = 1;
        else if (strGender.equals("女性")) gender = 2;
        else if (strGender.equals("どちらでもない")) gender = 3;
        System.out.println(gender);
        System.out.println(admin);
		// 更新または削除を行う
		EmployeesDao eDao = new EmployeesDao();
		if (request.getParameter("submit").equals("update")) {
			if (eDao.update(new EmployeesDto(id, name,  age, gender, phone, address,
					admin, login_id,password))) { // 更新成功
				request.setAttribute("msg", "更新成功よ～ん");
			}else {
				request.setAttribute("msg", "更新失敗ですよー");
			}
		}else {
			if (eDao.delete(new EmployeesDto(id, name,  age, gender, phone, address,
					admin, login_id,password))){
				request.setAttribute("msg", "削除成功よ～ん");
			}else {
				request.setAttribute("msg", "削除失敗だす");
			}
		}
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
		// 従業員一覧ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/EmployeesList.jsp");
		dispatcher.forward(request, response);

	}

}
