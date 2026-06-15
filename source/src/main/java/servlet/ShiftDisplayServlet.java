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

import dao.ShiftDao;
import dto.ShiftDto;

@WebServlet("/ShiftDisplayServlet")
public class ShiftDisplayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShiftDisplayServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
        HttpSession session = request.getSession();
		
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		if (session.getAttribute("userList") == null) {
			response.sendRedirect("LoginServlet");
			return;
		}
		
		// ShiftDisplay.jspにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/ShiftDisplay.jsp");
		dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		// 1. セッション情報の取得
		HttpSession session = request.getSession();
		
		//2. ログイン状態のチェック（未ログインならログイン画面へ）
				if (session.getAttribute("userList") == null) {
					response.sendRedirect("LoginServlet");
					return;
				}
				
		//3. リクエストパラメータを取得する
				request.setCharacterEncoding("UTF-8");
				String idStr = request.getParameter("id");   // 画面の検索入力：従業員ID
				String dayStr = request.getParameter("day"); // 画面の検索入力：日付

				// 数値への変換用変数
				int id = 0;
				if (idStr != null && !idStr.isEmpty()) {
					id = Integer.parseInt(idStr);
				}
				
				// 4. 検索条件を格納するDtoを作成（枝豆の殻の準備）
				// intimeは一旦 0 
				ShiftDto searchCondition = new ShiftDto(id, 0, dayStr);
				
				// 5. ShiftDaoを呼び出してデータベースからシフト情報を検索する（枝豆の中身を取り出す）
				ShiftDao dao = new ShiftDao();
				List<ShiftDto> shiftList = dao.select(searchCondition);
				
				// 6. 検索結果をリクエストスコープに保存してJSPに渡す
				request.setAttribute("shiftList", shiftList);
						
				// 7. ShiftDisplay.jspへフォワード
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/ShiftDisplay.jsp");
				dispatcher.forward(request, response);

	}
}
