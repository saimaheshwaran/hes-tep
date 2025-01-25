package com.tep.database.config;


public interface DbConfig {

    Object connectDatabase(String connectionString, String dbName);
}
