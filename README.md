## Student Management Backend

REST API cho hệ thống quản lý sinh viên.

### Công nghệ

| Thành phần | Công nghệ |
|------------|-----------|
| Framework | Spring Boot 2.7.9 |
| Java | 11 |
| Database | MySQL 8.x |
| ORM | Spring Data JPA |
| Security | Spring Security + JWT |
| Build Tool | Maven |

### Yêu cầu môi trường

- **Java**: JDK 11
- **Maven**: 3.8+ (hoặc dùng `mvnw` đi kèm project)
- **MySQL**: 8.x (hoặc dùng container Docker)
- **Git**: để clone source

### Chức năng chính

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

### Cấu trúc thư mục

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

### Cấu hình database

File `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/student_management?useUnicode=true&characterEncoding=UTF-8
spring.datasource.username=root
spring.datasource.password=1234
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update
```

Bạn nên thay đổi `spring.datasource.username` và `spring.datasource.password` cho phù hợp môi trường local của bạn.

### Chạy MySQL bằng Docker Compose

Project có sẵn file `docker-compose.yml` để chạy MySQL:

```bash
docker compose up -d
```

MySQL sẽ chạy với:

- Database: `student_management`
- User: `root`
- Password: `1234`
- Port: `3306`

Đảm bảo `spring.datasource.*` trong `application.properties` trùng với cấu hình trên (hoặc cập nhật lại cho phù hợp).

### Chạy ứng dụng

Chạy trực tiếp bằng Maven wrapper:

```bash
./mvnw spring-boot:run
```

Hoặc build JAR:

```bash
./mvnw clean package
java -jar target/studentmanagement-0.0.1-SNAPSHOT.jar
```
