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


@WebServlet("/CowsRegistServlet")
public class CowsRegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
		//Getの方
		protected void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			// もしもログインしていなかったらログインサーブレットにリダイレクトする
			HttpSession session = request.getSession();
			if (session.getAttribute("userList") == null) {
				response.sendRedirect("LoginServlet");
				return;
		}
			// ウシ個体登録ページにフォワードする
					RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/CowRegist.jsp");
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
			
			// リクエストパラメータを取得する ウシの更新
			request.setCharacterEncoding("UTF-8");
			
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("name");
			int gender = Integer.parseInt(request.getParameter("gender"));
			String birth_day = request.getParameter("birth_day");
			String status = request.getParameter("status");
			String photo = request.getParameter("photo");
			String updatedate = request.getParameter("updatedate");
			String cause = request.getParameter("cause");
			String regist_day = request.getParameter("regist_day");
			
			
			
			//　登録処理する
			CowsDao dao = new CowsDao();
			if(dao.insert(new CowsDto(0,id,name,gender,birth_day,status,photo,updatedate,cause,regist_day))) {
				request.setAttribute("msg", "登録成功");
			}else {
				request.setAttribute("msg", "登録失敗");
			}
			
			// ホームのページにフォワードする
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/home.jsp");
			dispatcher.forward(request, response);

	}
	}