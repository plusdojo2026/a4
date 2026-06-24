package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.ShiftDto;

public class ShiftDao {
	
	// 引数shift 指定された項目で検索して、取得されたデータのリストを返す
		public List<ShiftDto> select(ShiftDto shift) {
			Connection conn = null;
			//ArrayListにはShiftDtoの中身を入れると設定
			ArrayList<ShiftDto> shiftList = new ArrayList<ShiftDto>();
		
			try {
				// JDBCドライバを読み込む
				Class.forName("com.mysql.cj.jdbc.Driver");

				// データベースに接続する　a4データベース、IDは要変更、パスはpassword
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a4?"
						+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
						"a4", "HHi3Pi8a3jL74W9d");

				PreparedStatement pStmt;
				
				//nullなら全件取得
				if (shift == null) {

				    String sql = "SELECT id, intime, day FROM shift";
				    pStmt = conn.prepareStatement(sql);

				    //条件検索
				} else {

				    String sql =
				        "SELECT id, intime, day FROM shift WHERE id = ? AND day = ?";
				    pStmt = conn.prepareStatement(sql);

				    pStmt.setInt(1, shift.getId());
				    pStmt.setString(2, shift.getDate());
				}

				// SQL実行
				ResultSet rs = pStmt.executeQuery();
				
				// 結果表をコレクションにコピーする
				//空の枝豆の殻shiftdtoに枝豆の中身を取り出して格納していく
				while (rs.next()) {
					ShiftDto shiftdto = new ShiftDto(
							rs.getInt("id"),
							rs.getInt("intime"),
							rs.getString("day")
						);
					shiftList.add(shiftdto);
				}
				
				System.out.println(shiftList.size());
				//以下、例外処理
			} catch (SQLException e) {
				e.printStackTrace();
				shiftList = null;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				shiftList = null;
			} finally {
				// データベースを切断
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						shiftList = null;
					}
				}
			}

			// 結果を返す
			return shiftList;			
		}
		
		// 引数shiftで指定されたレコードを登録し、成功したらtrueを返す
		public boolean insert(ShiftDto shift) {
			Connection conn = null;
			boolean result = false;

			try {
				// JDBCドライバを読み込む
				Class.forName("com.mysql.cj.jdbc.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a4?"
						+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
						"a4", "HHi3Pi8a3jL74W9d");

				// SQL文を準備する
				String sql = "INSERT INTO shift (id, intime, day) VALUES (?, ?, ?)";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				
				pStmt.setInt(1, shift.getId());
				pStmt.setInt(2, shift.getIntime());
				pStmt.setString(3, shift.getDate());
				
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

			// 結果を返す
			return result;
		}
		// 引数shiftで指定されたレコードを更新し、成功したらtrueを返す
		public boolean update(ShiftDto shift) {
			Connection conn = null;
			boolean result = false;

			try {
				// JDBCドライバを読み込む
				Class.forName("com.mysql.cj.jdbc.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a4?"
						+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
						"a4", "HHi3Pi8a3jL74W9d");

				// SQL文を準備する
				String sql = "UPDATE shift SET intime = ? WHERE id = ? AND day = ?";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				
				pStmt.setInt(1, shift.getIntime());
				pStmt.setInt(2, shift.getId());
				pStmt.setString(3, shift.getDate());

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

			// 結果を返す
			return result;
		}
		
		// 引数shiftで指定された番号のレコードを削除し、成功したらtrueを返す
		public boolean delete(ShiftDto shift) {
			Connection conn = null;
			boolean result = false;

			try {
				// JDBCドライバを読み込む
				Class.forName("com.mysql.cj.jdbc.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a4?"
						+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
						"a4", "HHi3Pi8a3jL74W9d");

				// SQL文を準備する
				// 従業員idと日付に一致するシフトレコードを削除
				String sql = "DELETE FROM shift WHERE id = ? AND day = ?";
				PreparedStatement pStmt = conn.prepareStatement(sql);

				// SQL文を完成させる
				pStmt.setInt(1, shift.getId());
				pStmt.setString(2, shift.getDate());

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

			// 結果を返す
			return result;
		}
	}
