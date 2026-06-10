package dao;

import java.sql.Connection; // DBとの接続状態を確認、接続させるため
import java.sql.DriverManager; // データベースへ接続するため窓口用
import java.sql.PreparedStatement; // 実行するSQLをカプセル化するオブジェクト,セキュリティ用
import java.sql.ResultSet; // SQLの実行結果(レコード)を格納するオブジェクト
import java.sql.SQLException; // 接続失敗やエラーをキャッチするための例外クラス

import dto.EmployeesDto;

public class EmployeesDao {
	// 引数で指定されたidpwでログイン成功ならloginResultを返す
		public EmployeesDto isLoginOK(EmployeesDto inputData) {
			//接続状態、ログイン結果の初期値
			Connection conn = null;
			EmployeesDto loginResult = null;

			try {
				// JDBCドライバを読み込む
				Class.forName("com.mysql.cj.jdbc.Driver");

				// データベースに接続する　A4データベース、IDは要変更、パスはpassword
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a4?"
						+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
						"root", "password");

				// SELECT文を準備
				String sql = "SELECT * FROM employees WHERE login_id =? AND password=?";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				pStmt.setString(1, inputData.getLogin_id());
				pStmt.setString(2, inputData.getPassword());

				// SELECT文を実行し、結果表を取得する
				ResultSet rs = pStmt.executeQuery();
				
				// 従業員名前と管理者フラグをretuen、エラー時はnullで返す
				if (rs.next()) {	
					loginResult = new EmployeesDto();
					loginResult.setName(rs.getString("name"));
				    loginResult.setAdminstrator(rs.getString("adminstrator"));
				}else {
					loginResult = null;
				}
				//以下、例外処理
			} catch (SQLException e) {
				e.printStackTrace();
				loginResult = null;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				loginResult = null;
			} finally {
				// データベースを切断
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
						loginResult = null;
					}
				}
			}

			// 結果を返す
			return loginResult;			
		}
	}

