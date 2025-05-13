package ru.silentflame.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.silentflame.GenerateReportRequest;
import ru.silentflame.ReportsApi;
import ru.silentflame.service.impl.ReportsFacade;
import java.util.Locale;

@RestController
@RequiredArgsConstructor
public class ReportController implements ReportsApi {
  private final ReportsFacade reportsFacade;

  @Override
  public ResponseEntity<byte[]> generateReport(GenerateReportRequest request) {
    var content = reportsFacade.generateReport(request);
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
    var fileName = request.getReportName() + "." + request.getOutputFormat().toLowerCase(Locale.ROOT);
    headers.setContentDisposition(ContentDisposition.builder("attachment").filename(fileName).build());

    return ResponseEntity.ok()
            .headers(headers)
            .body(content);
  }
}