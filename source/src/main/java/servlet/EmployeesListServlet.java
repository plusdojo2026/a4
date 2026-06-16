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
		
		// genderに入ってる数字(1,2,3)をを文字(男、女、他)に置き換えたい
		String gender = null;
		if ("1".equals(empList)) gender = "男";
		else if ("2".equals(empList)) gender = "女";
		else if ("3".equals(empList)) gender = "他";

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
