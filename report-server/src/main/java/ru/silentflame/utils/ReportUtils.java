package ru.silentflame.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.UtilityClass;
import java.util.List;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReportUtils {
  public static List<String> getReportHeaders(List<Map<String, String>> reportData) {
    // Считать ли поля в разном регистре идентичными?
    return reportData.stream().flatMap(item -> item.keySet().stream()).toList();
  }
}
