# Dormitory management REST API

A course project for BI-TJV (Java Technologies) at FIT CTU, winter semester 2020/21. It is a small Spring Boot REST API for student dormitories. It manages the dormitories, their rooms, the students living in them and the faculties they study at. The command-line client for it is [BI-TJV-client](https://github.com/jakub-kucera/BI-TJV-client).

## What it does

- **Entities** (JPA):
  - A `Dormitory` has many rooms, and a `Room` has many students.
  - `Student` and `Faculty` are many-to-many.
  - Rooms have a price per day, a number of beds and a quarantine flag. Students have an account balance.
- **Endpoints:** `/api/dormitories`, `/api/rooms`, `/api/students` and `/api/faculties` each support:
  - `GET`, `GET /{id}`
  - `POST`, `PUT /{id}`
  - `DELETE /{id}`

  Bodies refer to related entities by id (`roomIds`, `facultyIds`, …).
- **Scholarships:** `PUT /api/students/giveScholarShip/{amount}` credits every student enrolled at more than one faculty.
- **Errors:** an unknown id gives 404, and a duplicate dormitory or faculty name gives 409.
- **Deletes cascade:** deleting a dormitory also deletes its rooms and their students.

## Tests

- JUnit 5 tests cover the controllers (MockMvc with mocked services) and the services (against an in-memory H2 database).
- The `.http` files under `src/test/java/.../client/` hold example requests.
- The `Dockerfile` runs `mvn test` on OpenJDK 11. It was used by the GitLab CI.

## Running

```bash
mvn test              # Java 11, Spring Boot 2.3.4
mvn spring-boot:run   # API on http://localhost:8080
```

The main configuration points at the faculty's Oracle database, with the password taken from `DB_PASSWORD`. So `spring-boot:run` needs that database or a different datasource.

## Known limitations

- A room without a dormitory, or a student without a room, is saved, but building the response then fails with a 500.
- Deleting a missing id returns 500, not 404.
- `chargeFeeAll` (charging the daily room price) is implemented but not exposed.
- The scholarship example in `StudentClientTest.http` uses `GET` instead of `PUT`.
