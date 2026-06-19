package benri;

import java.util.Calendar;
import java.util.Date;



	public class Benri {
		
		//java.util.Date型の値を引数で渡したら、
		//配列で0番目にその年のはじめ、1番目にその年の終わりの
		//日付を格納して帰してくれるメソッド
		public  String[] getYearRange(Date date) {
		    Calendar cal = Calendar.getInstance();
		    cal.setTime(date);
		    
		    int year = cal.get(Calendar.YEAR);
		    
		    String[] range = new String[2];
		    range[0] = year + "-01-01";  // その年の1月1日
		    range[1] = year + "-12-31";  // その年の12月31日
		    
		    return range;
		}
		
		//上記の月バージョン（月の初めと月の終わりを返却）
		public static String[] getMonthRange(Date date) {
		    Calendar cal = Calendar.getInstance();
		    cal.setTime(date);
		    
		    int year = cal.get(Calendar.YEAR);
		    int month = cal.get(Calendar.MONTH) + 1; // 0始まりなので+1
		    
		    // 月末日を取得
		    int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		    
		    String[] range = new String[2];
		    range[0] = String.format("%d-%02d-01", year, month);      // 月初
		    range[1] = String.format("%d-%02d-%02d", year, month, lastDay); // 月末
		    
		    return range;
		}
		
		// java.util.Date → java.sql.Dateに変換するメソッド
		public  java.sql.Date toSqlDate(java.util.Date utilDate) {
		    return new java.sql.Date(utilDate.getTime());
		}

		// java.sql.Date → java.util.Dateに変換するメソッド
		public  java.util.Date toUtilDate(java.sql.Date sqlDate) {
		    return new java.util.Date(sqlDate.getTime());
		}
	}

