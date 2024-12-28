package com.tep.utilities;

import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;
import org.apache.poi.ss.usermodel.Workbook;
import com.codoid.products.fillo.Connection;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.io.FileInputStream;

/**
 * Utility class to read data from Excel files.
 * This class utilizes Apache POI and Fillo libraries to interact with Excel sheets
 * and retrieve data in a structured format.
 */
public class ExcelReader {

    /**
     * Fillo object used for querying Excel data using SQL-like syntax.
     */
    private final Fillo fillo = new Fillo();

    /**
     * Connection object used to establish a connection to the Excel file for querying.
     */
    private Connection connection = null;

    /**
     * Nested map to store objects from the Excel file.
     * Structure: SheetName -> Variable -> (Locator -> Value).
     */
    private final LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, String>>> objects = new LinkedHashMap<>();

    /**
     * Reads page objects from an Excel file and organizes them into a structured map.
     *
     * @param excelFilePath The file path of the Excel document.
     * @return A map containing the structured page objects from the Excel file.
     */
    public LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, String>>> getPageObjects(String excelFilePath) {
        List<String> sheetNames = getSheetNames(excelFilePath);
        try {
            // Establish a connection to the Excel file
            connection = fillo.getConnection(excelFilePath);

            for (String page : sheetNames) {
                System.out.println(sheetNames);

                // Query the data from each sheet
                Recordset recordset = connection.executeQuery("SELECT * FROM " + page);
                LinkedHashMap<String, LinkedHashMap<String, String>> variables = new LinkedHashMap<>();

                while (recordset.next()) {
                    LinkedHashMap<String, String> locatorPair = new LinkedHashMap<>();
                    locatorPair.put(recordset.getField("locator"), recordset.getField("value"));
                    variables.put(recordset.getField("variables"), locatorPair);
                }

                recordset.close();
                objects.put(page, variables);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the connection if it's open
            if (connection != null)
                connection.close();
        }
        return objects;
    }

    /**
     * Retrieves the names of all sheets in the given Excel file.
     *
     * @param excelFilePath The file path of the Excel document.
     * @return A list of sheet names in the Excel file.
     */
    private List<String> getSheetNames(String excelFilePath) {
        List<String> sheetNames = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(excelFilePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            int numberOfSheets = workbook.getNumberOfSheets();
            for (int i = 0; i < numberOfSheets; i++) {
                sheetNames.add(workbook.getSheetName(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sheetNames;
    }
}
