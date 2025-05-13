package ru.silentflame.service.impl;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Service;
import ru.silentflame.GenerateReportRequest;
import ru.silentflame.service.ReportModule;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
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
    StringWriter sw = new StringWriter();

    CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
            .setHeader(headers.toArray(new String[0]))
            .build();
    try (final CSVPrinter printer = new CSVPrinter(sw, csvFormat)) {
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

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return sw.toString().getBytes(StandardCharsets.UTF_8);
  }
}