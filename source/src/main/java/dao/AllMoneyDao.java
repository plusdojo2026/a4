package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

	//セレクト、インサート文だけでいい
	public class AllMoneyDao {
	//string,intは受け取るものの名前
	public boolean insert (String record,int money){
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
			String sql ="INSERT INTO AllMoney(0,?,?,?)";
			PreparedStatement pStmt =conn.prepareStatement(sql);
			//収入か支出によって、入れる場所を分ける
			if(record.equals("収入")){
				pStmt.setInt(1,money);
				pStmt.setInt(2,0);
				pStmt.setDate(3,new java.sql.Date(System.currentTimeMillis()));	
				
			}else {
				pStmt.setInt(1,0);
				pStmt.setInt(2,money);
				pStmt.setDate(3,new java.sql.Date(System.currentTimeMillis()));		
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
	}			
		
	
