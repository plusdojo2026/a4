package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CowsDao;
import dto.CowsDto;



@WebServlet("/CowsUpdateDeleteServlet")
public class CowsUpdateDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public CowsUpdateDeleteServlet() {
        super();  
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		//もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if(session.getAttribute("userList") == null) {
			response.sendRedirect("LoginServlet");
			return;	
		}
		//牛更新ページにフォワードする
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/CowsUpdateDelete.jsp");
			dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if(session.getAttribute("userList") == null) {
			response.sendRedirect("LoginServlet");
			return;	
		}	
		
		
		//リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String birth_day = request.getParameter("birth_day");
		String status = request.getParameter("status");
		String photo = request.getParameter("photo");
		String updatedate = request.getParameter("updatedate");
		String cause = request.getParameter("cause");
		String regist_day = request.getParameter("regist_day");
		
		//更新または削除を行う
		CowsDao cDao = new CowsDao();
		
		if(request.getParameter("submit").equals("更新")) {
			if(cDao.update(new CowsDto(id, name, gender, birth_day, status, 
					 photo, updatedate, cause, regist_day))){
				request.setAttribute("msg","更新成功");
			}else {
				request.setAttribute("msg","更新失敗です");
			}
			
		}else {
			if(cDao.delete(new CowsDto(id, name, gender, birth_day, status, 
					 photo, updatedate, cause, regist_day))){
				request.setAttribute("msg","更新成功");
			}else {
				request.setAttribute("msg","更新失敗です");
			}
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/CowsList.jsp");
		dispatcher.forward(request, response);
	}

}
