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
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/CowsUpdateDelete.jsp");
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
		int intId = Integer.parseInt(id);
		int intGender = Integer.parseInt(gender);
		
		String submit = request.getParameter("submit");
		
		//更新または削除を行う
		CowsDao cDao = new CowsDao();
		
		if ("更新".equals(submit)) {
			if(cDao.update(new CowsDto(intId, name, intGender, birth_day, status, 
					 photo, updatedate, cause, regist_day))){
				session.setAttribute("msg","更新成功");
			}else {
				session.setAttribute("msg","更新失敗です");
			}
			
		}else {
			if(cDao.delete(new CowsDto(intId, name, intGender, birth_day, status, 
					 photo, updatedate, cause, regist_day))){
				session.setAttribute("msg","削除成功");
			}else {
				session.setAttribute("msg","削除失敗です");
			}
		}
		response.sendRedirect("CowsListServlet");
		return;
	}

}
