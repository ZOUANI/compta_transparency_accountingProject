package com.zsmart.accountingProject.service.util;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class ExcelUtil {
    public static XSSFSheet initSheet(XSSFWorkbook workbook, String sheetname, Map<String, Integer> columnHeaderWithColumnWidth, List<String> columnHeader, CellStyle headerStyle) {
        XSSFSheet sheet = workbook.createSheet(sheetname);

        if (columnHeaderWithColumnWidth != null && !columnHeaderWithColumnWidth.isEmpty()) {

            Row header = sheet.createRow(0);
            AtomicInteger index = new AtomicInteger();
            columnHeaderWithColumnWidth.forEach(
                    (s, integer) -> {
                        sheet.setColumnWidth(index.get(), integer);
                        Cell headerCell = header.createCell(index.get());
                        headerCell.setCellValue(s);
                        index.getAndIncrement();
                        if (headerStyle != null) headerCell.setCellStyle(headerStyle);

                    }
            );
            return sheet;
        } else if (!columnHeader.isEmpty()) {
            Row header = sheet.createRow(0);
            for (int i = 0; i < columnHeader.size(); i++) {
                sheet.setColumnWidth(i, 8000);
                Cell headerCell = header.createCell(i);
                headerCell.setCellValue(columnHeader.get(i));
                if (headerStyle != null) headerCell.setCellStyle(headerStyle);
            }
            return sheet;
        } else {
            return sheet;
        }
    }

    public static void fillTable(XSSFSheet sheet, List<List<String>> data, CellStyle cellStyle, Integer rowNum) {
        rowNum = rowNum == null || rowNum <= 0 ? 1 : rowNum;
        for (int i = 0; i < data.size(); i++) {
            Row row = sheet.createRow(rowNum++);
            for (int j = 0; j < data.get(i).size(); j++) {
                Cell cell = row.createCell(j);
                cell.setCellValue(data.get(i).get(j));
                if (cellStyle != null) cell.setCellStyle(cellStyle);
            }


        }

    }

    public static Resource exportBlob(XSSFWorkbook workbook) {
        try {
            try {
                FileUtils.cleanDirectory(new File("src\\main\\resources\\excelFiles\\"));
            } catch (IOException e) {
                ;
            }

            Long milli = new Date().getTime();
            File currDir = new File(".");
            String path = currDir.getAbsolutePath();
            String fileLocation = path.substring(0, path.length() - 1) + "src\\main\\resources\\excelFiles\\" + milli + "_temp.xlsx";

            FileOutputStream outputStream = new FileOutputStream(fileLocation);
            workbook.write(outputStream);
            workbook.close();
            Path path1 = Paths.get(path.substring(0, path.length() - 1) + "src\\main\\resources\\excelFiles\\");
            Path file = path1.resolve(milli + "_temp.xlsx");
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                return null;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
