package ru.silentflame.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.silentflame.GenerateReportRequest;
import ru.silentflame.ReportsApi;
import ru.silentflame.service.ReportsFacade;

@RestController
@RequiredArgsConstructor
public class ReportController implements ReportsApi {
  private final ReportsFacade reportsFacade;

  @Override
  public String generateReport(GenerateReportRequest request) {
    return reportsFacade.generateReport(request);
  }
}