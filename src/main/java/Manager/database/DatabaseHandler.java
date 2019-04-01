package Manager.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHandler extends Configs {

    public Connection getDbConnection() throws SQLException {

        String connectionString = "jdbc:sqlserver://" + dbHost + ":" + dbPort
                + ";database=" + dbName
                + ";user=" + dbUser + "@evohunt"
                + ";password=" + dbPass
                + ";encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";

        return DriverManager.getConnection(connectionString, dbUser, dbPass);
    }

}
