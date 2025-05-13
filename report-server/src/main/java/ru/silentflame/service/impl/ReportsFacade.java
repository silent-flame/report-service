package ru.silentflame.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.silentflame.GenerateReportRequest;
import ru.silentflame.service.ReportModule;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportsFacade {
  private final List<ReportModule> reportModuleList;

  public byte[] generateReport(GenerateReportRequest request) {
    var foundReportModule = reportModuleList.stream()
            .filter(reportModule -> reportModule.type().equalsIgnoreCase(request.getOutputFormat()))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Report type does not supported"));
    return foundReportModule.generate(request);
  }
}