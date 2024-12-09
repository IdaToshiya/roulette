import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/RouletteServlet")
public class RouletteServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // リクエストの内容を取得
        StringBuilder jsonData = new StringBuilder();
        try (BufferedReader reader = request.getReader()) {
            String line;
            while ((line = reader.readLine()) != null) {
                jsonData.append(line);
            }
        }

        // JSONデータを解析 (GsonやJacksonを使わない方法)
        String winningNumber = null;
        String jsonString = jsonData.toString();
        if (jsonString.contains("\"winningNumber\"")) {
            winningNumber = jsonString.split(":")[1].replace("}", "").replace("\"", "").trim();
        }

        // データの処理 (例: データベースに保存するなど)
        if (winningNumber != null) {
            System.out.println("受け取った当選番号: " + winningNumber);
        } else {
            System.err.println("当選番号の解析に失敗しました");
        }

        // レスポンスを返す
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            if (winningNumber != null) {
                out.print("{\"message\": \"結果が保存されました: " + winningNumber + "\"}");
            } else {
                out.print("{\"message\": \"結果の保存に失敗しました\"}");
            }
            out.flush();
        }
    }
}
