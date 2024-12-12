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
import jakarta.servlet.http.HttpSession;

@WebServlet("/RouletteServlet")
public class RouletteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // リクエストとレスポンスの文字コードをUTF-8に設定
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String result = request.getParameter("result");
     // セッションから現在のゲームIDを取得
        Integer gameId = (Integer) request.getSession().getAttribute("currentGameId");

        // ログ出力（デバッグ用）
        System.out.println("受け取った結果: " + result);

        if (result == null || result.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("エラー: 結果が送信されていません。");
        }
        
//     // SQLiteのデータベースパス
        String url = "jdbc:sqlite:C:/Users/7d02/Desktop/admin";
        

        try {
            // JDBCドライバをロード（必要に応じて）
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            response.getWriter().println("JDBCドライバが見つかりません。");
        }
        
        // データベース接続
        try (Connection conn = DriverManager.getConnection(url)) {

	            // データ挿入
	            String insertSQL = "UPDATE results SET winning_number = ?, winning_color = ? WHERE id = ?";
	            PreparedStatement pstmt = conn.prepareStatement(insertSQL);
                
                pstmt.setString(1, result);
                pstmt.setString(2, "color");
                pstmt.setInt(3, gameId);
                pstmt.addBatch();
                
                pstmt.executeBatch();      
                
       } catch (SQLException e) {
		// TODO 自動生成された catch ブロック
		e.printStackTrace();
       } 
        
        // セッションスコープに設定
        HttpSession session1 = request.getSession();
        System.out.println("ルーレットの結果を表示するところおおおおおおおおおおおおおお"+result);
        session1.setAttribute("result", result);
        request.getRequestDispatcher("/test2.jsp").forward(request, response);
        
    }
}