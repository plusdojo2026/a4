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

import dao.CowsDao;
import dto.CowsDto;

/**
 * Servlet implementation class CowsSearchServlet
 */
@WebServlet("/CowsSearchServlet")
public class CowsSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/*ログイン画面の表示要求（GET）を処理します。*/
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//ログインのチェック
		HttpSession session = request.getSession();
		if (session.getAttribute("userList") == null) {
			response.sendRedirect("/a4/LoginServlet");
			return;
		}
		
		//プルダウン用
		CowsDao dao = new CowsDao();
        request.setAttribute("idList", dao.getCowIdList());
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/CowsDaily.jsp");
        dispatcher.forward(request, response);
        
	}
	
	
	/* ログイン認証の実行要求（POST）を処理します。 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//ログインのチェック
		HttpSession session = request.getSession();
		if (session.getAttribute("userList") == null) {
			response.sendRedirect("/a4/LoginServlet");
			return;
	}

		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		String idSt = request.getParameter("cowId");//ID
		String name = request.getParameter("name");//name
		String birthday = request.getParameter("birthday");//birthday
		
		//IDをStringからintに直したい
		int id = 0;
        if (idSt != null && !idSt.isEmpty()) {
            id = Integer.parseInt(idSt);
        }
        
        //検索処理
        CowsDao dao = new CowsDao();
        List<CowsDto> list = dao.select(new CowsDto(id, name, birthday));
        
        //結果をセット
        request.setAttribute("cowList", list);
        
        //プルダウン用IDリスト
        request.setAttribute("idList", dao.getCowIdList());
        
     // JSP に戻す
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/CowsDaily.jsp");
        dispatcher.forward(request, response);
}}
