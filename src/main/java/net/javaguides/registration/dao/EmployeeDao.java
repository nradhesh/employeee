package net.javaguides.registration.dao;

import net.javaguides.registration.model.Employee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmployeeDao {

    public int registerEmployee(Employee employee) throws ClassNotFoundException, SQLException {
        String INSERT_USERS_SQL = "INSERT INTO employee (id,first_name, last_name, username, password, address, contact) VALUES (1,?, ?, ?, ?, ?, ?)";
        int result = 0;

        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/employees?useSSL=false&allowPublicKeyRetrieval=true", "root", "@1234sql#abc");
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
        	connection.setAutoCommit(false);
            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setString(3, employee.getUserName());
            preparedStatement.setString(4, employee.getPassword());
            preparedStatement.setString(5, employee.getAddress());
            preparedStatement.setString(6, employee.getContact());
            connection.commit();
            System.out.println(preparedStatement);
            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            printSQLException(e);
        }

        return result;
    }

    private void printSQLException(SQLException ex) {
        ex.printStackTrace(System.err);
        System.err.println("SQLState: " + ex.getSQLState());
        System.err.println("Error Code: " + ex.getErrorCode());
        System.err.println("Message: " + ex.getMessage());

        for (Throwable t = ex.getCause(); t != null; t = t.getCause()) {
            System.out.println("Cause: " + t);
        }
    }
}
