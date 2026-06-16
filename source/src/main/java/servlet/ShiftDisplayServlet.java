package servlet;

import java.io.IOException;

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
				
		//3. リクエストパラメータを取得
				request.setCharacterEncoding("UTF-8");
				String id = request.getParameter("id");   // 画面の検索入力：従業員ID
				String day = request.getParameter("day"); // 画面の検索入力：日付

				ShiftDto shift = new ShiftDto();
				
				// 登録処理を行う
				ShiftDao dao = new ShiftDao();
				//idと日付必須
				System.out.println(id);
				System.out.println(day);
				if(id != "" && day != "") {
					
					if (dao.insert(shift)) { // 登録成功
						//request.setAttribute("result", new Result("登録成功！", "レコードを登録しました。", "/webapp/MenuServlet"));
					
						
					}
					else { // 登録失敗
						//request.setAttribute("result", new Result("登録失敗！", "レコードを登録できませんでした。", "/webapp/MenuServlet"));
					}
			
				}
				else {
					//request.setAttribute("result", new Result("登録失敗！", "氏名と会社名を入力してください。", "/webapp/MenuServlet"));
				}
				
//				// 結果ページにフォワードする
//				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/result.jsp");
//				dispatcher.forward(request, response);
			}
				
	}

