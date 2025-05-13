package ru.silentflame.service.impl;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import ru.silentflame.GenerateReportRequest;
import ru.silentflame.service.ReportModule;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class ExcelReportModuleImpl implements ReportModule {
  @Override
  public String type() {
    return "xls";
  }

  @Override
  public byte[] generate(GenerateReportRequest request) {

    try (Workbook workbook = new XSSFWorkbook();) {
      Sheet sheet = workbook.createSheet("Report");

      var headers = getReportHeaders(request.getPayloadData());
      writeHeaders(sheet, workbook, headers);

      CellStyle style = workbook.createCellStyle();
      style.setWrapText(true);

      for (int itemCount = 0; itemCount < request.getPayloadData().size(); itemCount++) {
        Row row = sheet.createRow(itemCount + 1);
        var itemData = request.getPayloadData().get(itemCount);

        for (int columnCount = 0; columnCount < headers.size(); columnCount++) {
          Cell cell = row.createCell(columnCount);
          var headerName = headers.get(columnCount);
          var columnValue = itemData.getOrDefault(headerName, "");
          cell.setCellValue(columnValue);
          cell.setCellStyle(style);
        }
      }
      try (var outputStream = new ByteArrayOutputStream()) {
        workbook.write(outputStream);
        return outputStream.toByteArray();
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private static List<String> getReportHeaders(List<Map<String, String>> reportData) {
    // Считать ли поля в разном регистре идентичными?
    return reportData.stream().flatMap(item -> item.keySet().stream()).toList();
  }

  private static void writeHeaders(Sheet sheet, Workbook workbook, List<String> headers) {
    Row headerRow = sheet.createRow(0);

    CellStyle headerStyle = workbook.createCellStyle();
    headerStyle.setFillForegroundColor(IndexedColors.LIME.getIndex());
    headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

    XSSFFont font = ((XSSFWorkbook) workbook).createFont();
    font.setFontName("Arial");
    font.setFontHeightInPoints((short) 16);
    font.setBold(true);
    headerStyle.setFont(font);

    for (int headerNumber = 0; headerNumber < headers.size(); headerNumber++) {
      Cell headerCell = headerRow.createCell(headerNumber);
      var headerName = headers.get(headerNumber);
      sheet.setColumnWidth(headerNumber, 6000);
      headerCell.setCellValue(headerName);
      headerCell.setCellStyle(headerStyle);
    }
  }
}
