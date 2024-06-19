package com.yearup.dealership.db;

import com.yearup.dealership.models.LeaseContract;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LeaseDao {
    private DataSource dataSource;

    public LeaseDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addLeaseContract(LeaseContract leaseContract) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO lease_contracts VALUES ?, ?, ?, ?, ?;")) {
            preparedStatement.setInt(1, leaseContract.getContractId());
            preparedStatement.setString(2, leaseContract.getVin());
            preparedStatement.setObject(3, leaseContract.getLeaseStart());
            preparedStatement.setObject(4, leaseContract.getLeaseEnd());
            preparedStatement.setDouble(5, leaseContract.getMonthlyPayment());

            int rows = preparedStatement.executeUpdate();
            System.out.println("\nLease Contract added.");
            System.out.println("Rows updated : " + rows);

        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
