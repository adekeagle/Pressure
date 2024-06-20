package pl.adcom;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static DBConnection instance;

    private final static String URL = "jdbc:mysql://localhost:3306/pressure";
    private final static String USERNAME = "root";
    private final static String PASSWORD = "";


    private final Connection conn;

    private DBConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            this.conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            if (conn == null) {
                System.out.println("Mamy problem");
            } else {
                System.out.println("Jest ok");
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static DBConnection getInstance() {
        if (instance == null) {
            synchronized (DBConnection.class) {
                if (instance == null) {
                    instance = new DBConnection();
                }
            }
        }
        return instance;
    }

    public Connection getConnection() {
        return conn;
    }

    public void closeConnection() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException("You can't close connection");
        }
    }

}
