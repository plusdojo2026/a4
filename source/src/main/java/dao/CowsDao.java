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
	public List<String> getCowIdList() {
		Connection conn = null;
		List<String> list = new ArrayList<>();
		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection(

					// データベースに接続する a4データベース、IDは要変更、パスはpassword
					// idをnumberに変えた
					"jdbc:mysql://localhost:3306/a4?" + "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9",
					"a4", "HHi3Pi8a3jL74W9d");
			String sql = "SELECT id FROM cows ORDER BY id";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// idの全件取得←//idをnumberに変えた
			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {
				list.add(rs.getString("id"));
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			list = null;
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}
		return list;
	}
	//JSPから送られてきたnumberからIDを取得するメソッド
	public int getNumberById(String id) {
		
		//取得したnumberをいれる
	    int number = 0;
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        Connection conn = DriverManager.getConnection(
	            "jdbc:mysql://localhost:3306/a4?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9",
	            "a4", "HHi3Pi8a3jL74W9d");

	        //Cowｓテーブルからｎｕｍｂｅｒを取得
	        String sql = "SELECT number FROM cows WHERE id = ?";
	        PreparedStatement ps = conn.prepareStatement(sql);
	        ps.setString(1, id);

	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	            number = rs.getInt("number");
	        }

	        conn.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return number;
	}


	// 検索のメソッド
	// 【名称変更】条件を指定してウシデータを検索する
	public List<CowsDto> search(CowsDto cow) {
		Connection conn = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		List<CowsDto> list = new ArrayList<>();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/a4?" + "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9",
					"a4", "HHi3Pi8a3jL74W9d");

			// ※SELECT句に主キー（number）やその他の必要なカラムも含めて取得するのが一般的です
			String sql = "SELECT number, id, name, gender, birth_day, status, photo FROM cows "
					+ "WHERE id LIKE ? AND name LIKE ? AND birth_day LIKE ? " + "ORDER BY number";

			pStmt = conn.prepareStatement(sql);

			// 1. IDの検索 (cows.getId()はStringを返すため、nullおよび空文字チェックに変更)
			if (cow.getId() != null && !cow.getId().isEmpty()) {
				pStmt.setString(1, "%" + cow.getId() + "%");
			} else {
				pStmt.setString(1, "%");
			}

			// 2. 名前で検索
			if (cow.getName() != null && !cow.getName().isEmpty()) {
				pStmt.setString(2, "%" + cow.getName() + "%");
			} else {
				pStmt.setString(2, "%");
			}

			// 3. 誕生日検索
			if (cow.getBirth_day() != null && !cow.getBirth_day().isEmpty()) {
				pStmt.setString(3, "%" + cow.getBirth_day() + "%");
			} else {
				pStmt.setString(3, "%");
			}

			// SQL文を実行し、結果表を取得する
			rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while (rs.next()) {
				// 一覧表示の際に編集や詳細へのリンクを貼れるよう、主キー(number)も含めてDtoにセットします
				// 7つの引数を持つコンストラクタが定義されているため、それを利用してインスタンス化します
				CowsDto c = new CowsDto(rs.getInt("number"), rs.getString("id"), // テーブルに合わせてgetStringで取得
						rs.getString("name"), rs.getInt("gender"), rs.getString("birth_day"),
						String.valueOf(rs.getInt("status")), // IntからStringに変換
						rs.getString("photo"));

				list.add(c);
			}

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			// 途中でエラーが発生しても呼び出し元でヌルポ(NullPointerException)が起きないよう、
			// 空のリストを返すか、例外をスローするのが一般的ですが、元の挙動に合わせてnullをセットしています
			list = null;
		} finally {
			// データベースのリソースを切断（rsとpStmtのクローズを追加）
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
				}
			}
			if (pStmt != null) {
				try {
					pStmt.close();
				} catch (SQLException e) {
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					list = null;
				}
			}
		}

		return list;
	}

	// ウシデータの登録用のInsert文
	public boolean insert(CowsDto cows) {
		Connection conn = null;
		PreparedStatement pStmt = null; // 確実にクローズするためにここに宣言
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a4?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"a4", "HHi3Pi8a3jL74W9d");

			// SQL文を準備する 自動昨晩されるのでnumberのインサートは除外
			String sql = "INSERT INTO cows (id, name, gender, birth_day, status, photo, updatedate, cause, regist_day)"
					+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			pStmt = conn.prepareStatement(sql);

			pStmt.setString(1, String.valueOf(cows.getId())); // id: VARCHAR(10)
			pStmt.setString(2, cows.getName()); // name: VARCHAR(20)
			pStmt.setInt(3, cows.getGender()); // gender: INT
			pStmt.setString(4, cows.getBirth_day()); // birth_day: DATE
			pStmt.setInt(5, Integer.parseInt(cows.getStatus())); // status INT
			pStmt.setString(6, cows.getPhoto()); // photo: VARCHAR(50)
			pStmt.setString(7, cows.getUpdatedate()); // updatedate: DATE
			pStmt.setString(8, cows.getCause()); // cause: VARCHAR(20)
			pStmt.setString(9, cows.getRegist_day()); // regist_day: DATE

			// SQL文を実行する
			if (pStmt.executeUpdate() == 1) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			// データベースのリソースを切断（PreparedStatementもクローズ）
			if (pStmt != null) {
				try {
					pStmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
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

	// ウシの一覧を全件取得する
	public ArrayList<CowsDto> selectAll(CowsDto cows) {
		Connection conn = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		ArrayList<CowsDto> list = new ArrayList<>();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a4?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"a4", "HHi3Pi8a3jL74W9d");

			String sql = "SELECT * FROM cows";
			pStmt = conn.prepareStatement(sql);

			rs = pStmt.executeQuery();

			while (rs.next()) {
				CowsDto dto1 = new CowsDto(rs.getInt("number"), // 第1引数: int number
						rs.getString("id"), // id: VARCHAR(10)
						rs.getString("name"), // 名前
						rs.getInt("gender"), // 性別
						rs.getString("birth_day"), // 生年月日
						String.valueOf(rs.getInt("status")), // status: IntをStringに変換
						rs.getString("photo") // 写真
				);
				list.add(dto1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
				}
			}
			if (pStmt != null) {
				try {
					pStmt.close();
				} catch (SQLException e) {
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return list;
	}

	// 牛のnumberを指定して1件取得する
	public CowsDto selectByNum(CowsDto searchCow) {
		Connection conn = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		CowsDto dto = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/a4?" + "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9",
					"a4", "HHi3Pi8a3jL74W9d");

			String sql = "SELECT * FROM cows WHERE number = ?";
			pStmt = conn.prepareStatement(sql);

			// number（int型）をセットする
			pStmt.setInt(1, searchCow.getNumber());

			rs = pStmt.executeQuery();

			if (rs.next()) {
				dto = new CowsDto(rs.getInt("number"), rs.getString("id"), rs.getString("name"), rs.getInt("gender"),
						rs.getString("birth_day"), String.valueOf(rs.getInt("status")), rs.getString("photo"),
						rs.getString("updatedate"), rs.getString("cause"), rs.getString("regist_day"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
				}
			}
			if (pStmt != null) {
				try {
					pStmt.close();
				} catch (Exception e) {
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		return dto;
	}

	// 更新処理
	public boolean update(CowsDto cows) {
		Connection conn = null;
		PreparedStatement pStmt = null;
		boolean result = false;
		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a4?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"a4", "HHi3Pi8a3jL74W9d");

			// SQL文を準備する（主キーのnumberでレコードを特定する）
			String sql = "UPDATE cows SET id=?, name=?, gender=?, birth_day=?, status=?, photo=?, updatedate=?, cause=?, regist_day=? WHERE number=?";
			pStmt = conn.prepareStatement(sql);

			// 1. ID
			pStmt.setString(1, cows.getId());

			// 2. 名前
			if (cows.getName() != null && !cows.getName().isEmpty()) {
				pStmt.setString(2, cows.getName());
			} else {
				pStmt.setString(2, null);
			}

			// 3. 性別
			pStmt.setInt(3, cows.getGender());

			// 4. 生年月日
			if (cows.getBirth_day() != null && !cows.getBirth_day().isEmpty()) {
				pStmt.setString(4, cows.getBirth_day());
			} else {
				pStmt.setNull(4, java.sql.Types.DATE);
			}

			// 5. ステータス
			if (cows.getStatus() != null && !cows.getStatus().isEmpty()) {
				pStmt.setInt(5, Integer.parseInt(cows.getStatus()));
			} else {
				pStmt.setNull(5, java.sql.Types.INTEGER);
			}

			// 6. 写真
			if (cows.getPhoto() != null && !cows.getPhoto().isEmpty()) {
				pStmt.setString(6, cows.getPhoto());
			} else {
				pStmt.setString(6, null);
			}

			// 7. 更新日
			if (cows.getUpdatedate() != null && !cows.getUpdatedate().isEmpty()) {
				pStmt.setString(7, cows.getUpdatedate());
			} else {
				pStmt.setNull(7, java.sql.Types.DATE);
			}

			// 8. 理由
			if (cows.getCause() != null && !cows.getCause().isEmpty()) {
				pStmt.setString(8, cows.getCause());
			} else {
				pStmt.setString(8, null);
			}

			// 9. 登録日
			if (cows.getRegist_day() != null && !cows.getRegist_day().isEmpty()) {
				pStmt.setString(9, cows.getRegist_day());
			} else {
				pStmt.setNull(9, java.sql.Types.DATE);
			}

			// 10. WHERE句の条件（主キーであるnumberを指定してレコードを特定する）
			pStmt.setInt(10, cows.getNumber());

			if (pStmt.executeUpdate() == 1) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (pStmt != null) {
				try {
					pStmt.close();
				} catch (SQLException e) {
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return result;
	}

	// 削除処理
	public boolean delete(CowsDto cows) {
		Connection conn = null;
		PreparedStatement pStmt = null; // クローズ漏れ防止のためここに宣言
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a4?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"a4", "HHi3Pi8a3jL74W9d");

			// SQL文を準備する（主キーのnumberでレコードを特定して削除）
			String sql = "DELETE FROM cows WHERE number = ?";
			pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる（cowsからnumberを取得してセット）
			pStmt.setInt(1, cows.getNumber());

			// SQL文を実行する
			if (pStmt.executeUpdate() == 1) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			// データベースのリソースを切断
			if (pStmt != null) {
				try {
					pStmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
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
