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
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String birth_day = request.getParameter("birth_day");
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
		int intId = Integer.parseInt(id);
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
//	    //確認
//		System.out.println("gender=" + gender);
//		System.out.println("intGender=" + intGender);
		response.sendRedirect("CowsListServlet");
		return;
	}

}
