package dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.CowsDto;

public class CowsDailyDao {
	//同じ日付がないかチェックするにするメソッド
	public boolean check(CowsDto cows) {
		Connection conn = null;
		boolean result = false;
		
		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a4?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");
			
			// SQL文を準備する select count で同じ日付がないか探す
			String sql = "select count * from cows_daily WHERE day = ? AND number = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			pStmt.setString(1, cows.getDay());
			pStmt.setInt(2, cows.getId()); 
			
			// SELECT文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();
			//if文
						

	}
	public ArrayList<CowsDto>select(CowsDto cows){
		//ArrayListにはCowsDtoの中身を入れると設定
		ArrayList<CowsDto> cowsList = new ArrayList<CowsDto>();
		Connection conn = null;
	
		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する　a4データベース、IDは要変更、パスはpassword
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a4?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			// SELECT文を準備
			String sql = "SELECT day, number, temperature, appetite, drinking, manure,"
					+ " health FROM cows_daily WHERE day = ? AND number = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			pStmt.setString(1, cows.getDay());
			pStmt.setInt(2, cows.getId()); 

			// SELECT文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();
			
			// 結果表をコレクションにコピーする
			//空の枝豆の殻Cowsdtoに枝豆の中身を取り出して格納していく
			while (rs.next()) {
				CowsDto cowsdto = new CowsDto(
						rs.getInt("number"),       
						rs.getString("day"),       
						rs.getString("temperature"),
						rs.getInt("appetite"),     
						rs.getInt("drinking"),    
						rs.getInt("manure"),      
						rs.getInt("health")    
					);
				cowsList.add(cowsdto);
			}
			
			System.out.println(cowsList.size());
			//以下、例外処理
		} catch (SQLException e) {
			e.printStackTrace();
			cowsList = null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			cowsList = null;
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					cowsList = null;
				}
			}
		}
		//結果を返す
		return cowsList;
	}
	
	public boolean insert(CowsDto cows) {
		Connection conn = null;
		boolean result = false;
		
		
		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a4?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			// SQL文を準備する
			String sql = "INSERT INTO cows_daily (day, number, temperature, appetite, drinking, manure, health)"
					+ " VALUES (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			pStmt.setString(1, cows.getDay());
			pStmt.setInt(2, cows.getId()); 
			pStmt.setString(3, cows.getTemperature()); 
			pStmt.setInt(4, cows.getAppetite());
			pStmt.setInt(5, cows.getDrinking());
			pStmt.setInt(6, cows.getManure());
			pStmt.setInt(7, cows.getHealth());
			
			// SQL文を実行する
			if (pStmt.executeUpdate() == 1) {
				result = true;
			}
		} catch (SQLException e) {
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
		//結果を返す
		return result;
	}
	
	public boolean update(CowsDto cows) {
		Connection conn = null;
		boolean result = false;
		
		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a4?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			// SQL文を準備する
			String sql = "UPDATE cows_daily SET"
					+ " temperature = ?, appetite = ?, drinking = ?, manure = ?, health = ?"
					+ " WHERE day = ? AND number = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			pStmt.setString(1, cows.getTemperature());
			pStmt.setInt(2, cows.getAppetite());
			pStmt.setInt(3, cows.getDrinking());
			pStmt.setInt(4, cows.getManure());
			pStmt.setInt(5, cows.getHealth());
			pStmt.setString(6, cows.getDay());
			pStmt.setInt(7, cows.getId());

			// SQL文を実行する
			if (pStmt.executeUpdate() == 1) {
				result = true;
			}
		} catch (SQLException e) {
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
		
		//結果を返す
		return result;
	}
	
	public boolean delete(CowsDto cows) {
		Connection conn = null;
		boolean result = false;
		
		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a4?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			// SQL文を準備する
			String sql = "DELETE FROM cows_daily WHERE day = ? AND number = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			// SQL文を完成させる
			pStmt.setString(1, cows.getDay());
			pStmt.setInt(2, cows.getId());

			// SQL文を実行する
			if (pStmt.executeUpdate() == 1) {
				result = true;
			}
		} catch (SQLException e) {
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
		
		
		//結果を返す
		return result;
	}
}
