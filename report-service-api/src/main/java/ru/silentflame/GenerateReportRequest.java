package ru.silentflame;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Accessors(chain = true)
public class GenerateReportRequest {
  @NotBlank
  private String reportName;
  @NotEmpty
  private List<Map<String, String>> payloadData;
  @NotBlank
  private String outputFormat;
}