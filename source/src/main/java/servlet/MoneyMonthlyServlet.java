package servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import benri.Benri;
import dao.AllMoneyDao;
import dto.AllMoneyDto;

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
		System.out.println("動いとるよ");
		
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
				expenceList.add("10");
				
				
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
		
		//benriクラスからdateの月別のものを取ってくる
		Benri date = new Benri();
		//AllMoneyDaoをnewしてdaoに格納
		AllMoneyDao dao = new AllMoneyDao();
		request.setCharacterEncoding("UTF-8");
		//選択された月のデータを取得し、変換する
		String selectDate = request.getParameter("selectDate");
		System.out.println(selectDate+"dddddddddddddddddddddddddd");
		selectDate = selectDate.replace("年","-").replace("月","");
		SimpleDateFormat inputSdf = new SimpleDateFormat("yyyy-M");
		Date ddd = null;
		try {
			ddd = inputSdf.parse(selectDate);
		} catch (ParseException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}
		SimpleDateFormat sss = new SimpleDateFormat("yyyy-MM-dd");
		String insDate = sss.format(ddd);
		System.out.println(insDate);
		Date da=null;
		try {
			da = sss.parse(insDate);
		} catch (ParseException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}
		
		
		String[] nyan = date.getMonthRange(da);
		
		
		//dao.select1から取ってきたものをmonthlyListに格納
		List<AllMoneyDto> monthlyList = dao.select1(nyan[0],nyan[1]);
		for (AllMoneyDto dto:monthlyList){
			System.out.println(dto.getIncome()+"aaaaaa");
	}
			//変数
			int a = 0;//income（収益）
			int b = 0;//expense（支出）
			
			//拡張for文を使って配列の最後まで計算する
			for (AllMoneyDto dto:monthlyList){
					a += dto.getIncome();
					b += dto.getExpense();
			}
				//収支の円グラフのデータ
				ArrayList<Integer> moneyList = new ArrayList<>();
				moneyList.add(a);
				moneyList.add(b);
				
				//とりあえずリクエストスコープへセットする
				request.setAttribute("moneyList", moneyList);
			
			//変数
			int c = 0;//生乳販売
			int d = 0;//子牛販売
			int e = 0;//廃用牛販売
			int f = 0;//補助金・交付金
			int g = 0;//堆肥販売
			int h = 0;//加工品
			int i = 0;//観光
			int j = 0;//その他
			
			//拡張for文を使って配列の最後まで計算する
			for (AllMoneyDto dto:monthlyList){
				//
				if(dto.getReason().equals("生乳販売")) {
					c += dto.getIncome();
				}else if(dto.getReason().equals("子牛販売")){
					d += dto.getIncome();
				}else if(dto.getReason().equals("廃用牛販売")) {
					e += dto.getIncome();
				}else if(dto.getReason().equals("補助金・交付金")) {
					f += dto.getIncome();
				}else if(dto.getReason().equals("堆肥販売")) {
					g += dto.getIncome();
				}else if(dto.getReason().equals("加工品")) {
					h += dto.getIncome();
				}else if(dto.getReason().equals("観光")) {
					i += dto.getIncome();
				}else if(dto.getReason().equals("その他")) {
					j += dto.getIncome();
				}
			}
		
				//収入の円グラフのデータ 　7項目
				ArrayList<Integer> incomeList = new ArrayList<>();
			    incomeList.add(c);
				incomeList.add(d);
				incomeList.add(e);
				incomeList.add(f);
				incomeList.add(g);
				incomeList.add(h);
				incomeList.add(i);
				incomeList.add(j);
				
				//とりあえずリクエストスコープへセットする
				request.setAttribute("incomeList", incomeList);
				
			int k = 0;//飼料費・敷料費
			int l = 0;//光熱水料動力費
			int m = 0;//その他諸材料費
			int n = 0;//種付・獣医飼料・医薬品日
			int o = 0;//貸借料及び料金
			int p = 0;//物件税及び公課諸負担
			int q = 0;//乳牛償却費
			int r = 0;//建物費
			int s = 0;//自動車・農具費
			int t = 0;//労働費
			int u = 0;//その他
				
			//拡張for文を使って配列の最後まで計算する
			for (AllMoneyDto dto:monthlyList){
				if(dto.getReason().equals("飼料費・敷料費")) {
					k += dto.getExpense();
				}else if(dto.getReason().equals("光熱水料動力費")) {
					l += dto.getExpense();
				}else if(dto.getReason().equals("その他諸材料費")) {
					m += dto.getExpense();
				}else if(dto.getReason().equals("種付・獣医飼料・医薬品日")) {
					n += dto.getExpense();
				}else if(dto.getReason().equals("貸借料及び料金")) {
					o += dto.getExpense();
				}else if(dto.getReason().equals("物件税及び公課諸負担")) {
					p += dto.getExpense();
				}else if(dto.getReason().equals("乳牛償却費")) {
					q += dto.getExpense();
				}else if(dto.getReason().equals("建物費")) {
					r += dto.getExpense();
				}else if(dto.getReason().equals("自動車・農具費")) {
					s += dto.getExpense();
				}else if(dto.getReason().equals("労働費")) {
					t += dto.getExpense();
				}else if(dto.getReason().equals("その他")) {
					u += dto.getExpense();
				}
			
			}
			
				
				//支出の円グラフのデータ　10項目
				ArrayList<Integer> expenceList = new ArrayList<>();
			    expenceList.add(k);
				expenceList.add(l);
				expenceList.add(m);
				expenceList.add(n);
				expenceList.add(o);
				expenceList.add(p);
				expenceList.add(q);
				expenceList.add(r);
				expenceList.add(s);
				expenceList.add(t);
				expenceList.add(u);
				
				//とりあえずリクエストスコープへセットする
				request.setAttribute("expenceList", expenceList);

		
		//月別収支の表示jspにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/MoneyMonthly.jsp");
		dispatcher.forward(request, response);
		
		
	}

}
