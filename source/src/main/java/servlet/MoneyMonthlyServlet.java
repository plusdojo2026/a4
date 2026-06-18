package servlet;

import java.Benri;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AllMoneyDao;
import dto.AllMoneyDto;
import dto.FeedsDto;

/**
 * Servlet implementation class MoneyMonthlyServlet
 */
@WebServlet("/MoneyMonthlyServlet")
public class MoneyMonthlyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/*ログイン画面の表示要求（GET）を処理する*/
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		//もしもログインしていなかったらログインサーブレットにリダイレクトする
		if (session.getAttribute("userList") == null) {
			response.sendRedirect("LoginServlet");
			return;
		}
		
		AllMoneyDao dao = new AllMoneyDao();
		List<AllMoneyDto> List = dao.select(new AllMoneyDto());
		
				//収入と支出の円グラフのデータ
				ArrayList<String> moneyList = new ArrayList<String>();
				moneyList.add("20");
				moneyList.add("50");
				
				//とりあえずリクエストスコープへセットする
				request.setAttribute("moneyList", moneyList);
				
				//収入の円グラフのデータ 　7項目
				ArrayList<String> incomeList = new ArrayList<String>();
			    incomeList.add("15");
				incomeList.add("20");
				incomeList.add("10");
				incomeList.add("10");
				incomeList.add("15");
				incomeList.add("15");
				incomeList.add("15");
				
				
				//とりあえずリクエストスコープへセットする
				request.setAttribute("incomeList", incomeList);
				
				//支出の円グラフのデータ　10項目
				ArrayList<String> expenceList = new ArrayList<String>();
			    expenceList.add("15");
				expenceList.add("20");
				expenceList.add("20");
				expenceList.add("20");
				expenceList.add("20");
				expenceList.add("20");
				expenceList.add("20");
				expenceList.add("20");
				expenceList.add("20");
				expenceList.add("20");
				
				
				//とりあえずリクエストスコープへセットする
				request.setAttribute("expenceList", expenceList);
				
				//MoneyMonthly.jspに遷移させる
				String path="/WEB-INF/jsp/MoneyMonthly.jsp";
				RequestDispatcher dispatcher = request.getRequestDispatcher(path);
				dispatcher.forward(request, response);
	}

	/*ログイン認証の実行要求（POST）を処理する*/
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		if (session.getAttribute("userList") == null) {
			response.sendRedirect("LoginServlet");
			return;
		}
		
		Benri date = new Benri();
		AllMoneyDao dao = new AllMoneyDao();
		List<AllMoneyDto> monthlyList = dao.select1(new AllMoneyDto());
			
			int a = 0;//income（収益）
			int b = 0;//expense（支出）
			
			//拡張for文を使って配列の最後まで計算する
			for (AllMoneyDto dto:monthlyList){
				if(dto.getIncome().equals("収益")) {
					a += dto.getIncome();
				}else {
					b += dto.getExpense();
				}
				
			}
				//収入と支出の円グラフのデータ
				ArrayList<String> moneyList = new ArrayList<String>();
				moneyList.add("a");
				moneyList.add("b");
				
				//とりあえずリクエストスコープへセットする
				request.setAttribute("moneyList", moneyList);
				
			int c = 0;//生乳販売
			int d = 0;//子牛販売
			int e = 0;//廃用牛販売
			int f = 0;//補助金・交付金
			int g = 0;//堆肥販売
			int h = 0;//加工品
			int i = 0;//観光
				
			//拡張for文を使って配列の最後まで計算する
			for (AllMoneyDto dto:monthlyList){
				if() {
					c += dto.get();
				}else if() {
					d += dto.get();
				}else if() {
					e += dto.get();
				}else if() {
					f += dto.get();
				}else if() {
					g += dto.get();
				}else if() {
					h += dto.get();
				}else if() {
					i += dto.get();
				}
			
			}
		
				//収入の円グラフのデータ 　7項目
				ArrayList<String> incomeList = new ArrayList<String>();
			    incomeList.add("c");
				incomeList.add("d");
				incomeList.add("e");
				incomeList.add("f");
				incomeList.add("g");
				incomeList.add("h");
				incomeList.add("i");
				
				//とりあえずリクエストスコープへセットする
				request.setAttribute("incomeList", incomeList);
				
			int j = 0;//飼料費・敷料費
			int k = 0;//光熱水料動力費
			int l = 0;//その他諸材料費
			int m = 0;//種付・獣医飼料・医薬品日
			int n = 0;//貸借料及び料金
			int o = 0;//物件税及び公課諸負担
			int p = 0;//乳牛償却費
			int q = 0;//建物費
			int r = 0;//自動車・農具費
			int s = 0;//労働費
				
			//拡張for文を使って配列の最後まで計算する
			for (AllMoneyDto dto:monthlyList){
				if() {
					j += dto.get();
				}else if() {
					k += dto.get();
				}else if() {
					l += dto.get();
				}else if() {
					m += dto.get();
				}else if() {
					n += dto.get();
				}else if() {
					o += dto.get();
				}else if() {
					p += dto.get();
				}else if() {
					q += dto.get();
				}else if() {
					r += dto.get();
				}else if() {
					s += dto.get();
				}
			
			}
				
				//支出の円グラフのデータ　10項目
				ArrayList<String> expenceList = new ArrayList<String>();
			    expenceList.add("j");
				expenceList.add("k");
				expenceList.add("l");
				expenceList.add("m");
				expenceList.add("n");
				expenceList.add("o");
				expenceList.add("p");
				expenceList.add("q");
				expenceList.add("r");
				expenceList.add("s");
				
				
				//とりあえずリクエストスコープへセットする
				request.setAttribute("expenceList", expenceList);

		
		//月別収支の表示jspにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/MoneyMonthly.jsp");
		dispatcher.forward(request, response);
		
		
	}

}
