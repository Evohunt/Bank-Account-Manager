package Manager.database;

import Manager.model.User;

import java.sql.*;

public class DatabaseHandler extends Configs {

    private Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName
                + "?autoReconnect=true&useSSL=false";
        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
        return dbConnection;
    }

    public ResultSet validateUser(User user) {

        ResultSet resultSet = null;

        String query = "SELECT * FROM " + Const.USERS_TABLE + " WHERE "
                + Const.USERS_USERNAME + "=?";

        try {

            PreparedStatement preparedStatement;
            preparedStatement = getDbConnection().prepareStatement(query);
            preparedStatement.setString(1, user.getUserName());

            resultSet = preparedStatement.executeQuery();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return resultSet;

    }

    public ResultSet getUser(User user) {

        ResultSet resultSet = null;

        if (!user.getUserName().equals("") || !user.getPassword().equals("")) {

            String query = "SELECT * FROM " + Const.USERS_TABLE + " WHERE "
                    + Const.USERS_USERNAME + "=?" + " AND " + Const.USERS_PASSWORD
                    + "=?";

            try {

                PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
                preparedStatement.setString(1, user.getUserName());
                preparedStatement.setString(2, user.getPassword());

                resultSet = preparedStatement.executeQuery();

            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }

        } else {

            // Input Validation

        }

        return resultSet;

    }

    public void registerUser(User user) {

        String insert = "INSERT INTO " + Const.USERS_TABLE + "(" + Const.USERS_FIRSTNAME
                + ", " + Const.USERS_LASTNAME
                + ", " + Const.USERS_USERNAME
                + ", " + Const.USERS_EMAIL
                + ", " + Const.USERS_PASSWORD
                + ", " + Const.USERS_ADDRESS + ")" + " VALUES(?, ?, ?, ?, ?, ?)";

        try {

            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getUserName());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.setString(6, user.getAddress());


            preparedStatement.executeUpdate();


        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

}
