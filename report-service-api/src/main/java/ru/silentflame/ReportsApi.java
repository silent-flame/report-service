package ru.silentflame;

import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.awt.*;

public interface ReportsApi {

  @RequestMapping(method = RequestMethod.POST,
          value = "/report/generate",
          consumes = MediaType.APPLICATION_JSON_VALUE,
          produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
  ResponseEntity<byte[]> generateReport(@Valid @RequestBody GenerateReportRequest request);
}
