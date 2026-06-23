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
		// 1. 画面から「新しいID」と「変更前の古いID」をそれぞれ取得する
		int id = Integer.parseInt(request.getParameter("id"));
		int oldId = Integer.parseInt(request.getParameter("oldId")); // JSPに hidden で配置
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
		if(regist_day != null && regist_day.isEmpty()) {
			regist_day = null;
		}
		int intGender = Integer.parseInt(gender);
		
		// 現在の画像
		String oldPhoto = request.getParameter("oldPhoto");

		// DBへ保存する画像名
		String photo = oldPhoto;

		Part part = request.getPart("newPhoto");


		// Tomcatのimagesフォルダ
		String path = getServletContext().getRealPath("/images");


		if(part != null && part.getSize() > 0){

		    String originalName = part.getSubmittedFileName();

		    // 同じ名前対策
		    String photoName = System.currentTimeMillis()
		            + "_" + originalName;

		    photo = photoName;


		    File dir = new File(path);

		    if(!dir.exists()){
		        dir.mkdirs();
		    }


		    // 新しい画像保存
		    part.write(path + File.separator + photoName);


		    // 古い画像削除
		    if(oldPhoto != null && !oldPhoto.equals("")){

		        File oldFile = new File(
		            path + File.separator + oldPhoto
		        );

		        if(oldFile.exists()){
		            oldFile.delete();
		        }
		    }


		    System.out.println("保存先：" + path);
		    System.out.println("画像名：" + photo);
		}
		
		
		String submit = request.getParameter("submit");
		
		//更新または削除を行う
		CowsDao dao = new CowsDao();
		 CowsDto cows = new CowsDto(id, name, intGender, birth_day, status, photo, updatedate, cause, regist_day);
		
		if ("更新".equals(submit)) {
			if(dao.update(cows, oldId)){
	            session.setAttribute("msg", "更新成功");
	        } else {
	            session.setAttribute("msg", "更新失敗です");
	        }
			
		}else {
	        // 削除処理（削除時の引数は現状の delete メソッドの定義に合わせてください）
	        if(dao.delete(cows)){
	            session.setAttribute("msg", "削除成功");
	        } else {
	            session.setAttribute("msg", "削除失敗です");
	        }
	    }

//	    //確認
//		System.out.println("gender=" + gender);
//		System.out.println("intGender=" + intGender);
		response.sendRedirect("CowsListServlet");
		return;
	}

}
