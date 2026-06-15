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
	}

