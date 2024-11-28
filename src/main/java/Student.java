import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

public class Student {

    public static void main(String[] args) {
        String inputFile = "students.xlsx";
        String outputFile = "updated_students.xlsx";

        try (Workbook workbook = new XSSFWorkbook(new FileInputStream(inputFile));
             Workbook outputWorkbook = new XSSFWorkbook()) {

            Sheet inputSheet = workbook.getSheetAt(0);
            Sheet outputSheet = outputWorkbook.createSheet("Updated Students");

            for (Row row : inputSheet) {
                Row newRow = outputSheet.createRow(row.getRowNum());

                for (Cell cell : row) {
                    Cell newCell = newRow.createCell(cell.getColumnIndex());
                    if (cell.getCellType() == CellType.NUMERIC) {
                        newCell.setCellValue(cell.getNumericCellValue());
                    } else {
                        newCell.setCellValue(cell.getStringCellValue());
                    }
                }

                if (row.getRowNum() > 0) {
                    double gpa = row.getCell(4).getNumericCellValue();
                    double scholarship = row.getCell(3).getNumericCellValue();
                    String faculty = row.getCell(5).getStringCellValue();
                    double newScholarship = calculateNewScholarship(faculty, gpa, scholarship);
                    newRow.createCell(6).setCellValue(newScholarship);
                }
            }

            try (FileOutputStream fos = new FileOutputStream(outputFile)) {
                outputWorkbook.write(fos);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static double calculateNewScholarship(String faculty, double gpa, double scholarship) {
        return switch (faculty.toLowerCase()) {
            case "cybersecurity" -> gpa > 2.4 ? scholarship * 1.1 : scholarship;
            case "Infromation system" -> gpa > 2.4 ? scholarship * 1.15 : scholarship;
            case "Information technology" -> gpa > 2.2 ? scholarship * 1.05 : scholarship;
            case "marketing" -> gpa > 2.5 ? scholarship * 1.08 : scholarship;
            default -> scholarship;
        };
    }
}
