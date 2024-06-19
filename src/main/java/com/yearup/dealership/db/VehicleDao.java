package com.yearup.dealership.db;

import com.yearup.dealership.models.Vehicle;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleDao {
    private DataSource dataSource;

    public VehicleDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addVehicle(Vehicle vehicle) {
        try(Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO vehicle (Vehicle) values(?,?,?,?,?,?,?,?,?);")){
            preparedStatement.setString(1, vehicle.getVin());
            preparedStatement.setString(2, vehicle.getMake());
            preparedStatement.setString(3, vehicle.getModel());
            preparedStatement.setInt(4, vehicle.getYear());
            preparedStatement.setBoolean(5, vehicle.isSold());
            preparedStatement.setString(6, vehicle.getColor());
            preparedStatement.setString(7, vehicle.getVehicleType());
            preparedStatement.setInt(8, vehicle.getOdometer());
            preparedStatement.setDouble(9, vehicle.getPrice());

            int rows = preparedStatement.executeUpdate();

            System.out.println("Rows updated: " + rows);

        } catch (SQLException e){
            e.printStackTrace();
        }

    }

    public void removeVehicle(String VIN) {

        String deleteQuery = "REMOVE FROM vehicles WHERE VIN LIKE = ?";

        try (Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)){
            preparedStatement.setString(1, VIN);
             int rows = preparedStatement.executeUpdate();

             if (rows > 0){
                 System.out.println("Vehicle with VIN" + VIN + "removed from the database.");
             } else {
                 System.out.println("No vehicle found with VIN" + VIN + "");
             }

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public List<Vehicle> searchByPriceRange(double minPrice, double maxPrice) {
        List<Vehicle> priceRangeList = new ArrayList<>();

        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM vehicles WHERE price BETWEEN ? AND ?")){
            preparedStatement.setDouble(1, minPrice);
            preparedStatement.setDouble(2, maxPrice);

            try (ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()){
                    String setupVIN = resultSet.getString("VIN");
                    String setupMake = resultSet.getString("make");
                    String setupModel = resultSet.getString("model");
                    int setupYear = resultSet.getInt("year");
                    boolean isSold = resultSet.getBoolean("SOLD");
                    String setupColor = resultSet.getString("Color");
                    String setupVehicleType = resultSet.getString("VehicleType");
                    int setupOdometer = resultSet.getInt("Odometer");
                    double setupPrice = resultSet.getDouble("price");

                    Vehicle vehicle = new Vehicle(setupVIN, setupMake, setupModel, setupYear, isSold, setupColor, setupVehicleType, setupOdometer, setupPrice);
                    priceRangeList.add(vehicle);

                }

            }

        } catch (SQLException e){
            e.printStackTrace();
        }

        return priceRangeList;
    }

    public List<Vehicle> searchByMakeModel(String make, String model) {

        List<Vehicle> makeModelList = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM vehicles WHERE vehicles.make LIKE ? AND vehicles.model LIKE ?;")) {
            preparedStatement.setString(1, make);
            preparedStatement.setString(2, model);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String setupVIN = resultSet.getString("VIN");
                    String setupMake = resultSet.getString("make");
                    String setupModel = resultSet.getString("model");
                    int setupYear = resultSet.getInt("year");
                    boolean isSold = resultSet.getBoolean("SOLD");
                    String setupColor = resultSet.getString("Color");
                    String setupVehicleType = resultSet.getString("VehicleType");
                    int setupOdometer = resultSet.getInt("Odometer");
                    double setupPrice = resultSet.getDouble("price");

                    Vehicle vehicle = new Vehicle(setupVIN, setupMake, setupModel, setupYear, isSold, setupColor, setupVehicleType, setupOdometer, setupPrice);
                    makeModelList.add(vehicle);

                }
            }

        } catch (SQLException e){
                      e.printStackTrace();
        }

        return makeModelList;
    }

    public List<Vehicle> searchByYearRange(int minYear, int maxYear) {
        List<Vehicle> yearRangeList = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM vehicles WHERE vehicles.year BETWEEN ? AND ?;")) {
            preparedStatement.setInt(1, minYear);
            preparedStatement.setInt(2, maxYear);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String setupVIN = resultSet.getString("VIN");
                    String setupMake = resultSet.getString("make");
                    String setupModel = resultSet.getString("model");
                    int setupYear = resultSet.getInt("year");
                    boolean isSold = resultSet.getBoolean("SOLD");
                    String setupColor = resultSet.getString("Color");
                    String setupVehicleType = resultSet.getString("VehicleType");
                    int setupOdometer = resultSet.getInt("Odometer");
                    double setupPrice = resultSet.getDouble("price");

                    Vehicle vehicle = new Vehicle(setupVIN, setupMake, setupModel, setupYear, isSold, setupColor, setupVehicleType, setupOdometer, setupPrice);
                    yearRangeList.add(vehicle);

                }
            }

        } catch (SQLException e){
            e.printStackTrace();
        }


        return yearRangeList;
    }

    public List<Vehicle> searchByColor(String color) {
        List<Vehicle> colorList = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM vehicles WHERE vehicles.Color LIKE ?;")) {
            preparedStatement.setString(1, color);


            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String setupVIN = resultSet.getString("VIN");
                    String setupMake = resultSet.getString("make");
                    String setupModel = resultSet.getString("model");
                    int setupYear = resultSet.getInt("year");
                    boolean isSold = resultSet.getBoolean("SOLD");
                    String setupColor = resultSet.getString("Color");
                    String setupVehicleType = resultSet.getString("VehicleType");
                    int setupOdometer = resultSet.getInt("Odometer");
                    double setupPrice = resultSet.getDouble("price");

                    Vehicle vehicle = new Vehicle(setupVIN, setupMake, setupModel, setupYear, isSold, setupColor, setupVehicleType, setupOdometer, setupPrice);
                    colorList.add(vehicle);


                }
            }

        } catch (SQLException e){
            e.printStackTrace();
        }


        return colorList;
    }

    public List<Vehicle> searchByMileageRange(int minMileage, int maxMileage) {
        List<Vehicle> mileageRangeList = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM vehicles WHERE vehicles.Odometer BETWEEN ? AND ?;")) {
            preparedStatement.setInt(1, minMileage);
            preparedStatement.setInt(2, maxMileage);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String setupVIN = resultSet.getString("VIN");
                    String setupMake = resultSet.getString("make");
                    String setupModel = resultSet.getString("model");
                    int setupYear = resultSet.getInt("year");
                    boolean isSold = resultSet.getBoolean("SOLD");
                    String setupColor = resultSet.getString("Color");
                    String setupVehicleType = resultSet.getString("VehicleType");
                    int setupOdometer = resultSet.getInt("Odometer");
                    double setupPrice = resultSet.getDouble("price");

                    Vehicle vehicle = new Vehicle(setupVIN, setupMake, setupModel, setupYear, isSold, setupColor, setupVehicleType, setupOdometer, setupPrice);
                    mileageRangeList.add(vehicle);

                }
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

        return mileageRangeList ;
    }

    public List<Vehicle> searchByType(String type) {
        List<Vehicle> vehicleTypeList = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM vehicles WHERE vehicles.VehicleType LIKE ?;")) {
            preparedStatement.setString(1, type);


            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String setupVIN = resultSet.getString("VIN");
                    String setupMake = resultSet.getString("make");
                    String setupModel = resultSet.getString("model");
                    int setupYear = resultSet.getInt("year");
                    boolean isSold = resultSet.getBoolean("SOLD");
                    String setupColor = resultSet.getString("Color");
                    String setupVehicleType = resultSet.getString("VehicleType");
                    int setupOdometer = resultSet.getInt("Odometer");
                    double setupPrice = resultSet.getDouble("price");

                    Vehicle vehicle = new Vehicle(setupVIN, setupMake, setupModel, setupYear, isSold, setupColor, setupVehicleType, setupOdometer, setupPrice);
                    vehicleTypeList.add(vehicle);


                }
            }

        } catch (SQLException e){
            e.printStackTrace();
        }



        return vehicleTypeList;
    }

    private Vehicle createVehicleFromResultSet(ResultSet resultSet) throws SQLException {
        Vehicle vehicle = new Vehicle();
        vehicle.setVin(resultSet.getString("VIN"));
        vehicle.setMake(resultSet.getString("make"));
        vehicle.setModel(resultSet.getString("model"));
        vehicle.setYear(resultSet.getInt("year"));
        vehicle.setSold(resultSet.getBoolean("SOLD"));
        vehicle.setColor(resultSet.getString("color"));
        vehicle.setVehicleType(resultSet.getString("vehicleType"));
        vehicle.setOdometer(resultSet.getInt("odometer"));
        vehicle.setPrice(resultSet.getDouble("price"));
        return vehicle;
    }
}
