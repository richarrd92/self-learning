package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Model.Account;
import Util.ConnectionUtil;

public class AccountDAO {

    /**
     * This method retrieves all Account objects from the database.
     * @return a list of Account objects
     */
    public List<Account> getAllAccounts() {
        Connection connection = ConnectionUtil.getConnection();
        List<Account> accountArrayList = new ArrayList<>();
        try {
            String sqlStatement = "SELECT * FROM account;";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            ResultSet resultSet = preparedStatement.executeQuery();

            // iterate down account table
            while (resultSet.next()) {
                // account object
                Account account = new Account(
                    resultSet.getInt("account_id"),
                    resultSet.getString("username"),
                    resultSet.getString("password")        
                );

                // add account object to array
                accountArrayList.add(account);

            }
        } catch (Exception e) {
            System.out.println("Error retrieving all accounts");
            e.printStackTrace();
        }
        
        return accountArrayList;
    }

    /**
     * Adds an Account object to the database.
     * @param account the Account object to be added to the database
     * @return 
     */
    public Account registerAccount(Account account) {
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sqlStatement = "INSERT INTO account (username, password) VALUES (?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());
            preparedStatement.executeUpdate();

            // get primary key
            ResultSet primaryKeyResultSet = preparedStatement.getGeneratedKeys();

            // if primary key exists
            if (primaryKeyResultSet.next()) {
                int generated_account_id = (int) primaryKeyResultSet.getInt(1);
                // return account
                return new Account(generated_account_id, account.getUsername(), account.getPassword());
            }

        } catch (Exception e) {
            System.out.println("Error adding account");
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Checks if a username exists in the database.
     * @param username the username to be checked
     * @return true if the username exists, false otherwise
     */
    public boolean existsByUsername(String username) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionUtil.getConnection();
            String sql = "SELECT 1 FROM account WHERE username = ?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();

            return resultSet.next(); // true if username exists

        } catch (Exception e) {
            System.out.println("Error checking if username exists");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    

    /**
     * Finds an account by username.
     * @param username the username to search for
     * @return the Account if found, otherwise null
     */
    public Account findByUsername(String username) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionUtil.getConnection();
            String sql = "SELECT * FROM account WHERE username = ?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();

            // if account exists
            if (resultSet.next()) {
                return new Account(
                        resultSet.getInt("account_id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"));
            }

        } catch (Exception e) {
            System.out.println("Error finding account by username");
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Finds an account by account ID.
     * @param posted_by the account ID to search for
     * @return the Account if found, otherwise null
     */
    public Account findbyID(int posted_by) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionUtil.getConnection();
            String sqlStatement = "SELECT * FROM account WHERE account_id = ?;";
            preparedStatement = connection.prepareStatement(sqlStatement);

            preparedStatement.setInt(1, posted_by);
            resultSet = preparedStatement.executeQuery();

            // if account exists
            if (resultSet.next()) {
                return new Account(
                        resultSet.getInt("account_id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"));
            }
        } catch (Exception e) {
            System.out.println("Error finding account by account_id");
            e.printStackTrace();
        }

        return null;
    }

}
