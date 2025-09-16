import java.sql.*;
import java.io.IOException;
import java.io.FileInputStream;
import java.util.Properties;

public class Database {

    private static String url;
    private static String name;
    private static String DatabasePassword;

    static{
        try{
            Properties prop = new Properties();
            prop.load(new FileInputStream("config.properties"));
            url = prop.getProperty("db.url");
            name = prop.getProperty("db.name");
            DatabasePassword = prop.getProperty("db.password");
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public static Connection getConnection () throws SQLException{
        return DriverManager.getConnection(url,name,DatabasePassword);
    }

}
