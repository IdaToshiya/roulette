import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/RouletteServlet")
public class RouletteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // リクエストとレスポンスの文字コードをUTF-8に設定
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String result = request.getParameter("result");

        // ログ出力（デバッグ用）
        System.out.println("受け取った結果: " + result);

        if (result == null || result.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("エラー: 結果が送信されていません。");
        } else {
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("結果がサーバーに保存されました: " + result);
        }
        
     // SQLiteのデータベースパス
        String url = "jdbc:sqlite:C:/Users/7d02/Desktop/admin";

            // データベース接続
            try (Connection conn = DriverManager.getConnection(url)) {
                if (conn != null) {
                    // テーブル作成（存在しない場合のみ）
                    String createTableSQL = "CREATE TABLE IF NOT EXISTS results ("
                    		+ "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    		+ "winning_number TEXT NOT NULL,"
                    		+ "winning_color TEXT NOT NULL,"
                    		+ "date TIMESTAMP DEFAULT CURRENT_TIMESTAMP);";
                    conn.createStatement().execute(createTableSQL);
                }
            

                // データ挿入
                String insertSQL = "INSERT INTO results (winning_number, winning_color) VALUES (?, ?)";
                try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
                    
                    pstmt.setString(1, result);
                    pstmt.setString(2, "color");
                    pstmt.addBatch();
                    
                    pstmt.executeBatch();
                
	                // 勝敗を判定
	                String betSql = "SELECT * FROM bets WHERE bet_value = ? OR bet_value = ?";
	                PreparedStatement betStmt = conn.prepareStatement(betSql);
	                betStmt.setString(1, result);
	                betStmt.setString(2, "color");
	
	                var resultSet = betStmt.executeQuery();
	                StringBuilder resultMessage = new StringBuilder("結果:\n");
	
	                while (resultSet.next()) {
	                    resultMessage.append("ユーザー: ").append(resultSet.getString("user_id"))
	                                 .append(", 勝利ベット: ").append(resultSet.getString("bet_value"))
	                                 .append(", 金額: ").append(resultSet.getInt("amount")).append("\n");
	                }

	                response.getWriter().println(resultMessage.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                    response.getWriter().println("エラー: 結果の保存または照合に失敗しました。");
                }
            } catch (SQLException e1) {
				// TODO 自動生成された catch ブロック
				e1.printStackTrace();
			}
    }
}