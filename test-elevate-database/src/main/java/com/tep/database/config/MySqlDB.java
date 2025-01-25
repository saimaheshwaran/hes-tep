package com.tep.database.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class MySqlDB implements DBConfig {

    private static Connection connection;
    private static Statement statement;
    private static final Logger LOGGER = LoggerFactory.getLogger(com.tep.database.config.MySqlDB.class);

    public Statement connectDatabase(String connectionString, String credentials) {
        try {
            String[] creds=credentials.split("-");
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(connectionString, creds[0], creds[1]);
            statement = connection.createStatement();
        } catch (Exception e) {
            LOGGER.info("Error connecting to MongoDB: " + e.getMessage());
        }
        return statement;
    }
}
