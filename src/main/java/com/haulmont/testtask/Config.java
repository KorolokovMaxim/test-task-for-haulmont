package com.haulmont.testtask;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Config {
    //    private static final Config INSTANCE = new Config();
    private Connection db;

    private static class ConfigHolder {
        private static final Config HOLDER_INSTANCE = new Config();
    }

    private Config() {
        initDb();
    }

    public static Config getInstance() {
//        return INSTANCE;
        return ConfigHolder.HOLDER_INSTANCE;
    }


    public Connection getDb() {
        return db;
    }

    private void initDb() {

        ResourceBundle resourceBundle = ResourceBundle.getBundle("database");
        String driver = resourceBundle.getString("db.driver");
        String url = resourceBundle.getString("db.url");
        String username = resourceBundle.getString("db.username");
        String password = resourceBundle.getString("db.password");
        try {
            Class.forName(driver);

            db = DriverManager.getConnection(
                    url,
                    username,
                    password);

            if (db == null) {
                System.err.println("Нет соединения с БД!");
                System.exit(1);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
