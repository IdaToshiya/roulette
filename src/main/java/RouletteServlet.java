import java.io.IOException;

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
    }
}
