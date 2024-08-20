package ReusableMethods;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ExcelDataReader {
    public static List<String[]> readDataFromExcel(String filePath, String sheetName) throws IOException {
        List<String[]> data = new ArrayList<>();
        FileInputStream fis = new FileInputStream(filePath);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheet(sheetName);
        DecimalFormat decimalFormat = new DecimalFormat("#.##########");

        for (Row row : sheet) {
            List<String> rowData = new ArrayList<>();
            for (Cell cell : row) {
                switch (cell.getCellType()) {
                    case STRING:
                        rowData.add(cell.getStringCellValue());
                        break;
                    case NUMERIC:
                        // Check if the numeric cell is a date
                        if (DateUtil.isCellDateFormatted(cell)) {
                            rowData.add(cell.getDateCellValue().toString());
                        } else {
                            rowData.add(decimalFormat.format(cell.getNumericCellValue()));
                        }
                        break;
                    case BOOLEAN:
                        rowData.add(String.valueOf(cell.getBooleanCellValue()));
                        break;
                    case BLANK:
                        rowData.add(""); // Add empty string for blank cells
                        break;
                    default:
                        rowData.add(""); // Default case, add empty string
                        break;
                }
            }
            data.add(rowData.toArray(new String[0]));
        }

        workbook.close();
        fis.close();
        return data;
    }
}
