package dao;

import java.sql.Connection; // DBとの接続状態を確認、接続させるため
import java.sql.DriverManager; // データベースへ接続するため窓口用
import java.sql.PreparedStatement; // 実行するSQLをカプセル化するオブジェクト,セキュリティ用
import java.sql.ResultSet; // SQLの実行結果(レコード)を格納するオブジェクト
import java.sql.SQLException; // 接続失敗やエラーをキャッチするための例外クラス
import java.util.ArrayList;
import java.util.List;

import dto.EmployeesDto;


public class EmployeesDao {
	// login用のselect
	// 引数で指定されたloginUserでログイン成功ならuserListを返す
		public List<EmployeesDto> select(EmployeesDto loginUser) {
			//接続状態、ログイン結果の初期値
			Connection conn = null;
			ArrayList<EmployeesDto> userList = new ArrayList<EmployeesDto>();
			System.out.println(loginUser.getLogin_id()+","+loginUser.getPassword());

			try {
				// JDBCドライバを読み込む
				Class.forName("com.mysql.cj.jdbc.Driver");

				// データベースに接続する　a4データベース、IDは要変更、パスはpassword
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a4?"
						+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
						"root", "password");

				// SELECT文を準備
				String sql = "SELECT * FROM employees WHERE login_id =? AND password=?";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				pStmt.setString(1, loginUser.getLogin_id());
				pStmt.setString(2, loginUser.getPassword());

				// SELECT文を実行し、結果表を取得する
				ResultSet rs = pStmt.executeQuery();
				
				// 従業員名前と管理者フラグをretuen、エラー時はnullで返す
				while(rs.next()) {	
					System.out.println("入ったよ");
					EmployeesDto lu = new EmployeesDto(
					rs.getString("name"),
					rs.getString("admin"),
					//int num分 (コンストラクタ要参照)
					0
					);
				userList.add(lu);
				}
				System.out.println(userList.size());
				//以下、例外処理
			} catch (SQLException e) {
				e.printStackTrace();
				userList = null;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				userList = null;
			} finally {
				// データベースを切断
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						userList = null;
					}
				}
			}

			// 結果を返す
			return userList;			
		}
		// 従業員登録用
		public boolean insert(EmployeesDto emp) {
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
				String sql = "INSERT INTO bc VALUES (0, ?, ?, ?, ?, ?, ?, ?, ?)";
				PreparedStatement pStmt = conn.prepareStatement(sql);

				// SQL文を完成させる
					pStmt.setString(1, emp.getName());
					pStmt.setInt(2, emp.getAge());
					pStmt.setInt(3, emp.getGender());
					pStmt.setString(4, emp.getPhone());
					pStmt.setString(5, emp.getAddress());
					pStmt.setString(6, emp.getAdmin());
					pStmt.setString(7, emp.getLogin_id());
					pStmt.setString(8, emp.getPassword());
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
		// 引数cardで指定されたレコードを更新し、成功したらtrueを返す
		public boolean update(EmployeesDto emp) {
			Connection conn = null;
			boolean result = false;

			try {
				// JDBCドライバを読み込む
				Class.forName("com.mysql.cj.jdbc.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hapyo?"
						+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
						"root", "password");

				// SQL文を準備する
				String sql = "UPDATE bc SET name=?, name_ruby=?, com=?, com_ruby=?, dep=?, p_num=?, mail=?, pos=? WHERE id=?";
				PreparedStatement pStmt = conn.prepareStatement(sql);

				// SQL文を完成させる
				if (meisi.getName() != null) {
					pStmt.setString(1, meisi.getName());
				} else {
					pStmt.setString(1, null);
				}
				if (meisi.getName_ruby() != null) {
					pStmt.setString(2, meisi.getName_ruby());
				} else {
					pStmt.setString(2, null);
				}
				if (meisi.getCom() != null) {
					pStmt.setString(3, meisi.getCom());
				} else {
					pStmt.setString(3, null);
				}
				if (meisi.getCom_ruby() != null) {
					pStmt.setString(4, meisi.getCom_ruby());
				} else {
					pStmt.setString(4, null);
				}
				if (meisi.getDep() != null) {
					pStmt.setString(5, meisi.getDep());
				} else {
					pStmt.setString(5, null);
				}
				if (meisi.getP_num() != null) {
					pStmt.setString(6, meisi.getP_num());
				} else {
					pStmt.setString(6, null);
				}
				if (meisi.getMail() != null) {
					pStmt.setString(7, meisi.getMail());
				} else {
					pStmt.setString(7, null);
				}
				if (meisi.getPos() != null) {
					pStmt.setString(8, meisi.getPos());
				} else {
					pStmt.setString(8, null);
				}
				pStmt.setInt(9, meisi.getId());

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

