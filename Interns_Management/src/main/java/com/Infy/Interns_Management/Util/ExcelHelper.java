package com.Infy.Interns_Management.Util;

import java.io.InputStream;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import com.Infy.Interns_Management.Entity.Intern;

@Component
public class ExcelHelper {

    public List<Intern> convertExcelToInternList(InputStream is) {

        List<Intern> interns = new ArrayList<>();

        try (Workbook workbook = new XSSFWorkbook(is)) {

            Sheet sheet = workbook.getSheetAt(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {

                Row row = sheet.getRow(i);
                if (row == null) continue;

                Intern intern = new Intern();

                intern.setInternId(getStringValue(row, 0));
                intern.setInternName(getStringValue(row, 1));
                intern.setEmail(getStringValue(row, 2));
                intern.setPhoneNumber(getStringValue(row, 3));
                intern.setCollege(getStringValue(row, 4));
                intern.setDomain(getStringValue(row, 5));

                // ✅ Correct Date Handling
                intern.setInternshipStartDate(getDateValue(row, 7));
                intern.setInternshipEndDate(getDateValue(row, 6));

                intern.setStatus(getStringValue(row, 8));

                interns.add(intern);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Excel Parsing Error");
        }

        return interns;
    }

    // Safe String Reader
    private String getStringValue(Row row, int index) {

        Cell cell = row.getCell(index);

        if (cell == null) return "";

        switch (cell.getCellType()) {

            case STRING:
                return cell.getStringCellValue();

            case NUMERIC:
                return String.valueOf((long) cell.getNumericCellValue());

            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());

            default:
                return "";
        }
    }

    // ✅ Strong Date Reader (Works for real Excel Date format)
    private LocalDate getDateValue(Row row, int index) {

        Cell cell = row.getCell(index);

        if (cell == null) return null;

        try {

            if (cell.getCellType() == CellType.NUMERIC && DateUtil.isCellDateFormatted(cell)) {

                return cell.getDateCellValue()
                        .toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate();
            }

            if (cell.getCellType() == CellType.STRING) {

                return LocalDate.parse(cell.getStringCellValue());
            }

        } catch (Exception e) {
            return null;
        }

        return null;
    }
}