package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.EmployeesDao;
import dao.ShiftDao;
import dto.EmployeesDto;
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
		
        HttpSession session = request.getSession();
		
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		if (session.getAttribute("userList") == null) {
			response.sendRedirect("LoginServlet");
			return;
		}
		//初期表示用にシフトデータを呼ぶ
		ShiftDao dao = new ShiftDao();
		List<ShiftDto> shiftList = dao.select(null); 
		
		//MapにシフトDTOをidごとに格納しそのマップを更に格納するカレンダーマップ
		Map<String, Map<Integer, ShiftDto>> calendarMap = new HashMap<>();
		for (ShiftDto shift : shiftList) {

			String date = shift.getDate();   // 日付
	        Integer empId = shift.getId();   // 従業員ID

		    //従業員IDがMapに登録されていないならMapに格納
		    if (!calendarMap.containsKey(date)) {
		    	calendarMap.put(date, new HashMap<>());
		    }
		    
		    // 日付の中に従業員シフトを入れる
	        calendarMap.get(date).put(empId, shift);
		}
		//従業員データを持ってくる
		EmployeesDao empDao = new EmployeesDao();
		List<EmployeesDto> employeesList = empDao.select2(null);
		//入っているか確認
		System.out.println("確認" + employeesList.size());
		
				// リクエストスコープに格納
				request.setAttribute("employeesList", employeesList);
				request.setAttribute("calendarMap", calendarMap);
				
				// ShiftDisplay.jspにフォワードする
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/ShiftDisplay.jsp");
				dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. セッション情報の取得
		HttpSession session = request.getSession();
		
		//2. ログイン状態のチェック（未ログインならログイン画面へ）
				if (session.getAttribute("userList") == null) {
					response.sendRedirect("LoginServlet");
					return;
				}
				
		//3. リクエストパラメータを取得
				request.setCharacterEncoding("UTF-8");
				
				ShiftDto dto = new ShiftDto();
				ShiftDao dao = new ShiftDao();
				
				
			//どのボタンが押されたか
				String updateBtn = request.getParameter("shift_updatebutton");
				String submitBtn = request.getParameter("shift_submitbutton");
				
//----------------------------------------------------------------------------------
		// 更新削除ボタンが押されたら
		if(updateBtn != null) {
				String id = request.getParameter("id");   // 従業員ID
				String day = request.getParameter("day"); // 日付
				
			if (id != null && !id.isEmpty() && day != null && !day.isEmpty()) {
				//ShiftDtoに入れる
				dto.setId(Integer.parseInt(id));
				dto.setDate(day);
				//検索処理
				List<ShiftDto> shiftList = dao.select(dto);
				
			// 検索結果をリクエストスコープに格納する
				request.setAttribute("shiftList", shiftList);
			}
			else {
				request.setAttribute("errorMsg", "従業員と日付を選択してください。");
			}
		}
//----------------------------------------------------------------------------------
//----------------------------------------------------------------------------------
		//登録ボタンが押されたら
		else if(submitBtn != null) {
		 //リクエストパラメータを取得
			String id = request.getParameter("id");   // 従業員ID
			String day = request.getParameter("day"); // 日付
			String intime = request.getParameter("intime");//画面のシフトに入る時間選択}
			
			if (id != null && !id.isEmpty() && day != null && !day.isEmpty() && intime != null && !intime.isEmpty()) {
				
				// 文字列（朝・夕など）をDB用の数値に変換する
                int time_number = Integer.parseInt(intime);
                
				// ShiftDtoに入れる
			    dto.setId(Integer.parseInt(id));
			    dto.setDate(day);
			    dto.setIntime(time_number);
			    
			// 登録実行
			    if (dao.insert(dto)) {
				    request.setAttribute("msg", "シフトを登録しました。");
				    }
			    else {
				    	request.setAttribute("errorMsg", "シフトの登録に失敗しました。");
				    }
			    }
			else {
				request.setAttribute("errorMsg", "登録に必要な情報をすべて入力してください。");
				}
			}
//-----------------------------------------------------------------------------------------------------------------
		//初回以降表示させる用に
		EmployeesDao empDao = new EmployeesDao();
		List<EmployeesDto> employeesList = empDao.select2(null);

		request.setAttribute("employeesList", employeesList);
		ShiftDao shiftDao = new ShiftDao();
		List<ShiftDto> shiftList = shiftDao.select(null);

		Map<Integer, List<ShiftDto>> shiftMap = new HashMap<>();
		for (ShiftDto shift : shiftList) {
		    Integer empId = shift.getId();

		    if (!shiftMap.containsKey(empId)) {
		        shiftMap.put(empId, new ArrayList<>());
		    }

		    shiftMap.get(empId).add(shift);
		}
		request.setAttribute("calendarMap", shiftMap);
		
		// ShiftDisplay.jspにフォワードする
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/ShiftDisplay.jsp");
			dispatcher.forward(request, response);
	}
}

