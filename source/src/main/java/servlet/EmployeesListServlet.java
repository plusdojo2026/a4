package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.EmployeesDto;



@WebServlet("/EmployeesListServlet")
public class EmployeesListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EmployeesListServlet() {
        super();
    }

	/**
	 * 従業員りすとの表示要求の処理
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession session =request.getSession();
		
		//ログインしていないや、管理者フラグが０出なければ拒否される
		if (session.getAttribute("userList") == null) {
  			response.sendRedirect("LoginServlet");
  			return;
  		}
		
	//EmployeesDao empDao = new EmployeesDao();
	//List<EmployeesDto> employeeList = empDao.selectAll();
	
		java.util.List<EmployeesDto>employeesList =new java.util.ArrayList<>();
	//リクエストスコープに格納
	request.setAttribute("employeesList",employeesList);
	
	RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/EmployeesList.jsp");
	dispatcher.forward(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
