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



@WebServlet("/EmployeesListServlet")
public class EmployeesListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EmployeesListServlet() {
        super();
    }
    // GETの文
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession session =request.getSession();
		
		//ログインしていないや、管理者フラグが０出なければ拒否される
		if (session.getAttribute("userList") == null) {
  			response.sendRedirect("LoginServlet");
  			return;
  		}
	
		EmployeesDao empDao = new EmployeesDao();
		List<EmployeesDto> empList = empDao.select2(new EmployeesDto());		
		// empList内のgenderに入ってる数字(1,2,3)をを文字(男、女、他)に置き換えたい
		for (int i=0; i<empList.size();i++) {
			if (empList.get(i).getGender() == 1) {
				empList.equals("男");
			}else if (empList.get(i).getGender() == 2) {
				empList.equals("女");
			}else if (empList.get(i).getGender() == 3) {
				empList.equals("その他");
			}
		}
		System.out.println(empList.get(0).getGender());
//		String gender = null;
//		if (empList.gender.equals(1)) gender ="男性";
//		else if (empList.gender.equals(2)) gender ="女性";
//		else if (empList.gender.equals(3)) gender ="その他";

		//文字→数字
//        String appetiteStr = request.getParameter("appetite");
//       //〇△✕を数値に変換	
//        int appetite = 0;
//        if (appetiteStr.equals("〇")) appetite = 1;
//        else if (appetiteStr.equals("△")) appetite = 2;
//        else if (appetiteStr.equals("✕")) appetite = 3;
	//リクエストスコープに格納
	request.setAttribute("empList",empList);
	
	RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/EmployeesList.jsp");
	dispatcher.forward(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
