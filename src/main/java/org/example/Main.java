package org.example;
import java.io.IOException;
import java.lang.InterruptedException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        ApiService apiService = new ApiService();

        try {
            ISSCurrentLocationResponse currentLocation = apiService.fetchCurrentISSLocation();

            //establishing connection
            new DatabaseService().createTableIfNotExist(DatabaseService.getConnection());

            System.out.println("ISS Current Location:");
            System.out.println("Longitude: " + currentLocation.iss_position.longitude);
            System.out.println("Latitude: " + currentLocation.iss_position.latitude);
            System.out.println("Timestamp: " + currentLocation.timestamp);

            //saving entries
            new DatabaseService().saveEntry(currentLocation.iss_position, currentLocation.timestamp);
        } catch (IOException | InterruptedException |SQLException e) {
            e.printStackTrace();
        }
    }
}
