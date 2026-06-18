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
		
		//食欲
        String strGender = request.getParameter("gender");
       //〇△✕を数値に変換	
        int gender = 0;
        if (strGender.equals("男")) gender = 1;
        else if (strGender.equals("女")) gender = 2;
        else if (strGender.equals("その他の性別")) gender = 3;
		// 更新または削除を行う
		EmployeesDao eDao = new EmployeesDao();
		if (request.getParameter("submit").equals("更新")) {
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
		// 従業員一覧ページにフォワードする
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/EmployeesList.jsp");
				dispatcher.forward(request, response);

	}

}
