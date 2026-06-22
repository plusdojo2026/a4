package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.AllMoneyDto;

	//セレクト、インサート文だけでいい
	public class AllMoneyDao {
	//string,intは受け取るものの名前
	public boolean insert (String record,int money,String reason){
		Connection conn = null;
		//返却する箱を用意しておく
		boolean ans =false;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			
		// データベースに接続する　
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a4?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root","password");
			
			
			
		//SQL文を準備する
			String sql ="INSERT INTO all_money_daily VALUES(0,?,?,?,?)";
			PreparedStatement pStmt =conn.prepareStatement(sql);
			//収入か支出によって、入れる場所を分ける
			if(record.equals("収入")){
				pStmt.setInt(1,money);
				pStmt.setInt(2,0);
				pStmt.setDate(3,new java.sql.Date(System.currentTimeMillis()));
				pStmt.setString(4,reason);
				
			}else {
				pStmt.setInt(1,0);
				pStmt.setInt(2,money);
				pStmt.setDate(3,new java.sql.Date(System.currentTimeMillis()));
				pStmt.setString(4,reason);
			}
			//SQL文を流す（行ってらっしゃい！何件入ったかが返ってくる）
			int a = pStmt.executeUpdate();
			
			//ちゃんと入っていたら、ansをtrueに変更する
			if(a==1){
				ans = true;
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		//結果を呼び出し元のServletに返却する
		return ans;
				
		}
				
	public List<AllMoneyDto> select(AllMoneyDto okane){
		Connection conn = null;
		List<AllMoneyDto> AllMoneyList = new ArrayList<AllMoneyDto>();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a4?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root","password");
			
			 // 「AllMoneyテーブルからデータを持ってきてね、日付が古い順に並べてね」というSQLの命令文
			String sql = "SELECT money_id,income,expense,date,reason FROM all_money_daily ORDER BY date ASC";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			ResultSet rs = pStmt.executeQuery();
			
			while(rs.next()){
				AllMoneyDto dto =new AllMoneyDto(
					rs.getInt("money_id"),
					rs.getInt("income"),
					rs.getInt("expense"),
					rs.getDate("date"),
					rs.getString("reason")
				);
				AllMoneyList.add(dto);
			}
				
			}catch(SQLException e) {
				e.printStackTrace();
			}catch(ClassNotFoundException e){
				e.printStackTrace();
			}finally {
				if(conn !=null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
						
					}
				}
			}
			return AllMoneyList;//結果を呼び出し元のServletに返却する
		}
	
	//収支表示（月別）のselect文
	public List<AllMoneyDto> select1(String one ,String two){
		Connection conn = null;
		List<AllMoneyDto> MoneyList = new ArrayList<AllMoneyDto>();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a4?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root","password");
			
			 // 「all_money_dailyテーブルからデータを持ってきてね、？と？の間の物を取ってくる」というSQLの命令文
			String sql = "SELECT * FROM all_money_daily where date between ? and ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			System.out.println(one +","+two);
			pStmt.setString(1, one);
			pStmt.setString(2, two);
			
			ResultSet rs = pStmt.executeQuery();
			
			while(rs.next()){
				AllMoneyDto dto =new AllMoneyDto(
					rs.getInt("money_id"),
					rs.getInt("income"),
					rs.getInt("expense"),
					rs.getDate("date"),
					rs.getString("reason")
				);
				MoneyList.add(dto);
			}
				
			}catch(SQLException e) {
				e.printStackTrace();
			}catch(ClassNotFoundException e){
				e.printStackTrace();
			}finally {
				if(conn !=null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
						
					}
				}
			}
			return MoneyList;//結果を呼び出し元のServletに返却する
		}
	
}
	