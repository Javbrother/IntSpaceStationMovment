package org.example;

import java.sql.*;
import java.util.Collection;

public class DatabaseService {
    private static final String URL = "jdbc:mysql://localhost:3306/iss_data";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException exception) {
            throw new RuntimeException("Error connecting database", exception);
        }
    }

    public void createTableIfNotExist(Connection connection)
            throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS ISS_LOCATION (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "timestamp varchar(50) NULL," +
                "latitude varchar(150) NOT NULL," +
                "longitude varchar(150) NOT NULL" +
                ")";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.execute();
        }
    }

    public void saveEntry(Position iss_position, long timestamp) {
        try {
            Statement stmt = getConnection().createStatement();
            String sql = "INSERT INTO iss_data (column1, column2, column3) VALUES ('" + iss_position.latitude + "," + iss_position.longitude  + "," + timestamp + ")"; // replace with your SQL query

           int rows = stmt.executeUpdate(sql);
            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

