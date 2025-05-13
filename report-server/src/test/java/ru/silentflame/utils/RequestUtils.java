package ru.silentflame.utils;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.silentflame.GenerateReportRequest;
import java.util.List;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RequestUtils {
  public static GenerateReportRequest getReportRequest() {
    return new GenerateReportRequest().setReportName("testReport")
            .setPayloadData(List.of(Map.of("attribute1", "value1",
                            "attribute2", "value2",
                            "attribute3", "value3"),
                    Map.of("attribute3", "value3",
                            "attribute4", "value4",
                            "attribute5", "value5")));
  }
}