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
                + "WHERE id like ? AND name LIKE ? AND birth_day LIKE ? "
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
	
	//ウシデータの登録用のInsert文
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
			String sql = "INSERT INTO cows (number, id, name, gender, birth_day, status, photo, updatedate, cause, regist_day)"
					+ " VALUES (0, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			pStmt.setInt(1, cows.getId());
			pStmt.setString(2, cows.getName()); 
			pStmt.setInt(3, cows.getGender()); 
			pStmt.setString(4, cows.getBirth_day());
			pStmt.setString(5, cows.getStatus());
			pStmt.setString(6, cows.getPhoto());
			pStmt.setString(7, cows.getUpdatedate());
			pStmt.setString(8, cows.getCause());
			pStmt.setString(9, cows.getRegist_day());
			
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
	
	//ウシの一覧のselect文
	public ArrayList<CowsDto> select2 (CowsDto cows){
		Connection conn = null;
		//ArrayListで箱を用意する
		//<>のなかは参照型のみ　入れるものは制約
		ArrayList<CowsDto> list = new ArrayList<>();
		CowsDto dto = new CowsDto();
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// データベースに接続する　
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a4?"
			+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
			"root","password");
			
			String sql = "SELECT id,name,gender,birth_day,status from cows";
			
			PreparedStatement pStmt =conn.prepareStatement(sql);
			
			pStmt.setInt(1, cows.getId());
			pStmt.setString(2, cows.getName()); 
			pStmt.setInt(3, cows.getGender()); 
			pStmt.setString(4, cows.getBirth_day());
			pStmt.setString(5, cows.getStatus());
			
			ResultSet rs = pStmt.executeQuery();
			
			//whileでrs.nextを条件になくなるまでlistに追加する
			while(rs.next()) {
				CowsDto dto1 = new CowsDto(
					rs.getInt("id"),           // id
					rs.getString("name"),      //名前
					rs.getInt("gender"),       //性別
					rs.getString("birth_day"), //生年月日
					rs.getString("status")     //生死
				);
				list.add(dto1);	
			}
				
			}catch (SQLException e) {
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
		
		return list;
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
				String sql = "INSERT INTO employees VALUES (0, ?, ?, ?, ?, ?, ?, ?, ?)";
				PreparedStatement pStmt = conn.prepareStatement(sql);

				if (cows.getName() != null) {
					pStmt.setString(1, cows.getName());
				} else {
					pStmt.setString(1, null);
				}
				if (cows.getGender() != 0) {
					pStmt.setInt(2, cows.getGender());
				} else {
					pStmt.setString(2, null);
				}
				if (cows.getBirth_day() != null) {
					pStmt.setString(3, cows.getBirth_day());
				} else {
					pStmt.setString(3, null);
				}
				if (cows.getStatus() != null) {
					pStmt.setString(4, cows.getStatus());
				} else {
					pStmt.setString(4, null);
				}
				if (cows.getPhoto() != null) {
					pStmt.setString(5, cows.getPhoto());
				} else {
					pStmt.setString(5, null);
				}
				if (cows.getUpdatedate() != null) {
					pStmt.setString(6, cows.getUpdatedate());
				} else {
					pStmt.setString(6, null);
				}
				if (cows.getCause() != null) {
					pStmt.setString(7, cows.getCause());
				} else {
					pStmt.setString(7, null);
				}
				if (cows.getRegist_day() != null) {
					pStmt.setString(8, cows.getRegist_day());
				} else {
					pStmt.setString(8, null);
				}
				
					pStmt.setInt(9, cows.getId());
				
					if(pStmt.executeUpdate() == 1) {
						result = true;
					}			
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			
			if(conn != null) {
				try {
					conn.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return false;
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
				String sql = "DELETE FROM cows WHERE id=?";
				PreparedStatement pStmt = conn.prepareStatement(sql);

				// SQL文を完成させる
				pStmt.setInt(1, cows.getId());

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
				return false;
	}
}
	

		
		
	

