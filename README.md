# Student Management Backend

REST API cho hệ thống quản lý sinh viên.

## Công nghệ

| Thành phần | Công nghệ |
|------------|-----------|
| Framework | Spring Boot 2.7.9 |
| Java | Version 11 |
| Database | MySQL |
| ORM | Spring Data JPA |
| Security | Spring Security + JWT |
| Build Tool | Maven |

## Chức năng chính

- **Authentication**: Đăng nhập, xác thực JWT
- **Student Management**: Quản lý sinh viên
- **Class Management**: Quản lý lớp học
- **Subject Management**: Quản lý môn học
- **Mark Management**: Quản lý điểm số
- **Teacher Management**: Quản lý giáo viên
- **Semester/SchoolYear**: Quản lý kỳ học, năm học
- **Majoring**: Quản lý chuyên ngành
- **Department**: Quản lý khoa/phòng ban
- **Schedule Exam**: Quản lý lịch thi

## Cấu trúc thư mục

```
src/main/java/com/example/studentmanagement/
├── controller/      # REST API endpoints
├── service/         # Business logic layer
├── service/impl/    # Service implementations
├── repository/      # Data access layer
├── model/           # Entity classes
├── security/        # JWT & Security config
├── exception/       # Custom exceptions
├── constants/       # Constants & enums
└── payload/         # DTOs for requests/responses
```

## Cấu hình

Cấu hình database trong `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/student_management
spring.datasource.username=root
spring.datasource.password=your_password
```

## Chạy ứng dụng

```bash
./mvnw spring-boot:run
```

Hoặc build JAR:

```bash
./mvnw clean package
java -jar target/studentmanagement-0.0.1-SNAPSHOT.jar
```
