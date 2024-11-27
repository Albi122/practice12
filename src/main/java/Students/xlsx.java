package Students;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

public class xlsx {

        public static void main(String[] args) throws IOException {
                try {
                        File excelFile = new File("C:\\Users\\37418\\IdeaProjects\\Albina\\src\\Student2.xlsx");
                        FileInputStream fis = new FileInputStream(excelFile);
                        XSSFWorkbook workbook = new XSSFWorkbook(fis);
                        XSSFSheet sheet = workbook.getSheetAt(0);

                        Iterator<Row> rowIt = sheet.iterator();

                        while (rowIt.hasNext()) {
                                Row row = rowIt.next();

                                Iterator<Cell> cellIterator = row.cellIterator();

                                while (cellIterator.hasNext()) {
                                        Cell cell = cellIterator.next();
                                        System.out.print(cell.toString() + ";");
                                }
                                System.out.println();
                        }
                } catch (FileNotFoundException e) {
                        System.out.println("Error: File not found - " + e.getMessage());
                } catch (IOException e) {
                        System.out.println("Error: An IO exception occurred - " + e.getMessage());
                }
        }
}
