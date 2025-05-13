package ru.silentflame.service.impl;

import org.springframework.stereotype.Service;
import ru.silentflame.GenerateReportRequest;
import ru.silentflame.service.ReportModule;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.StringJoiner;

@Service
public class TxtReportModuleImpl implements ReportModule {
  @Override
  public String type() {
    return "txt";
  }

  @Override
  public byte[] generate(GenerateReportRequest request) {
    try (var outputStream = new ByteArrayOutputStream();
         Writer writer = new PrintWriter(outputStream)) {
      for (var item : request.getPayloadData()) {
        var stringJoiner = new StringJoiner(";");
        for (var field : item.keySet().stream().sorted().toList()) {
          var fieldValue = item.get(field);
          stringJoiner.add(field + "=" + fieldValue);
        }
        writer.append(stringJoiner.toString());
        writer.append("\n");
      }
      writer.flush();
      return outputStream.toByteArray();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}