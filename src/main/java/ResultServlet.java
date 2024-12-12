import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/ResultServlet")
public class ResultServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	String result = (String) request.getSession().getAttribute("result");
        Integer gameId = (Integer) request.getSession().getAttribute("currentGameId");
        System.out.println("aaaaaaaaaaaaaaaa"+result);
        System.out.println("aaaaaaaaaaaaaaaa"+gameId);
       
    	String url = "jdbc:sqlite:C:/Users/7d02/Desktop/admin";
    	Connection conn;
		try {
			conn = DriverManager.getConnection(url);
			
			 // 2. 最新のゲームIDに関連するベットを取得
	        String selectBetsSQL = "SELECT * FROM bets WHERE game_id = ?";
	        try (PreparedStatement pstmt1 = conn.prepareStatement(selectBetsSQL)) {
	            pstmt1.setInt(1, gameId);
	            ResultSet rs = pstmt1.executeQuery();

	            // 照合と結果作成
	            StringBuilder resultMessage = new StringBuilder();
	            while (rs.next()) {
	                String betType = rs.getString("bet_type");
	                String betValue = rs.getString("bet_value");
	                int amount = rs.getInt("amount");

	                boolean isWin = false;

	                if (betValue.equals(result)) {
	                    isWin = true;
	                }

	                if (isWin) {
	                    resultMessage.append(String.format("ベット '%s' に当たり!\n", betValue));
	                } else {
	                    resultMessage.append(String.format("ベット '%s' は外れ...\n", betValue));
	                }
	            }

	            // セッションに格納するのはループが終わった後
	            HttpSession session1 = request.getSession();
	            session1.setAttribute("resultMessage", resultMessage.toString());
	            request.getRequestDispatcher("/test2.jsp").forward(request, response);

	                
            }catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
            }
         
        } catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
    }
}