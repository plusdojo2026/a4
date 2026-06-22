package servlet;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import dao.CowsDao;
import dto.CowsDto;

@MultipartConfig
@WebServlet("/CowsRegistServlet")
public class CowsRegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Getの方
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("userList") == null) {
			response.sendRedirect("LoginServlet");
			return;
		}
		// ウシ個体登録ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/CowsRegist.jsp");
		dispatcher.forward(request, response);
	}

	// Postの方
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

		String idStr = request.getParameter("id");
		int id = 0;
		
		if (idStr != null && !idStr.isEmpty()) {
		    id = Integer.parseInt(idStr);
		}	
		String name = request.getParameter("name");
		int gender = Integer.parseInt(request.getParameter("gender"));
		String birth_day = request.getParameter("birth_day");
		String status = request.getParameter("status");
		//String photo = request.getParameter("photo");
		String updatedate = request.getParameter("updatedate");
		String cause = request.getParameter("cause");
		String regist_day = request.getParameter("regist_day");
		
		// 画像処理
				Part part = request.getPart("photo");
				String photo = null;

				if (part != null && part.getSize() > 0) {

					String fileName = System.currentTimeMillis()
							+ "_" + part.getSubmittedFileName();

					String path = getServletContext().getRealPath("/images");

					File dir = new File(path);
					if (!dir.exists()) {
						dir.mkdirs();
					}

					part.write(path + File.separator + fileName);

					photo = fileName;
				}


		// 登録処理する
		CowsDao dao = new CowsDao();
		if (dao.insert(new CowsDto(id, name, gender, birth_day, status, photo, updatedate, cause, regist_day))) {
			request.setAttribute("msg", "登録成功");
		} else {
			request.setAttribute("msg", "登録失敗");
		}

		// 一覧へ戻る
		response.sendRedirect("CowsListServlet");
		return;
	}
}