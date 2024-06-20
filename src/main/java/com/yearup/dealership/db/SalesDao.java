package com.yearup.dealership.db;

import com.yearup.dealership.models.SalesContract;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SalesDao {
    private DataSource dataSource;

    public SalesDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addSalesContract(SalesContract salesContract) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO sales_contracts (VIN, contract_id, sale_date, price) VALUES (?, ?, ?, ?)")) {
            preparedStatement.setInt(1, salesContract.getContractId());
            preparedStatement.setString(2, salesContract.getVin());
            preparedStatement.setObject(3, salesContract.getSaleDate());
            preparedStatement.setDouble(4, salesContract.getPrice());

            int rows = preparedStatement.executeUpdate();
            System.out.println("\nSales Contract added.");
            System.out.println("Rows updated : " + rows);

             try (ResultSet keys = preparedStatement.getGeneratedKeys()) {
                 while (keys.next()){
                     System.out.println("new key was added: " + keys.getInt(1));
                 }

            }

        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
