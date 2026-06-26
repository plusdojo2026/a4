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
		// もしもログインしていなかったらログインサーブレットにリダイレクトして終了
		if (session.getAttribute("userList") == null) {
			response.sendRedirect("LoginServlet");
			return;
		}

		// 文字コード
		request.setCharacterEncoding("UTF-8");

		
		// JSPからの入力データの受け取り
		// どのボタンが押されたかを取得
		String submit = request.getParameter("submit");

		// 主キーを取得
		int number = Integer.parseInt(request.getParameter("number"));

		// 各種ウシ情報を取得
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");

		// 日付項目が空文字で届いた場合は、DBに合わせてnullに変換する
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

		// 性別を数値型に変換
		int intGender = Integer.parseInt(gender);

		// 画像に関する情報の取得とパスの設定
		String oldPhoto = request.getParameter("oldPhoto"); // 変更前の画像名
		String photo = oldPhoto; // 初期値として古い画像名をセット
		String path = getServletContext().getRealPath("/images"); // 保存先フォルダの絶対パス

		
		// DTOオブジェクトの作成
		
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

		//変更
		if ("update".equals(submit)) {

			// 【バリデーションチェック①】 ウシIDの10桁チェック
			if (id == null || !id.matches("^[0-9]{10}$")) {
				request.setAttribute("errorMsg", "ウシIDは10桁で入力してください。");

				// DBから変更前のデータを取得し直して画面に戻す
				CowsDto originalCow = dao.getCowByNumber(number);
				request.setAttribute("cow", originalCow);

				request.getRequestDispatcher("/WEB-INF/jsp/CowsUpdateDelete.jsp").forward(request, response);
				return;
			}

			//  他のウシとのID重複チェック
			if (dao.existsIdForUpdate(id, number)) {
				request.setAttribute("errorMsg", "このウシIDは既に登録されています。");

				// DBから変更前のデータを取得し直して画面に戻す
				CowsDto originalCow = dao.getCowByNumber(number);
				request.setAttribute("cow", originalCow);

				request.getRequestDispatcher("/WEB-INF/jsp/CowsUpdateDelete.jsp").forward(request, response);
				return;
			}

			// 画像アップロード処理
			try {
				Part part = request.getPart("newPhoto");
				if (part != null && part.getSize() > 0) {
					String originalName = part.getSubmittedFileName();
					String photoName = System.currentTimeMillis() + "_" + originalName;
					photo = photoName;
					cows.setPhoto(photo); // DTOに新画像名を再セット

					File dir = new File(path);
					if (!dir.exists()) {
						dir.mkdirs();
					}

					part.write(path + File.separator + photoName);

					// 古い画像の削除
					if (oldPhoto != null && !oldPhoto.equals("")) {
						File oldFile = new File(path + File.separator + oldPhoto);
						if (oldFile.exists()) {
							oldFile.delete();
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			// DB更新実行
			if (dao.update(cows)) {
				session.setAttribute("msg", "更新成功");
			} else {
				session.setAttribute("msg", "更新失敗です");
			}

			// 削除ボタンが押された場合の処理
		} else if ("Delete".equals(submit)) {

			// データベースからウシのレコードを削除する
			if (dao.delete(cows)) {
				// DBからの削除が成功した場合のみ、サーバー上の画像ファイルも削除する
				if (oldPhoto != null && !oldPhoto.equals("")) {
					File oldFile = new File(path + File.separator + oldPhoto);
					if (oldFile.exists()) {
						oldFile.delete();
					}
				}
				session.setAttribute("msg", "削除成功");
			} else {
				session.setAttribute("msg", "削除失敗です");
			}

			// どちらのボタンでもない場合
		} else {
			session.setAttribute("msg", "不正な操作です");
		}

		
		// 処理完了後の画面リダイレクト
		// 一覧画面サーブレットへ移動する
		response.sendRedirect("CowsListServlet");
		return;
	}
}