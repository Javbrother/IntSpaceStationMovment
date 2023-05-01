package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class IssDataDao {
    private DatabaseService databaseService;
    public IssDataDao(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }
    public void saveIssLocation (ISSCurrentLocationResponse issData){
        String sql = "INSERT INTO iss_location (timestamp, latitude, longitude) VALUES (?, ?, ?)";

       try (Connection connection = databaseService.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, issData.getTimestamp());
            preparedStatement.setDouble(2, issData.getIss_position());
           preparedStatement.setDouble(3, issData.getIss_position());
            preparedStatement.executeUpdate();
        }catch (SQLException exception) {
            throw new RuntimeException("Error inserting ISS location data" , exception);
        }
    }
}
