package com.tep.utilities;

import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

/**
 * Utility class for reading data from Excel files using Fillo and Apache POI.
 */
public class ExcelReader {

    Fillo fillo = new Fillo();
    Connection connection = null;
    private LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, String>>> objects = new LinkedHashMap<>();

    /**
     * Reads page objects from the specified Excel file.
     *
     * @param excelFilePath The path to the Excel file.
     * @return A LinkedHashMap containing the page objects.
     */
    public LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, String>>> getPageObjects(String excelFilePath) {
        List<String> sheetNames = getSheetNames(excelFilePath);
        try {
            connection = fillo.getConnection(excelFilePath);
            for (String page : sheetNames) {
                System.out.println(sheetNames);
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
            if (connection != null)
                connection.close();
        }
        return objects;
    }

    /**
     * Retrieves the names of all sheets in the specified Excel file.
     *
     * @param excelFilePath The path to the Excel file.
     * @return A list of sheet names.
     */
    private List<String> getSheetNames(String excelFilePath) {
        List<String> sheetNames = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(excelFilePath);
             Workbook workbook = new XSSFWorkbook(fis)) {
            int numberOfSheets = workbook.getNumberOfSheets();
            for (int i = 0; i < numberOfSheets; i++)
                sheetNames.add(workbook.getSheetName(i));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sheetNames;
    }

}


