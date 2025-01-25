package com.tep.database.config;

import com.mongodb.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MongoDB implements DBConfig{

    private static MongoClient mongoClient;
    private static MongoDatabase database;
    private static final Logger LOGGER = LoggerFactory.getLogger(com.tep.database.config.MongoDB.class);

     /**
     * Connects to MongoDB using the provided connection string, database name, and collection name.
     *
     * @param connectionString The MongoDB connection string.
     * @param dbName           The name of the MongoDB database.
     */
    public MongoDatabase connectDatabase(String connectionString, String dbName) {
        try {
            mongoClient = MongoClients.create(connectionString);
            database = mongoClient.getDatabase(dbName);
            LOGGER.info("Connected to MongoDB: " + dbName);
        } catch (Exception e) {
            LOGGER.info("Error connecting to MongoDB: " + e.getMessage());
        }
        return database;
    }

}
