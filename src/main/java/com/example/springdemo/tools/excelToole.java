package com.example.springdemo.tools;

import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

public class excelToole {

    /**
     *  sheetViewList    幾個分頁
     *  headerView       參數名稱
     *  callViewList     全部資料
     */
    public static void ResponseToExcel(HttpServletResponse response, List<List<String>> sheetViewList, List<String> headerView, List<List<String>> callViewList) {

        // 创建一个空白的工作簿对象
        Workbook workbook = new XSSFWorkbook();

        int sheetIndex = 0;

        for (List<String> sheetView : sheetViewList) {

            // 在工作簿中创建一个工作表对象
            int finalSheetIndex = sheetIndex;
            Sheet sheet = workbook.createSheet(Optional.ofNullable(sheetView.get(sheetIndex++)).orElseGet(() -> (String.valueOf(finalSheetIndex))));

            Row headerRow = sheet.createRow(0);

            // 创建表头行并设置表头单元格的值和样式
            for (int i = 0; i < headerView.size(); i++) {
                CellStyle headerCellStyle = createHeaderStyle(workbook);
                createHeaderCell(headerRow, i, headerView.get(i), headerCellStyle);
            }

            // 写入全部数据
            int rowNum = 1;
            for (List<String> callView : callViewList) {
                Row row = sheet.createRow(rowNum);
                for (int i = 0; i < callView.size(); i++) {
                    row.createCell(i).setCellValue(callView.get(i));
                }
                ++rowNum;
            }
        }
        exportRegisterStatisticExcel(response, workbook);
    }

    // 导出 Excel
    public static void exportRegisterStatisticExcel(HttpServletResponse response, Workbook workbook) {

        try {

            // 清除之前的設定
            response.reset();

            // 将工作簿中的数据写入输出流
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment; filename=" + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")) + "_in-api-p001" + ".xlsx");

            // 获取输出流
            OutputStream outputStream = response.getOutputStream();

            // 将 Excel 数据写入输出流
            workbook.write(outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static CellStyle createHeaderStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        return style;
    }

    private static void createHeaderCell(Row row, int column, String value, CellStyle style) {
        Cell cell = row.createCell(column);
        cell.setCellValue(value);
        cell.setCellStyle(style);
    }


}


