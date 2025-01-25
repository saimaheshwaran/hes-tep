package com.tep.database.dml;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class Retrieve {
    private static MongoDatabase database;
    private String dataBase;
    private static Statement statement;
    private static final Logger LOGGER = LoggerFactory.getLogger(Retrieve.class);

    public Retrieve(Object db, String DBType) {
        this.dataBase = DBType;
        switch (dataBase.toUpperCase()) {
            case "MONGODB":
                this.database = (MongoDatabase) db;
                break;
            case "MYSQL":
                this.statement = (Statement) db;
                break;
            case "SQL":
                break;
        }

    }

    /**
     * Retrieves the first row/document from the specified database and collection/table.
     *
     * @param collectionName The name of the collection or table to query.
     * @return A list containing the first document/row from the query.
     */
    public List<Document> getFirstRowData(String collectionName) {
        List<Document> results = new ArrayList<>();
        try {
            switch (dataBase.toUpperCase()) {
                case "MONGODB":
                    MongoCollection<Document> collection = database.getCollection(collectionName);
                    Document documents = collection.find().first();
                    results.add(documents);
                    break;
                case "MYSQL":
                    ResultSet resultSet = statement.executeQuery("SELECT * FROM " + collectionName + " LIMIT 1;");
                    while (resultSet.next()) {
                        Document row = new Document();
                        for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                            row.append(resultSet.getMetaData().getColumnName(i), resultSet.getObject(i));
                        }
                        results.add(row);
                    }
                    break;
                case "SQL":
                    break;
                default:
                    LOGGER.error("Invalid DB selection. Available DB's are MongoDB and SQL");
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
     * @param collectionName The name of the collection or table to query.
     * @return A list of all documents/rows from the query.
     */
    public List<Document> getAllData(String collectionName) {
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
                    ResultSet resultSet = statement.executeQuery("SELECT * FROM " + collectionName + ";");
                    while (resultSet.next()) {
                        Document row = new Document();
                        for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                            row.append(resultSet.getMetaData().getColumnName(i), resultSet.getObject(i));
                        }
                        results.add(row);
                    }
                    break;
                case "SQL":
                    break;
                default:
                    LOGGER.error("Invalid DB selection. Available DB's are MongoDB and SQL");
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
     * @param collectionName The name of the collection or table to query.
     * @param query          The query string to use for retrieving data.
     * @return A list of documents/rows that match the query.
     */
    public List<Document> getDataWithQuery(String collectionName, String query, int limit) {
        List<Document> results = new ArrayList<>();
        try {
            switch (dataBase.toUpperCase()) {
                case "MONGODB":
                    MongoCollection<Document> collection = database.getCollection(collectionName);
                    FindIterable<Document> documents = collection.find(Document.parse(query)).limit(limit);
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
                case "SQL":
                    break;
                default:
                    LOGGER.error("Invalid DB selection. Available DB's are MongoDB and SQL");
            }
        } catch (Exception e) {
            LOGGER.info("Error while fetching the data: " + e.getMessage());
            e.printStackTrace();
        }
        return results;
    }

}
