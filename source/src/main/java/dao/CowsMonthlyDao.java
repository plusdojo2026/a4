package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.CowsDto;

public class CowsMonthlyDao {
    //betweenを使って月を絞り込む
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
			String sql = "SELECT number, weight, milkquality, bacterial_count,"
					+ " milk_fat_content, somatic_cell_count, day, temperature"
					+ " FROM cows_monthly WHERE day = ? AND number = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			pStmt.setString(1, cows.getDay());
			pStmt.setInt(2, cows.getId()); 

			// SELECT文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();
			
			// 結果表をコレクションにコピーする
			//空の枝豆の殻Cowsdtoに枝豆の中身を取り出して格納していく
			while (rs.next()) {
				CowsDto cowsdto = new CowsDto(
						rs.getInt("number"),              // id
						rs.getString("day"),              // 日付
						rs.getBigDecimal("weight"),       //体重
						rs.getInt("milkquality"),         ///乳の質
						rs.getBigDecimal("bacterial_count"), //細菌数
						rs.getBigDecimal("milk_fat_content"),//乳脂肪分
						rs.getInt("somatic_cell_count")// 体細胞数
						
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
			String sql = "INSERT INTO cows_monthly (number, weight, milkquality,"
					+ " bacterial_count, milk_fat_content, somatic_cell_count, day, temperature)"
					+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			pStmt.setInt(1, cows.getId());
			pStmt.setBigDecimal(2, cows.getWeight());
			pStmt.setInt(3, cows.getMilkquality());
			pStmt.setBigDecimal(4, cows.getBacterialCount());
			pStmt.setBigDecimal(5, cows.getMilk_fat_content());
			pStmt.setInt(6, cows.getSomatic_cell_count());
			pStmt.setString(7, cows.getDay());
			pStmt.setString(8, cows.getTemperature());
			
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
		    String sql = "UPDATE cows_monthly SET weight = ?, milkquality = ?, bacterial_count = ?,"
		    		+ " milk_fat_content = ?, somatic_cell_count = ?, temperature = ?"
		    		+ " WHERE day = ? AND number = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			pStmt.setBigDecimal(1, cows.getWeight());
			pStmt.setInt(2, cows.getMilkquality());
			pStmt.setBigDecimal(3, cows.getBacterialCount());
			pStmt.setBigDecimal(4, cows.getMilk_fat_content());
			pStmt.setInt(5, cows.getSomatic_cell_count());
			pStmt.setString(6, cows.getTemperature());
			pStmt.setString(7, cows.getDay());
			pStmt.setInt(8, cows.getId());

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
			String sql = "DELETE FROM cows_monthly WHERE day = ? AND number = ?";
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
