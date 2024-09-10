package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

    private String path;

    public ExcelUtils(String path) {
        this.path = path;
    }

    public int getRowCount(String sheetName) throws IOException {
        try (FileInputStream fi = new FileInputStream(path);
             XSSFWorkbook workbook = new XSSFWorkbook(fi)) {
            XSSFSheet sheet = workbook.getSheet(sheetName);
            return sheet.getLastRowNum();
        }
    }

    public int getCellCount(String sheetName, int rownum) throws IOException {
        try (FileInputStream fi = new FileInputStream(path);
             XSSFWorkbook workbook = new XSSFWorkbook(fi)) {
            XSSFSheet sheet = workbook.getSheet(sheetName);
            XSSFRow row = sheet.getRow(rownum);
            return row.getLastCellNum();
        }
    }

    public String getCellData(String sheetName, int rownum, int colnum) throws IOException {
        try (FileInputStream fi = new FileInputStream(path);
             XSSFWorkbook workbook = new XSSFWorkbook(fi)) {
            XSSFSheet sheet = workbook.getSheet(sheetName);
            XSSFRow row = sheet.getRow(rownum);
            XSSFCell cell = row.getCell(colnum);

            DataFormatter formatter = new DataFormatter();
            return formatter.formatCellValue(cell);
        } catch (Exception e) {
            return "";
        }
    }

    public void setCellData(String sheetName, int rownum, int colnum, String data) throws IOException {
        File xlfile = new File(path);
        if (!xlfile.exists()) {
            try (XSSFWorkbook workbook = new XSSFWorkbook();
                 FileOutputStream fo = new FileOutputStream(path)) {
                workbook.write(fo);
            }
        }

        try (FileInputStream fi = new FileInputStream(path);
             XSSFWorkbook workbook = new XSSFWorkbook(fi);
             FileOutputStream fo = new FileOutputStream(path)) {
            if (workbook.getSheetIndex(sheetName) == -1) {
                workbook.createSheet(sheetName);
            }
            XSSFSheet sheet = workbook.getSheet(sheetName);

            XSSFRow row = (sheet.getRow(rownum) == null) ? sheet.createRow(rownum) : sheet.getRow(rownum);
            XSSFCell cell = row.createCell(colnum);
            cell.setCellValue(data);

            workbook.write(fo);
        }
    }

    public void fillGreenColor(String sheetName, int rownum, int colnum) throws IOException {
        fillColor(sheetName, rownum, colnum, IndexedColors.GREEN);
    }

    public void fillRedColor(String sheetName, int rownum, int colnum) throws IOException {
        fillColor(sheetName, rownum, colnum, IndexedColors.RED);
    }

    private void fillColor(String sheetName, int rownum, int colnum, IndexedColors color) throws IOException {
        try (FileInputStream fi = new FileInputStream(path);
             XSSFWorkbook workbook = new XSSFWorkbook(fi);
             FileOutputStream fo = new FileOutputStream(path)) {
            XSSFSheet sheet = workbook.getSheet(sheetName);
            XSSFRow row = sheet.getRow(rownum);
            XSSFCell cell = row.getCell(colnum);

            CellStyle style = workbook.createCellStyle();
            style.setFillForegroundColor(color.getIndex());
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            cell.setCellStyle(style);
            workbook.write(fo);
        }
    }
}
