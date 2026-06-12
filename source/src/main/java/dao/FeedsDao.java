package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class FeedsDao {

	// 引数card指定された項目で検索して、取得されたデータのリストを返す
	public List<FeedsDto> select(FeedsDto feeds) {
		List<FeedsDto> feedsAmount = new ArrayList<FeedsDto>();
		
		//接続状態
		Connection conn = null;
		
		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a4?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			// SQL文を準備する
			String sql = "SELECT * FROM feeds WHERE increase_amount =? AND decrease_amount=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, feeds.getIncrease_amount());
			pStmt.setString(2, feeds.getDecrease_amount());
			
			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while (rs.next()) {
				FeedsDto feeds = new FeedsDto(
						rs.getInt("feed_id"), 
						rs.getDate("date"),
						rs.getInt("increase_amount"),
						rs.getInt("decrease_amount")
						);
				feedsAmount.add(feeds);
			}
			
			//以下、例外処理
		} catch (SQLException e) {
			e.printStackTrace();
			feedsAmount = null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			feedsAmount = null;
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					feedsAmount = null;
				}
			}
		}

		// 結果を返す
		return feedsAmount;
	}
	
	
	
	
	
	
	
	
｝