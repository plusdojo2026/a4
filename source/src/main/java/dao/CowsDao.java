package dao;

import java.sql.Connection; // DBとの接続状態を確認、接続させるため
import java.sql.DriverManager; // データベースへ接続するため窓口用
import java.sql.PreparedStatement; // 実行するSQLをカプセル化するオブジェクト,セキュリティ用
import java.sql.ResultSet; // SQLの実行結果(レコード)を格納するオブジェクト
import java.sql.SQLException; // 接続失敗やエラーをキャッチするための例外クラス
import java.util.ArrayList;
import java.util.List;

import dto.CowsDto;

public class CowsDao {
	public List<Integer> getCowIdList() {
		Connection conn = null;
		List<Integer> list = new ArrayList<>();
		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// データベースに接続する
			 conn = DriverManager.getConnection(
					 
					// データベースに接続する　a4データベース、IDは要変更、パスはpassword
					 "jdbc:mysql://localhost:3306/a4?"
				      + "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9",
				                "root", "password");
			 String sql = "SELECT id FROM cows ORDER BY id";
			 PreparedStatement pStmt = conn.prepareStatement(sql);
			 
			 //idの全件取得
			 ResultSet rs = pStmt.executeQuery();
			 while (rs.next()) {
			        list.add(rs.getInt("id"));
			    }
		} catch (SQLException | ClassNotFoundException e) {
		    e.printStackTrace();
		    list = null;
		} finally {
		    if (conn != null) try { conn.close(); } catch (SQLException e) {}
		}
		return list;
	}
	
	//検索のメソッド
	public List<CowsDto> select(CowsDto cow) {
	    Connection conn = null;
	    List<CowsDto> list = new ArrayList<>();
	    try {
	    Class.forName("com.mysql.cj.jdbc.Driver");
	    conn = DriverManager.getConnection(
	    	    "jdbc:mysql://localhost:3306/a4?"
	    	    + "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9",
	    	    "root", "password");
	   
	    String sql = "SELECT  id, name, birth_day "
	    		+ "FROM cows "
                + "WHERE id = ? AND name LIKE ? AND birth_day LIKE ? "
                + "ORDER BY id";
	   
	    PreparedStatement pStmt = conn.prepareStatement(sql);//プリペアードステートメント

      
	    // SQL文を完成させる
        if (cow.getId() != 0) {				//IDの検索
            pStmt.setString(1, "%" + cow.getId() + "%");
        } else {
            pStmt.setString(1, "%");
        }

        if (cow.getName() != null) {
            pStmt.setString(2, "%" + cow.getName() + "%");	//名前で検索
        } else {
            pStmt.setString(2, "%");
        }

        if (cow.getBirth_day() != null) {		//誕生日検索
            pStmt.setString(3, "%" + cow.getBirth_day() + "%");
        } else {
            pStmt.setString(3, "%");
        }

        // SQL文を実行し、結果表を取得する
        ResultSet rs = pStmt.executeQuery();

        // 結果表をコレクションにコピーする
        while (rs.next()) {
            CowsDto c = new CowsDto();
            c.setId(rs.getInt("id"));
            c.setName(rs.getString("name"));
            c.setBirth_day(rs.getString("birth_day"));

            list.add(c);
        }

    } catch (SQLException | ClassNotFoundException e) {
        e.printStackTrace();
        list = null;
    } finally {
        // データベースを切断
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
                list = null;
            }
        }
    }

    // 結果を返す
    return list;
}
}
		
		
	

