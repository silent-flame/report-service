## Запуск приложения
```shell
sh run.sh
```

## Swagger
http://localhost:8080/swagger-ui/index.html
#### swagger yaml
http://localhost:8080/v3/api-docs.yaml

## Сборка/прогон тестов/коммит изменений 
Если в проекте допущена ошибка, проект не компилируется или падают тесты, то коммит не будет выполнен
```shell
sh commit.sh 'git commit message'
```