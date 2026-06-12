package dao;

import java.sql.Connection; // DBとの接続状態を確認、接続させるため
import java.sql.Date;
import java.sql.DriverManager; // データベースへ接続するため窓口用
import java.sql.PreparedStatement; // 実行するSQLをカプセル化するオブジェクト,セキュリティ用
import java.sql.ResultSet;
import java.sql.SQLException; // 接続失敗やエラーをキャッチするための例外クラス
import java.util.ArrayList;
import java.util.List;

import dto.WeatherDto;

public class WeatherDao {
	public boolean insert(WeatherDto wt) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a4"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			// SQL文を準備する
			String sql = "INSERT INTO login VALUES (?,?,?,?,?,?,?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			// util date とsql dateの変換
				pStmt.setDate(1, (java.sql.Date) wt.getDay());
				pStmt.setInt(2, wt.getWeather());
				pStmt.setBigDecimal(3, wt.getHigh_temperature());
				pStmt.setBigDecimal(4, wt.getLow_temperature());
				pStmt.setInt(5, wt.getHumidity());
				pStmt.setBigDecimal(6, wt.getPrecipitation());
				pStmt.setBigDecimal(7, wt.getWindpower());
				
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
	public List<WeatherDto> select(WeatherDto tenki) {
		//接続状態、ログイン結果の初期値
		Connection conn = null;
		List<WeatherDto> weatherList = new ArrayList<WeatherDto>();

		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する　a4データベース、IDは要変更、パスはpassword
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a4?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			// SELECT文を準備
			String sql = "SELECT * FROM weather WHERE day = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setDate(1, (Date) tenki.getDay());

			// SELECT文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();
			
			// 従業員名前と管理者フラグをretuen、エラー時はnullで返す
			while(rs.next()) {	
				System.out.println("入ったよ");
				WeatherDto w = new WeatherDto(
				rs.getDate("day"),
				rs.getInt("weather"),
				rs.getBigDecimal("high_temperature"),
				rs.getBigDecimal("low_temperature"),
				rs.getInt("humidity"),
				rs.getBigDecimal("precipitation"),
				rs.getBigDecimal("windpower")				
				);
			weatherList.add(w);
			}
			System.out.println(weatherList.size());
			//以下、例外処理
		} catch (SQLException e) {
			e.printStackTrace();
			weatherList = null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			weatherList = null;
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					weatherList = null;
				}
			}
		}

		// 結果を返す
		return weatherList;			
	}
}
