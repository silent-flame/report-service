package ru.silentflame.service.impl;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Service;
import ru.silentflame.GenerateReportRequest;
import ru.silentflame.service.ReportModule;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import static ru.silentflame.utils.ReportUtils.getReportHeaders;

@Service
public class CsvReportModuleImpl implements ReportModule {
  @Override
  public String type() {
    return "csv";
  }

  @Override
  public byte[] generate(GenerateReportRequest request) {
    var headers = getReportHeaders(request.getPayloadData());
    CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
            .setHeader(headers.toArray(new String[0]))
            .build();

    try (var outputStream = new ByteArrayOutputStream();
         Writer writer = new PrintWriter(outputStream);
         final CSVPrinter printer = new CSVPrinter(writer, csvFormat)) {
      for (int itemNumber = 0; itemNumber < request.getPayloadData().size(); itemNumber++) {
        var itemData = request.getPayloadData().get(itemNumber);
        List<String> cellValues = new ArrayList<>();
        for (int fieldNumber = 0; fieldNumber < headers.size(); fieldNumber++) {
          var fieldName = headers.get(fieldNumber);
          var fieldValue = itemData.getOrDefault(fieldName, "");
          cellValues.add(fieldValue);
        }
        printer.printRecord(cellValues);
      }
      writer.flush();
      return outputStream.toByteArray();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}