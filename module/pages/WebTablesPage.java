package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class WebTablesPage {
    private WebDriver driver;

    // Locators
    private static final String TABLE_ROWS_XPATH = "//*[@id='app']/div/div/div/div[2]/div[2]/div[3]/div[1]/div[2]/div";
    private static final String TABLE_CELL_XPATH_TEMPLATE = "//*[@id='app']/div/div/div/div[2]/div[2]/div[3]/div[1]/div[2]/div[%d]/div/div[%d]";

    // Constructor to initialize WebDriver
    public WebTablesPage(WebDriver driver) {
        this.driver = driver;
    }

    // Method to get data from a specific cell    
    public String getCellData(int row, int column) {
        String cellXpath = String.format(TABLE_CELL_XPATH_TEMPLATE, row, column);
        WebElement cell = driver.findElement(By.xpath(cellXpath));
        return cell.getText();
    }

    // Method to get the data for a specific column (e.g., First Name, Last Name, etc.)
    public List<String> getColumnData(int column) {
        List<String> columnData = new ArrayList<>();
        int numberOfRows = getNumberOfRows();
        for (int i = 1; i <= numberOfRows; i++) {
            columnData.add(getCellData(i, column));
        }
        return columnData;
    }

    // Method to get the number of rows in the table
    public int getNumberOfRows() {
        List<WebElement> rows = driver.findElements(By.xpath(TABLE_ROWS_XPATH));
        return rows.size();
    }
}
