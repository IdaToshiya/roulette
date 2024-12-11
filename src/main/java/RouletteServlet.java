import java.io.IOException;

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

//        String result = request.getParameter("result");
//     // セッションから現在のゲームIDを取得
//        Integer gameId = (Integer) request.getSession().getAttribute("currentGameId");
//
//        // ログ出力（デバッグ用）
//        System.out.println("受け取った結果: " + result);
//
//        if (result == null || result.isEmpty()) {
//            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//            response.getWriter().write("エラー: 結果が送信されていません。");
//        }
//        
//     // SQLiteのデータベースパス
//        String url = "jdbc:sqlite:C:/Users/7d02/Desktop/admin";
//        
//
//        try {
//            // JDBCドライバをロード（必要に応じて）
//            Class.forName("org.sqlite.JDBC");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//            response.getWriter().println("JDBCドライバが見つかりません。");
//        }
//        
//        // データベース接続
//        try (Connection conn = DriverManager.getConnection(url)) {
//
//	            // データ挿入
//	            String insertSQL = "UPDATE results SET winning_number = ?, winning_color = ? WHERE id = ?";
//	            PreparedStatement pstmt = conn.prepareStatement(insertSQL);
//                
//                pstmt.setString(1, result);
//                pstmt.setString(2, "color");
//                pstmt.setInt(3, gameId);
//                pstmt.addBatch();
//                
//                pstmt.executeBatch();
//            
//             // 2. 最新のゲームIDに関連するベットを取得
//                String selectBetsSQL = "SELECT * FROM bets WHERE game_id = ?";
//                try (PreparedStatement pstmt1 = conn.prepareStatement(selectBetsSQL)) {
//                    pstmt1.setInt(1, gameId);
//                    ResultSet rs = pstmt1.executeQuery();
//
//                    // 照合と結果作成
//                    StringBuilder resultMessage = new StringBuilder();
//                    while (rs.next()) {
////                        String betType = rs.getString("bet_type");
//                        String betValue = rs.getString("bet_value");
////                        int amount = rs.getInt("amount");
//
//                        boolean isWin = false;
//
//                        if (betValue.equals(result)) {
//                            isWin = true;
//                        } 
//
//                        if (isWin) {
//                            resultMessage.append(String.format("ベット '%s' に当たり!", betValue));
//                            System.out.println(resultMessage);
//                        } else {
//                            resultMessage.append(String.format("ベット '%s' は外れ...\n", betValue));
//                            System.out.println(resultMessage);
//                        }
//                    }
     // セッションに結果を設定
        String result = "some result"; // 例: 結果の値
        HttpSession session = request.getSession();
        session.setAttribute("result", result);

        // リダイレクト先に送る
        response.sendRedirect("test2.jsp");

                }

//        } catch (Exception e) {
//            e.printStackTrace();
//            response.getWriter().println("エラー: 結果の保存または照合に失敗しました。");
//        }
        
//    }
}