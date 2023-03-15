package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {
    private static final String URL_DB = "jdbc:mysql://localhost/java2_store";
    private static final String USER_DB = "root";

    private static final String PASSWORD_DB ="dinhtheanh998";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL_DB, USER_DB, PASSWORD_DB);
    }
}
