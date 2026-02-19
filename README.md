# Бэкенд приложение "Платформа"

Описание проекта: https://github.com/dianainya/Software-engineering-methodology

---

## Документация проекта (ПЭД)

Краткий навигатор по ключевым частям кодовой базы.

### Контроллеры (REST API)

| Назначение | Файл | Базовый путь API |
|------------|------|------------------|
| **Админ: нарушения (бары)** | [BarsController](src/main/java/sadiva/mpi/platformbackend/controller/rest/admin/BarsController.java) | `api/v1/private/bars` |
| **Админ: продукты** | [ProductController](src/main/java/sadiva/mpi/platformbackend/controller/rest/admin/ProductController.java) | `api/v1/private/products` |
| **Админ: платформа** (распределение, этажи, старт/стоп) | [PlatformController](src/main/java/sadiva/mpi/platformbackend/controller/rest/admin/PlatformController.java) | `api/v1/private/platform` |
| **Админ: заключённые** | [PrisonerController](src/main/java/sadiva/mpi/platformbackend/controller/rest/admin/PrisonerController.java) | `api/v1/private/prisoners` |
| **Админ: текущее меню** | [CurrentMenuController](src/main/java/sadiva/mpi/platformbackend/controller/rest/admin/CurrentMenuController.java) | `api/v1/private/menu` |
| **Админ: пользователи** | [UserController](src/main/java/sadiva/mpi/platformbackend/controller/rest/admin/UserController.java) | `api/v1/private/users` |
| **Админ: блюда** | [DishController](src/main/java/sadiva/mpi/platformbackend/controller/rest/admin/DishController.java) | `api/v1/private/dishes` |
| **Админ: роли** | [RoleController](src/main/java/sadiva/mpi/platformbackend/controller/rest/admin/RoleController.java) | `api/v1/private/roles` |
| **Публичная авторизация** | [AuthenticationController](src/main/java/sadiva/mpi/platformbackend/controller/rest/publics/AuthenticationController.java) | `api/v1` |
| **«Я» — заключённый** (этаж, меню, выбор блюд) | [MyPrisonerController](src/main/java/sadiva/mpi/platformbackend/controller/rest/publics/MyPrisonerController.java) | `api/v1/me/prisoners` |
| **Обработка ошибок REST** | [RestResponseEntityExceptionHandler](src/main/java/sadiva/mpi/platformbackend/controller/advice/RestResponseEntityExceptionHandler.java) | — |

### Планировщик (cron)

| Задача | Файл | Описание |
|--------|------|----------|
| **Распределение заключённых по этажам** | [ScheduledTasks](src/main/java/sadiva/mpi/platformbackend/scheduled/ScheduledTasks.java) | Запускается по расписанию из `cron.distribute-person` ([application.yaml](src/main/resources/application.yaml)); вызывает [PlatformService.distributePrisoners()](src/main/java/sadiva/mpi/platformbackend/service/PlatformService.java). |

Точка входа приложения с включённым планированием: [PlatformBackendApplication](src/main/java/sadiva/mpi/platformbackend/PlatformBackendApplication.java) (`@EnableScheduling`).

### SQL: миграции Flyway и триггеры

Миграции лежат в [src/main/resources/db/migration/](src/main/resources/db/migration/).

| Миграция | Назначение |
|----------|------------|
| [V1__platform_init.sql](src/main/resources/db/migration/V1__platform_init.sql) | Инициализация: пользователи, роли |
| [V2__create_product_dish_tables.sql](src/main/resources/db/migration/V2__create_product_dish_tables.sql) | Таблицы продукта и блюда |
| [V3__create_prisoner_table.sql](src/main/resources/db/migration/V3__create_prisoner_table.sql) | Таблица заключённого |
| [V4__create_platform_prisoner_table.sql](src/main/resources/db/migration/V4__create_platform_prisoner_table.sql) | Связь платформа–заключённый |
| [V5__insert_prisoner_violatation_table.sql](src/main/resources/db/migration/V5__insert_prisoner_violatation_table.sql) | Таблица нарушений |
| **[V6__trigger_prisoner_rating.sql](src/main/resources/db/migration/V6__trigger_prisoner_rating.sql)** | **Триггер:** при вставке в `prisoner` создаётся запись в `prisoner_rating` (стартовый рейтинг 100) |
| [V7__cascade_prisoner_rating.sql](src/main/resources/db/migration/V7__cascade_prisoner_rating.sql) | Каскад удаления рейтинга при удалении заключённого |
| [V8__create_product_warehouse.sql](src/main/resources/db/migration/V8__create_product_warehouse.sql) | Таблица склада продуктов |
| [V9__create_platform_history.sql](src/main/resources/db/migration/V9__create_platform_history.sql) | История платформы |
| [V10__insert_roles.sql](src/main/resources/db/migration/V10__insert_roles.sql) | Начальные роли |
| **[V11__create_trigger_product_amount.sql](src/main/resources/db/migration/V11__create_trigger_product_amount.sql)** | **Триггер:** при вставке в `product` создаётся запись в `product_warehouse` (amount = 0) |
| [V12__extract_floor_activity...](src/main/resources/db/migration/V12__extract_floor_activity_to_table_platform_active_floor.sql) | Таблица активного этажа |
| [V13__drop_not_null_constraint_dish.sql](src/main/resources/db/migration/V13__drop_not_null_constraint_dish.sql) | Ослабление NOT NULL для блюда |
| [V14__add_is_alive_prisoner.sql](src/main/resources/db/migration/V14__add_is_alive_prisoner.sql) | Поле `is_alive` у заключённого |
| [V15__add_reference_cascade_user_role.sql](src/main/resources/db/migration/V15__add_reference_cascade_user_role.sql) | Ссылка и каскад user–role |
| [V16__add_cuurent_menu_table.sql](src/main/resources/db/migration/V16__add_cuurent_menu_table.sql) | Таблица текущего меню |
| [V17__insert_active_floor.sql](src/main/resources/db/migration/V17__insert_active_floor.sql) | Начальные данные активного этажа |

### Конфигурация и безопасность

| Назначение | Файл |
|------------|------|
| **Конфиг приложения** (БД, порт, JWT, cron) | [application.yaml](src/main/resources/application.yaml) |
| **Безопасность и JWT** | [SecurityConfig](src/main/java/sadiva/mpi/platformbackend/config/SecurityConfig.java) |
| **CORS и веб** | [WebConfiguration](src/main/java/sadiva/mpi/platformbackend/config/WebConfiguration.java) |
| **Общие бины** | [AppConfig](src/main/java/sadiva/mpi/platformbackend/config/AppConfig.java) |
| **Логирование** | [logback.xml](src/main/resources/logback.xml) |

### Сервисы и репозитории

- **Сервисы** (бизнес-логика): [src/main/java/sadiva/mpi/platformbackend/service/](src/main/java/sadiva/mpi/platformbackend/service/) — ProductService, PlatformService, PrisonerService, BarsService, UserService, CurrentMenuService, DishService, MyPrisonerService и др.
- **Репозитории** (доступ к БД): [src/main/java/sadiva/mpi/platformbackend/repo/](src/main/java/sadiva/mpi/platformbackend/repo/) — ProductRepo, PlatformRepo, PrisonerRepo, DishRepo, UserRepo, CurrentMenuRepo, RatingRepo, ViolationRepo и др.

### Тестирование (Testcontainers)

Проект использует **Testcontainers** для интеграционных тестов с реальной PostgreSQL базой данных в Docker-контейнере.

#### Базовый класс для тестов

| Файл | Описание |
|------|----------|
| [TestContainerStarter](src/test/java/test/sadiva/mpi/platformbackend/TestContainerStarter.java) | Базовый класс с настройкой PostgreSQL контейнера (`postgres:15-alpine`). Автоматически настраивает `spring.datasource.*` через `@DynamicPropertySource`. Все тесты наследуются от этого класса. |

**Зависимости** (в [build.gradle](build.gradle)):
- `org.testcontainers:junit-jupiter`
- `org.testcontainers:postgresql`

#### Тесты

| Тип | Файл | Описание |
|-----|------|----------|
| **REST контроллер** | [BarsControllerTest](src/test/java/test/sadiva/mpi/platformbackend/rest/BarsControllerTest.java) | Тесты API для управления нарушениями (барами): добавление/вычитание очков, валидация, обработка ошибок (404, 400). |
| **E2E тест** | [InmatePointsManagementTest](src/test/java/test/sadiva/mpi/platformbackend/e2e/InmatePointsManagementTest.java) | End-to-end тест: аналитик отправляет форму с нарушением → проверка обновления рейтинга → админ просматривает данные. |

#### Утилиты для тестов

| Файл | Назначение |
|------|------------|
| [BaseUtils](src/test/java/test/sadiva/mpi/platformbackend/utils/BaseUtils.java) | Базовые утилиты: очистка данных (`clearData()`), создание тестовых сущностей (Dish, Prisoner). |
| [DishUtils](src/test/java/test/sadiva/mpi/platformbackend/utils/DishUtils.java) | Утилиты для создания блюд и ингредиентов. |
| [ProductUtils](src/test/java/test/sadiva/mpi/platformbackend/utils/ProductUtils.java) | Утилиты для создания продуктов. |
| [PrisonerUtils](src/test/java/test/sadiva/mpi/platformbackend/utils/PrisonerUtils.java) | Утилиты для создания заключённых. |
| [PlatformUserUtils](src/test/java/test/sadiva/mpi/platformbackend/utils/PlatformUserUtils.java) | Утилиты для создания пользователей платформы. |
| [RandomUtils](src/test/java/test/sadiva/mpi/platformbackend/utils/RandomUtils.java) | Генерация случайных данных для тестов. |
| [ApiConstants](src/test/java/test/sadiva/mpi/platformbackend/constants/ApiConstants.java) | Константы API путей для тестов. |

**Запуск тестов:**
```bash
./gradlew test
```

---

### Настройка PostgreSQL

| Наименование      | Описание                                       | Пример    |
|-------------------|------------------------------------------------|-----------|
| DATABASE_HOST     | Хост базы данных                               | localhost |
| DATABASE_PORT     | Порт базы данных                               | 5432      |
| DATABASE_NAME     | Название базы данных                           | platform  |
| DATABASE_SCHEME   | Схема базы данных                              | public    |
| DATABASE_USER     | Имя пользователя для подключения к базе данных | platform  |
| DATABASE_PASSWORD | Пароль для подключения к базе данных           | password  |

### Настройка сервиса

| Наименование           | Описание                                          | Пример      |
|------------------------|---------------------------------------------------|-------------|
| SERVER_PORT            | Порт приложения                                   | 8080        |
| JWT_SECRET             | Секрет для генерации токена                       | secret      |
| DISTRIBUTE_PERSON_CRON | Периодичность распределения заключенных по этажам | 0 0 0 1 * * |

### Деплой сервиса

````
docker compose -p platform-backend up -d
````