package com.example.springdemo.controller.system;

import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class excel {

    // 制作 Excel
    public static Workbook RegisterStatisticResponseToExcel(Workbook workbook, HttpServletResponse httpServletResponse, List<List<String>> responseList, String sheetName, List<String> headerNameList) {

        // 创建一个空白的工作簿对象
        workbook = new XSSFWorkbook();

        // 在工作簿中创建一个工作表对象
        Sheet sheet = workbook.createSheet(Optional.ofNullable(sheetName).orElseGet(() -> ("未命名")));

//        // 设定索引为0
//        workbook.setSheetOrder(sheet.getSheetName(), 0);


        Row headerRow = sheet.createRow(0);

        // 创建表头行并设置表头单元格的值和样式
        for (int i = 0; i < headerNameList.size(); i++) {
            CellStyle headerCellStyle = createHeaderStyle(workbook);
            createHeaderCell(headerRow, i, headerNameList.get(i), headerCellStyle);
        }

        // 写入全部数据
        int rowNum = 1;
        for (List<String> list : responseList) {
            Row row = sheet.createRow(rowNum);
            for (int i = 0; i < list.size(); i++) {
                row.createCell(i).setCellValue(list.get(i));
                System.out.println(list. get(i));
            }
            ++rowNum;
        }

        return workbook;
    }

    // 导出 Excel
    public static void exportRegisterStatisticExcel(HttpServletResponse response, Workbook workbook) {

//        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
//        response.setHeader("Content-Disposition", "attachment; filename=" + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")) + "_in-api-p001" + ".xlsx");

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

    // Header 命名
    public static List<String> headerName() {
        List<String> list = new LinkedList<>();
        list.add("appName");
        list.add("日期");
        list.add("OTP发送量");
        list.add("註冊量");
        list.add("相冊认证數");
        list.add("通讯录认证數");
        list.add("AppList认证數");
        list.add("短信通话认证數");
        list.add("实名认证數");
        list.add("紧急联系人认证數");
        list.add("Face id认证數");
        list.add("绑卡认证數");
        list.add("內部风控通过數");
        list.add("外部风控通过數");
        list.add("订单申请量");
        list.add("新客放款量");
        return list;
    }
}


