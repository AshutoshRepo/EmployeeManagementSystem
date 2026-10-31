package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
    private static String URL;
    private static String USERNAME;
    private static String PASSWORD;
    // Runs once when the class is first used: reads the DB details from db.properties
    static
    {
        Properties props=new Properties();
        try(FileInputStream fis=new FileInputStream("db.properties"))
        {
            props.load(fis);
            URL=props.getProperty("db.url");
            USERNAME=props.getProperty("db.username");
            PASSWORD=props.getProperty("db.password");
        }
        catch (IOException e)
        {
            throw new RuntimeException("Could not read db.properties. Copy db.properties.example and fill in your values.",e);
        }
    }
    public static Connection getConnection() throws SQLException
    {
        return DriverManager.getConnection(URL,USERNAME,PASSWORD);
    }
}