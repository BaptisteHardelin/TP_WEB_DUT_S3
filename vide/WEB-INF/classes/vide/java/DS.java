package vide.java;
import java.sql.*;
import java.io.FileInputStream;
import java.util.Properties;

public class DS {

    private String driverPath, url, login, passwd;

    public DS() {
        Properties props = new Properties();
        try {
            props.load(new FileInputStream("props.properties"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.driverPath = props.getProperty("driver");
        this.url = props.getProperty("url");
        this.login = props.getProperty("login");
        this.passwd = props.getProperty("passwd");
    }

    public Connection getConnection() {
        try {
            Class.forName(this.driverPath);
            return DriverManager.getConnection(this.url, this.login, this.passwd);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}