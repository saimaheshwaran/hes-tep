package com.tep.database.config;


public interface DBConfig {

    Object connectDatabase(String connectionString, String dbName);
}
