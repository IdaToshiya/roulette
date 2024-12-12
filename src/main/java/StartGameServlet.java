import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/StartGameServlet")
public class StartGameServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	System.out.println("aaa");
    	// SQLiteのデータベースパス
        String url = "jdbc:sqlite:C:/Users/7d02/Desktop/admin";

        try {
            // JDBCドライバをロード（必要に応じて）
            Class.forName("org.sqlite.JDBC");

            // データベース接続
            try (Connection conn = DriverManager.getConnection(url)) {
                if (conn != null) {
                    // テーブル作成（存在しない場合のみ）
                    String createTableSQL = "CREATE TABLE IF NOT EXISTS results ("
                    		+ "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    		+ "winning_number TEXT NOT NULL,"
                    		+ "winning_color TEXT NOT NULL,"
                    		+ "date TIMESTAMP DEFAULT CURRENT_TIMESTAMP)";
                    conn.createStatement().execute(createTableSQL);
                }
        	System.out.println("bbb");
            
            // 新しいゲームのエントリを挿入
            String insertGameSQL = """
            		INSERT INTO results (winning_number, winning_color) VALUES ("a", "b")
            		""";
            PreparedStatement pstmt = conn.prepareStatement(insertGameSQL, Statement.RETURN_GENERATED_KEYS);
            pstmt.executeUpdate();
            
            // 自動生成されたIDを取得
            ResultSet keys = pstmt.getGeneratedKeys();
            if (keys.next()) {
                int gameId = keys.getInt(1); // 生成されたゲームIDを取得
                System.out.println(gameId);

                // ゲームIDをセッションに保存
                request.getSession().setAttribute("currentGameId", gameId);
                request.getRequestDispatcher("/BetServlet").forward(request, response);
                
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("エラー: 新しいゲームの開始に失敗しました。");
        }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            response.getWriter().println("JDBCドライバが見つかりません。");
        }
    }
}
