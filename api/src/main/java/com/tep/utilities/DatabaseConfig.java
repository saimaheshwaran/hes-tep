package com.tep.utilities;

import com.mongodb.client.*;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class provides methods to connect to and interact with MongoDB and MySQL databases.
 */
public class DatabaseConfig {

    private static MongoClient mongoClient;
    private static MongoDatabase database;
    private static MongoCollection collection;
    private static Connection connection;
    private static Statement statement;
    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseConfig.class);

    /**
     * Connects to the specified database using default connection parameters.
     *
     * @param dataBase The name of the database to connect to. Can be "MONGODB" or "MYSQL".
     */
    public static void connectDatabase(String dataBase) {
        try {
            switch (dataBase.toUpperCase()) {
                case "MONGODB":
                    mongoClient = MongoClients.create("mongodb://localhost:27017");
                    database = mongoClient.getDatabase("API");
                    break;
                case "MYSQL":
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/world", "root", "Sms.EY@2024");
                    statement = connection.createStatement();
                    break;
                default:
                    LOGGER.error("Invalid DB selection. Available DB's are MongoDB and MySQL");
            }
        } catch (Exception e) {
            LOGGER.info("Error connecting to MongoDB: " + e.getMessage());
        }
    }

    /**
     * Connects to MongoDB using the provided connection string, database name, and collection name.
     *
     * @param connectionString The MongoDB connection string.
     * @param dbName           The name of the MongoDB database.
     * @param collectionName   The name of the MongoDB collection.
     */
    public static void connectDatabase(String connectionString, String dbName, String collectionName) {
        try {
            mongoClient = MongoClients.create(connectionString);
            database = mongoClient.getDatabase(dbName);
            collection = database.getCollection(collectionName);
            LOGGER.info("Connected to MongoDB: " + dbName + " -> " + collectionName);
        } catch (Exception e) {
            LOGGER.info("Error connecting to MongoDB: " + e.getMessage());
        }
    }

    /**
     * Retrieves the first row/document from the specified database and collection/table.
     *
     * @param dataBase        The name of the database to query. Can be "MONGODB" or "MYSQL".
     * @param collectionName  The name of the collection or table to query.
     * @return A list containing the first document/row from the query.
     */
    public static List<Document> getFirstRowData(String dataBase, String collectionName) {
        List<Document> results = new ArrayList<>();
        try {
            switch (dataBase.toUpperCase()) {
                case "MONGODB":
                    MongoCollection<Document> collection = database.getCollection(collectionName);
                    Document documents = collection.find().first();
                    results.add(documents);
                    break;
                case "MYSQL":
                    ResultSet resultSet = statement.executeQuery("SELECT * FROM world." + collectionName + " LIMIT 1;");
                    while (resultSet.next()) {
                        Document row = new Document();
                        for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                            row.append(resultSet.getMetaData().getColumnName(i), resultSet.getObject(i));
                        }
                        results.add(row);
                    }
                    break;
                default:
                    LOGGER.error("Invalid DB selection. Available DB's are MongoDB and MySQL");
            }
        } catch (Exception e) {
            LOGGER.info("Error while fetching the data: " + e.getMessage());
            e.printStackTrace();
        }
        return results;
    }

    /**
     * Retrieves all rows/documents from the specified database and collection/table.
     *
     * @param dataBase        The name of the database to query. Can be "MONGODB" or "MYSQL".
     * @param collectionName  The name of the collection or table to query.
     * @return A list of all documents/rows from the query.
     */
    public static List<Document> getAllData(String dataBase, String collectionName) {
        List<Document> results = new ArrayList<>();
        try {
            switch (dataBase.toUpperCase()) {
                case "MONGODB":
                    MongoCollection<Document> collection = database.getCollection(collectionName);
                    FindIterable<Document> documents = collection.find();
                    for (Document doc : documents) {
                        results.add(doc);
                    }
                    break;
                case "MYSQL":
                    ResultSet resultSet = statement.executeQuery("SELECT * FROM world." + collectionName + ";");
                    while (resultSet.next()) {
                        Document row = new Document();
                        for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                            row.append(resultSet.getMetaData().getColumnName(i), resultSet.getObject(i));
                        }
                        results.add(row);
                    }
                    break;
                default:
                    LOGGER.error("Invalid DB selection. Available DB's are MongoDB and MySQL");
            }
        } catch (Exception e) {
            LOGGER.info("Error while fetching the data: " + e.getMessage());
            e.printStackTrace();
        }
        return results;
    }

    /**
     * Retrieves rows/documents from the specified database and collection/table using a query.
     *
     * @param dataBase        The name of the database to query. Can be "MONGODB" or "MYSQL".
     * @param collectionName  The name of the collection or table to query.
     * @param query           The query string to use for retrieving data.
     * @return A list of documents/rows that match the query.
     */
    public static List<Document> getDataWithQuery(String dataBase, String collectionName, String query) {
        List<Document> results = new ArrayList<>();
        try {
            switch (dataBase.toUpperCase()) {
                case "MONGODB":
                    MongoCollection<Document> collection = database.getCollection(collectionName);
                    FindIterable<Document> documents = collection.find(Document.parse(query));
                    for (Document doc : documents) {
                        results.add(doc);
                    }
                    break;
                case "MYSQL":
                    ResultSet resultSet = statement.executeQuery(query);
                    while (resultSet.next()) {
                        Document row = new Document();
                        for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                            row.append(resultSet.getMetaData().getColumnName(i), resultSet.getObject(i));
                        }
                        results.add(row);
                    }
                    break;
                default:
                    LOGGER.error("Invalid DB selection. Available DB's are MongoDB and MySQL");
            }
        } catch (Exception e) {
            LOGGER.info("Error while fetching the data: " + e.getMessage());
            e.printStackTrace();
        }
        return results;
    }

    /**
     * Inserts a document into the specified MongoDB collection.
     *
     * @param collectionName The name of the MongoDB collection.
     * @param document       The document to insert.
     */
    public static void insertData(String collectionName, Document document) {
        database.getCollection(collectionName).insertOne(document);
    }

}
