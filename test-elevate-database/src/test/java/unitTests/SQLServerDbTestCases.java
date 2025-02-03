package unitTests;

import com.tep.database.DbDriver;
import org.bson.Document;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class SQLServerDbTestCases {
    DbDriver driver = new DbDriver();

    @Test
    public void SQLServerDB_Fetch_First_Row_Data() {
        driver.getConnection();
        List<Document> document = driver.retrive.getFirstRowData("dbo.customer");
        System.out.println(document);
        Assertions.assertTrue(document.getFirst().containsKey("Customer_ID"));
        Assertions.assertEquals("Default", document.getFirst().getString("Customer_CD").trim());
    }

    @Test
    public void MongoDB_Fetch_All_Data() {
        driver.getConnection();
        List<Document> documents = driver.retrive.getAllData("dbo.customer");
        Assertions.assertNotEquals(0, documents.size());
    }

    @Test
    public void MongoDB_Fetch_Data_With_Query() {
        String query="SELECT TOP (1000) [ABM].[dbo].[Customer].[Customer_ID]\n" +
                "\t  ,[ABM].[dbo].[Customer].[Customer_CD]\n" +
                "\t  ,[ABM].[dbo].[Customer].[Customer_Name]\n" +
                "\t  ,[ABM].[dbo].[Billing_Detail].[Date_Of_Service]\n" +
                "      ,[ABM].[dbo].[Billing_Detail].[Service_CD]\n" +
                "      ,[ABM].[dbo].[Billing_Detail].[HES_Billed_HCPC]\n" +
                "\t  ,[ABM].[dbo].[Billing_Header].[Claim_Number]\n" +
                "\t  ,[ABM].[dbo].[Billing_Header].[Provider_FedTaxID]\n" +
                "\t  ,[ABM].[dbo].[Billing_Header].[Status]\n" +
                "\t  ,[ABM].[dbo].[Billing_Detail].[HES_Invoice_Header_ID]\n" +
                "  FROM [ABM].[dbo].[Customer]\n" +
                "INNER JOIN ABM.dbo.Billing_Header ON ABM.dbo.Customer.Customer_ID = ABM.dbo.Billing_Header.Customer_ID\n" +
                "INNER JOIN ABM.dbo.Billing_Detail ON ABM.dbo.Billing_Detail.HES_Invoice_Header_ID = ABM.dbo.Billing_Header.HES_Invoice_Header_ID\n" +
                "  WHERE ABM.dbo.Billing_Header.Customer_ID = '2' AND Status IN ('Sent to Customer', 'Passed Level2 Validations', 'Paid', 'Customer Received')";
        driver.getConnection();
        List<Document> documents = driver.retrive.getDataWithQuery("", query, 0);
        Assertions.assertEquals(1000, documents.size());
    }
}
