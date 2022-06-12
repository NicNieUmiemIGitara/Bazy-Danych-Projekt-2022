package insides.Server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatavaseConnect {
    String host = "127.0.0.1";
    int port = 1521;
    String database = "databaseadmin";
    String user = "c##databaseadmin";
    String password = "admin";

    void connect() throws ClassNotFoundException, SQLException {
        host = "localhost";
        port = 1521;
        database = "databaseadmin";
        user = "c##databaseadmin";
        password = "admin";
        String url = "jdbc:oracle:thin:" + user + "/" + password + "@" + host + ":" + port + ":" + database;
        Connection connection = DriverManager.getConnection(url);
    }
}