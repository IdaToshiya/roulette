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

@WebServlet("/BetServlet")
public class BetServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String[] betValues = request.getParameterValues("bet");
     // セッションから現在のゲームIDを取得
        Integer gameId = (Integer) request.getSession().getAttribute("currentGameId");

        // SQLiteのデータベースパス
        String url = "jdbc:sqlite:C:/Users/7d02/Desktop/admin";

        try {
            // JDBCドライバをロード（必要に応じて）
            Class.forName("org.sqlite.JDBC");

            // データベース接続
            try (Connection conn = DriverManager.getConnection(url)) {
                if (conn != null) {
                    // テーブル作成（存在しない場合のみ）
                    String createTableSQL = "CREATE TABLE IF NOT EXISTS bets ("
                            + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                            + "game_id INTEGER NOT NULL, "
                            + "bet_value TEXT NOT NULL, "
                            + "user_id TEXT NOT NULL, "
                            + "bet_type TEXT NOT NULL,"
                            + "amount INTEGER NOT NULL,"
                            + "date TIMESTAMP DEFAULT CURRENT_TIMESTAMP)";
                    conn.createStatement().execute(createTableSQL);

                    // データ挿入
                    String insertSQL = "INSERT INTO bets (game_id, bet_value, user_id, bet_type, amount) VALUES (?, ?, ?, ?, ?)";
                    try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
                        for (String betValue : betValues) {
                        	pstmt.setInt(1, gameId);
                            pstmt.setString(2, betValue);
                            pstmt.setString(3, "user_id");
                            pstmt.setString(4, "bet_type");
                            pstmt.setInt(5, 1);
                            pstmt.addBatch();
                        }
                        pstmt.executeBatch();
                    }
                    request.setAttribute("betValues", betValues);
        			request.getRequestDispatcher("test2.jsp").forward(request, response);
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            response.getWriter().println("JDBCドライバが見つかりません。");
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("データベース操作中にエラーが発生しました。");
        }
    }
}
