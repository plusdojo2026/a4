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

		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");

		String id = request.getParameter("id");
		String name = request.getParameter("name");
		
		// 1. 未入力（空文字）チェック
				// 2. 半角数字10桁チェック 
				if (id == null || id.isEmpty() || !id.matches("^[0-9]{10}$")) {
					// エラーメッセージをセット
					request.setAttribute("errorMsg", "ウシIDは半角数字10桁で入力してください。");
					
					// 入力されていた名前などを画面に保持するためにリクエストにセット
					request.setAttribute("id", id);
					request.setAttribute("name", name);
					request.setAttribute("gender", request.getParameter("gender"));
					request.setAttribute("birth_day", request.getParameter("birth_day"));
					request.setAttribute("status", request.getParameter("status"));
					
					// 登録画面（JSP）にフォワードで戻す（リダイレクトだとエラーメッセージが消えるため）
					RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/CowsRegist.jsp");
					dispatcher.forward(request, response);
					return; // 処理をここで終了し、登録処理へ進ませない
				}
				//名前が未入力の時はIDを名前とする
				if (name == null || name.trim().isEmpty()) {
					name = id; // 名前が空なら、ウシIDの値を名前に代入
				}
		
		int gender = Integer.parseInt(request.getParameter("gender"));

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

		// 画像処理
		Part part = request.getPart("photo");
		String photo = null;

		if (part != null && part.getSize() > 0) {
			String fileName = System.currentTimeMillis() + "_" + part.getSubmittedFileName();
			String path = getServletContext().getRealPath("/images");

			File dir = new File(path);
			if (!dir.exists()) {
				dir.mkdirs();
			}

			part.write(path + File.separator + fileName);
			photo = fileName;
		}

		// コンストラクタ不一致エラーを防止
		CowsDto cows = new CowsDto();
		cows.setId(id);
		cows.setName(name);
		cows.setGender(gender);
		cows.setBirth_day(birth_day);
		cows.setStatus(status);
		cows.setPhoto(photo);
		cows.setUpdatedate(updatedate);
		cows.setCause(cause);
		cows.setRegist_day(regist_day);

		// 登録処理する
		CowsDao dao = new CowsDao();

		// request ではなく session にスコープを変更して格納します
		if (dao.insert(cows)) {
			session.setAttribute("msg", "登録成功");
		} else {
			session.setAttribute("msg", "登録失敗");
		}

		// 一覧へ戻る
		response.sendRedirect("CowsListServlet");
		return;
	}
}