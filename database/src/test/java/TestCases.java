import com.tep.database.DatabaseConfig;
import org.bson.Document;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TestCases {

    @Test
    public void test_endpoint_MongoDB_Fetch_First_Row_Data() {
        DatabaseConfig.connectDatabase("mongodb://localhost:27017", "API", "cat_data_insert");
        List<Document> document = DatabaseConfig.getFirstRowData("MongoDB", "cat_data_insert");
        Assertions.assertTrue(document.getFirst().containsKey("fact"));
        String expectedFact = "The way you treat kittens in the early stages of it's life will render it's personality traits later in life.";
        String actualFact = document.getFirst().getString("fact");
        Assertions.assertEquals(expectedFact, actualFact);
    }

    @Test
    public void test_endpoint_MongoDB_Fetch_All_Data() {
        DatabaseConfig.connectDatabase("mongodb://localhost:27017", "API", "cat_data_insert");
        List<Document> documents = DatabaseConfig.getAllData("MongoDB", "cat_data_insert");
        Assertions.assertEquals(7, documents.size());
    }

    @Test
    public void test_endpoint_MongoDB_Fetch_Data_With_Query() {
        DatabaseConfig.connectDatabase("mongodb://localhost:27017", "API", "cat_data_insert");
        List<Document> documents = DatabaseConfig.getDataWithQuery("MongoDB", "cat_data_insert", "{ \"_id\": { $ne: ObjectId(\"676173fcb0b2f013b419f0c1\") } }");
        Assertions.assertEquals(6, documents.size());
    }

    @Test
    public void test_MySQL_Fetch_First_Row_Data() {
        DatabaseConfig.connectDatabase("MySQL");
        List<Document> document = DatabaseConfig.getFirstRowData("MySQL", "city");
        Assertions.assertTrue(document.getFirst().containsKey("Name"));
        int expectedPopulation = 1780000;
        int actualPopulation = document.getFirst().getInteger("Population");
        Assertions.assertEquals(expectedPopulation, actualPopulation);
    }

    @Test
    public void test_MySQL_Fetch_All_Data() {
        DatabaseConfig.connectDatabase("MySQL");
        List<Document> documents = DatabaseConfig.getAllData("MySQL", "city");
        Assertions.assertEquals(4079, documents.size());
    }

    @Test
    public void test_MySQL_Fetch_Data_With_Query() {
        DatabaseConfig.connectDatabase("MySQL");
        List<Document> documents = DatabaseConfig.getDataWithQuery("MySQL", "city", "SELECT * FROM world.city LIMIT 10;");
        Assertions.assertEquals(10, documents.size());
    }

}
