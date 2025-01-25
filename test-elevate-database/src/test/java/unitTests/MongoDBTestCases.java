package unitTests;

import com.tep.database.DatabaseDriver;
import org.bson.Document;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MongoDBTestCases {
    DatabaseDriver driver = new DatabaseDriver();

    @Test
    public void MongoDB_Fetch_First_Row_Data() {
        driver.getConnection();
        List<Document> document = driver.retrive.getFirstRowData("RetrospectiveBill");
        Assertions.assertTrue(document.getFirst().containsKey("_id"));
        String expectedFact = "660aed89eb2777373de908cf";
        String actualFact = document.getFirst().getString("_id");
        Assertions.assertEquals(expectedFact, actualFact);
    }

    @Test
    public void MongoDB_Fetch_All_Data() {
        driver.getConnection();
        List<Document> documents = driver.retrive.getAllData("RetrospectiveBill");
        Assertions.assertNotEquals(0, documents.size());
    }

    @Test
    public void MongoDB_Fetch_Data_With_Query() {
        driver.getConnection();
        List<Document> documents = driver.retrive.getDataWithQuery("RetrospectiveBill", "{_t:\"RejectedRetrospectiveBill\"}", 10);
        Assertions.assertEquals(10, documents.size());
    }
}
