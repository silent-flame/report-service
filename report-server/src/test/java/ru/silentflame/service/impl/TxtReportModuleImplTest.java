package ru.silentflame.service.impl;

import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import ru.silentflame.service.ReportModule;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.silentflame.utils.RequestUtils.getReportRequest;

class TxtReportModuleImplTest {
  ReportModule reportModule = new TxtReportModuleImpl();

  @SneakyThrows
  @Test
  void generate() {
    var request = getReportRequest();
    var actualContent = reportModule.generate(request);
    var expectedReport = Path.of("src", "test", "resources", "response.txt").toFile();
    var expectedContent = FileUtils.readFileToByteArray(expectedReport);
    assertEquals(new String(expectedContent, StandardCharsets.UTF_8), new String(actualContent, StandardCharsets.UTF_8));
  }
}