package ru.silentflame.service;

import ru.silentflame.GenerateReportRequest;

public interface ReportModule {

  String type();

  byte[] generate(GenerateReportRequest request);
}