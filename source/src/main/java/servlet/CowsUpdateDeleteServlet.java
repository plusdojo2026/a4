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
@WebServlet("/CowsUpdateDeleteServlet")
public class CowsUpdateDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CowsUpdateDeleteServlet() {
		super();
	}

	/* 画面の表示要求（GET）を処理する */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("userList") == null) {
			response.sendRedirect("LoginServlet");
			return;
		}
		// ウシ更新削除jspにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/CowsUpdateDelete.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		if (session.getAttribute("userList") == null) {
			response.sendRedirect("LoginServlet");
			return;
		}

		request.setCharacterEncoding("UTF-8");

		// numberをJSPのhiddenから取得
		int number = Integer.parseInt(request.getParameter("number"));

		// リクエストパラメータから取得する
		String id = request.getParameter("id");

		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String birth_day = request.getParameter("birth_day");
		if (birth_day != null && birth_day.isEmpty()) {
			birth_day = null;
		}
		String status = request.getParameter("status");
		String updatedate = request.getParameter("updatedate");
		if (updatedate != null && updatedate.isEmpty()) {
			updatedate = null;
		}
		String cause = request.getParameter("cause");
		String regist_day = request.getParameter("regist_day");
		if (regist_day != null && regist_day.isEmpty()) {
			regist_day = null;
		}
		int intGender = Integer.parseInt(gender);

		// 画像処理
		String oldPhoto = request.getParameter("oldPhoto");
		String photo = oldPhoto;
		Part part = request.getPart("newPhoto");
		String path = getServletContext().getRealPath("/images");

		if (part != null && part.getSize() > 0) {
			String originalName = part.getSubmittedFileName();
			String photoName = System.currentTimeMillis() + "_" + originalName;
			photo = photoName;

			File dir = new File(path);
			if (!dir.exists()) {
				dir.mkdirs();
			}

			part.write(path + File.separator + photoName);

			if (oldPhoto != null && !oldPhoto.equals("")) {
				File oldFile = new File(path + File.separator + oldPhoto);
				if (oldFile.exists()) {
					oldFile.delete();
				}
			}
			System.out.println("保存先：" + path);
			System.out.println("画像名：" + photo);
		}

		String submit = request.getParameter("submit");

		// CowsDtoオブジェクトを組み立てる
		CowsDto cows = new CowsDto();
		cows.setNumber(number);
		cows.setId(id);
		cows.setName(name);
		cows.setGender(intGender);
		cows.setBirth_day(birth_day);
		cows.setStatus(status);
		cows.setPhoto(photo);
		cows.setUpdatedate(updatedate);
		cows.setCause(cause);
		cows.setRegist_day(regist_day);

		CowsDao dao = new CowsDao();

		// 【修正】各DAOの引数を新定義（cowsオブジェクトのみ）に合わせて呼び出し
		if ("更新".equals(submit)) {
			if (dao.update(cows)) {
				session.setAttribute("msg", "更新成功");
			} else {
				session.setAttribute("msg", "更新失敗です");
			}
		} else {
			if (dao.delete(cows)) {
				session.setAttribute("msg", "削除成功");
			} else {
				session.setAttribute("msg", "削除失敗です");
			}
		}

		response.sendRedirect("CowsListServlet");
		return;
	}
}