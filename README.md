Для работы приложения будут нужны:

Проект на Java SE 17 (https://download.oracle.com/java/17/archive/jdk-17.0.9_windows-x64_bin.msi (sha256 ))

- IntelliJ IDEA (EDU or Ultimate)
- Docker - Для разворота базы данных
- Maven - Сборщик

Необязательные программы:

- Postman - Для работы с эндпоинтами
- DBeaver - Для работы с базами данных

Для запуска приложения требуется:

При первом запуске IDEA - установить плагин Docker для IDEA

1. Запустить IDEA
2. Запустить Docker
3. Запустить docker-compose.yaml (с помощью плагина)
4. Запустить TelecomApplication
5. После этого доступны эндпоинты указанные в контракте

Контракт: http://localhost:8080/api

Контракт лежит в корневой папке openapi.yaml
