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
		public List<EmployeesDto> select1(EmployeesDto loginUser) {
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
					0 //int num分 (コンストラクタ要参照)
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
		// 従業員検索用
		public List<EmployeesDto> select2(EmployeesDto loginEmp) {
			//接続状態、全検索結果の初期値
			Connection conn = null;
			ArrayList<EmployeesDto> empList = new ArrayList<EmployeesDto>();

			try {
				// JDBCドライバを読み込む
				Class.forName("com.mysql.cj.jdbc.Driver");

				// データベースに接続する　a4データベース、IDは要変更、パスはpassword
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a4?"
						+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
						"root", "password");

				// SELECT文を準備
				String sql = "SELECT * FROM employees ";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				// SELECT文を実行し、結果表を取得する
				ResultSet rs = pStmt.executeQuery();
				
				// 従業員名前と管理者フラグをretuen、エラー時はnullで返す
				while(rs.next()) {	
					System.out.println("入ったよ");
					EmployeesDto emp = new EmployeesDto(
					rs.getInt("id"),
					rs.getString("name"),
					rs.getInt("gender"),
					rs.getString("phone"),
					rs.getString("address")
					);
				empList.add(emp);
				}
				System.out.println(empList.size());
				//以下、例外処理
			} catch (SQLException e) {
				e.printStackTrace();
				empList = null;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				empList = null;
			} finally {
				// データベースを切断
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						empList = null;
					}
				}
			}

			// 結果を返す
			return empList;			
		}
		//従業員変更準備用
		public List<EmployeesDto> select3(EmployeesDto searchEmp) {
			//接続状態、全検索結果の初期値
			Connection conn = null;
			ArrayList<EmployeesDto> empUdList = new ArrayList<EmployeesDto>();

			try {
				// JDBCドライバを読み込む
				Class.forName("com.mysql.cj.jdbc.Driver");

				// データベースに接続する　a4データベース、IDは要変更、パスはpassword
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a4?"
						+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
						"root", "password");

				// SELECT文を準備
				String sql = "SELECT * FROM employees where id = ?";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				//？に値を入れてsql文を完成させる
				pStmt.setInt(1, searchEmp.getId());
				// SELECT文を実行し、結果表を取得する
				ResultSet rs = pStmt.executeQuery();
				
				// 従業員名前と管理者フラグをretuen、エラー時はnullで返す
				while(rs.next()) {	
					System.out.println("select3");
					EmployeesDto ud = new EmployeesDto(
					rs.getInt("id"),
					rs.getString("name"),
					rs.getInt("age"),
					rs.getInt("gender"),
					rs.getString("phone"),
					rs.getString("address"),
					rs.getString("admin"),
					rs.getString("login_id"),
					rs.getString("password")
					);
				empUdList.add(ud);
				}
				System.out.println(empUdList.size());
				//以下、例外処理
			} catch (SQLException e) {
				e.printStackTrace();
				empUdList = null;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				empUdList = null;
			} finally {
				// データベースを切断
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						empUdList = null;
					}
			}
			}
			return empUdList;
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
				String sql = "INSERT INTO employees VALUES (0, ?, ?, ?, ?, ?, ?, ?, ?)";
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
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a4?"
						+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
						"root", "password");

				// SQL文を準備する
				String sql = "UPDATE employees SET id=?, name=?, age=?, gender=?, phone=?, address=?, admin=?, login_id=?, password=? WHERE id=?";
				PreparedStatement pStmt = conn.prepareStatement(sql);

				// SQL文を完成させる
				if (emp.getId() != 0) {
					pStmt.setInt(1, emp.getId());
				} else {
					pStmt.setInt(1, 0);
				}
				if (emp.getName() != null) {
					pStmt.setString(2, emp.getName());
				} else {
					pStmt.setString(2, null);
				}
				if (emp.getAge() != 0) {
					pStmt.setInt(3, emp.getAge());
				} else {
					pStmt.setInt(3, 0);
				}
				if (emp.getGender() != 0) {
					pStmt.setInt(4, emp.getGender());
				} else {
					pStmt.setInt(4, 0);
				}
				if (emp.getPhone() != null) {
					pStmt.setString(5, emp.getPhone());
				} else {
					pStmt.setString(5, null);
				}
				if (emp.getAddress() != null) {
					pStmt.setString(6, emp.getAddress());
				} else {
					pStmt.setString(6, null);
				}
				if (emp.getAdmin() != null) {
					pStmt.setString(7, emp.getAdmin());
				} else {
					pStmt.setString(7, null);
				}
				if (emp.getLogin_id() != null) {
					pStmt.setString(8, emp.getLogin_id());
				} else {
					pStmt.setString(8, null);
				}
				if (emp.getPassword() != null) {
					pStmt.setString(9, emp.getPassword());
				} else {
					pStmt.setString(9, null);
				}
				pStmt.setInt(10, emp.getId());

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
		// 引数cardで指定された番号のレコードを削除し、成功したらtrueを返す
		public boolean delete(EmployeesDto emp) {
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
				String sql = "DELETE FROM employees WHERE id=?";
				PreparedStatement pStmt = conn.prepareStatement(sql);

				// SQL文を完成させる
				pStmt.setInt(1, emp.getId());

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

