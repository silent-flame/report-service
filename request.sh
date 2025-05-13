#!/bin/bash

# Чистый curl запрос
curl -X 'POST' -v \
  'http://localhost:8080/report/generate' \
  -H 'accept: application/octet-stream' \
  -H 'Content-Type: application/json' \
  -d '{
  "reportName": "report5",
  "payloadData": [
    {
      "attribute1": "value1",
      "attribute2": "value2",
      "attribute3": "value3"
    },
    {
      "attribute3": "value3",
      "attribute4": "value4",
      "attribute5": "value5"
    }
  ],
  "outputFormat": "csv"
}'

# Сохранение ответа в файл CSV
curl -X 'POST' -v \
  'http://localhost:8080/report/generate' \
  -H 'accept: application/octet-stream' \
  -H 'Content-Type: application/json' \
  -d '{
  "reportName": "report5",
  "payloadData": [
      {
        "attribute1": "value1",
        "attribute2": "value2",
        "attribute3": "value3"
      },
      {
        "attribute3": "value3",
        "attribute4": "value4",
        "attribute5": "value5"
      }
  ],
  "outputFormat": "csv"
}' >> response.csv

# Сохранение ответа в файл CSV
curl -X 'POST' -v \
  'http://localhost:8080/report/generate' \
  -H 'accept: application/octet-stream' \
  -H 'Content-Type: application/json' \
  -d '{
  "reportName": "report5",
  "payloadData": [
      {
        "attribute1": "value1",
        "attribute2": "value2",
        "attribute3": "value3"
      },
      {
        "attribute3": "value3",
        "attribute4": "value4",
        "attribute5": "value5"
      }
  ],
  "outputFormat": "xls"
}' >> response.xls