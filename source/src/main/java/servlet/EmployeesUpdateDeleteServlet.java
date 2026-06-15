package servlet;

import java.io.IOException;

import javax.naming.spi.DirStateFactory.Result;
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
		String Sgender = request.getParameter("gender");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String admin = request.getParameter("admin");
		String login_id = request.getParameter("login_id");
		String password = request.getParameter("password");
		int id = Integer.parseInt(Sid);
		int age = Integer.parseInt(Sage);
		int gender = Integer.parseInt(Sgender);
		// 更新または削除を行う
		EmployeesDao eDao = new EmployeesDao();
		if (request.getParameter("submit").equals("更新")) {
			if (eDao.update(new EmployeesDto(id, name,  age, gender, phone, address, 
					admin, login_id,password))) { // 更新成功
				request.setAttribute("result", new Result("更新成功！", "レコードを更新しました。", "MenuServlet"));
			} else { // 更新失敗
				request.setAttribute("result", new Result("更新失敗！", "レコードを更新できませんでした。", "MenuServlet"));
			}
		} else {
			if (eDao.delete(new EmployeesDto(id, name,  age, gender, phone, address, 
					admin, login_id,password))) { // 削除成功
				request.setAttribute("result", new Result("削除成功！", "レコードを削除しました。", "MenuServlet"));
			} else { // 削除失敗
				request.setAttribute("result", new Result("削除失敗！", "レコードを削除できませんでした。", "MenuServlet"));
			}
		}

		// 結果ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/EmployeesList.jsp");
		dispatcher.forward(request, response);
	}

}
