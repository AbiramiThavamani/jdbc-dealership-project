package com.yearup.dealership.db;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InventoryDao {
    private DataSource dataSource;

    public InventoryDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addVehicleToInventory(String vin, int dealershipId) {
        try (Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO inventory (dealership_id,VIN) VALUES (?,?)",
                PreparedStatement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setInt(1, dealershipId);
            preparedStatement.setString(2, vin);
            preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()){
                if (generatedKeys.next()){
                    int dealership = generatedKeys.getInt(1);
                    String vehicleId = generatedKeys.getString(2);
                    System.out.println("The Vehicle with vin: " + vehicleId + "was added to the dealership with id:" + dealership);
                }

            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void removeVehicleFromInventory(String vin) {
        try (Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM inventory WHERE VIN = ?")) {
            preparedStatement.setString(1, vin);
            preparedStatement.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
