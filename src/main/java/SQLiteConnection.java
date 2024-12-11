import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLiteConnection {
    public static void main(String[] args) {
        // SQLiteのデータベースファイルへのパス（新しいデータベースが作成されます）
        String url = "jdbc:sqlite:C:/Users/7d02/Desktop/admin";

        // データベース接続と操作の例
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                // テーブルの作成
                String createTableSQL = "CREATE TABLE IF NOT EXISTS users (id INTEGER PRIMARY KEY, name TEXT)";
                try (Statement stmt = conn.createStatement()) {
                    stmt.execute(createTableSQL);
                    System.out.println("テーブルが作成されました。");

                    // データの挿入
                    String insertSQL = "INSERT INTO users (name) VALUES ('Alice'), ('Bob'), ('Charlie')";
                    stmt.executeUpdate(insertSQL);
                    System.out.println("データが挿入されました。");

                    // データの取得
                    String selectSQL = "SELECT id, name FROM users";
                    ResultSet rs = stmt.executeQuery(selectSQL);

                    // 結果の表示
                    while (rs.next()) {
                        int id = rs.getInt("id");
                        String name = rs.getString("name");
                        System.out.println("ID: " + id + ", 名前: " + name);
                    }
                } catch (SQLException e) {
                    System.out.println("SQLエラー: " + e.getMessage());
                }
            }
        } catch (SQLException e) {
            System.out.println("接続エラー: " + e.getMessage());
        }
    }
}
