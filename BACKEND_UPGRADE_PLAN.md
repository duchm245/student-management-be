## Kế hoạch nâng cấp backend Spring Boot (Student Management)

Backend: Spring Boot (Java), project `student-management-be`. Frontend được tách sang project khác, backend chỉ tập trung cung cấp REST API ổn định, sạch và dễ mở rộng.

---

## Phase 0 – Đánh giá & dọn dẹp cơ bản (ưu tiên cao)

**Mục tiêu**

- Nắm lại kiến trúc hiện tại và loại bỏ nợ kỹ thuật quá lộ.
- Chuẩn hóa tối thiểu để dễ refactor ở các phase sau.

**Công việc chính**

- **Đọc cấu trúc project**
  - Xem lại các package: `controller`, `service`, `service.impl`, `repository`, `model`, `security`, `exception`, `constants`, `payload`.
  - Đọc nhanh `application.properties` để hiểu cấu hình DB, port, security, v.v.
- **Kiểm tra build & dependency**
  - Mở `pom.xml` để nắm version Spring Boot (hiện tại 2.7.9), Java (11) và các starter (web, security, JPA, JWT, v.v.).
- **Chuẩn hóa cơ bản**
  - Thiết lập code style thống nhất (formatting, naming, structure).
  - Xóa class, endpoint, config không còn dùng.
  - Gom các hằng số, enum vào `constants` nếu còn rải rác.
- **README**
  - Cập nhật `README.md`: mô tả công nghệ, cách chạy, yêu cầu môi trường (Java, Maven, MySQL).

**Kỹ năng luyện**

- Đọc hiểu codebase legacy, đánh giá kỹ thuật, viết README rõ ràng – nền tảng để đi tiếp.

---

## Phase 1 – Kiến trúc & mô hình domain (ưu tiên cao)

**Mục tiêu**

- Có kiến trúc rõ ràng theo layered (hoặc kết hợp feature-based).
- Mô hình domain (Student, Class, Subject, Mark, Teacher, …) rõ ràng, chuẩn hóa.

**Công việc chính**

- **Chuẩn hóa layering**
  - `controller`: chỉ nhận request HTTP, validate input, mapping DTO ↔ response, trả về status code.
  - `service`: chứa business logic, transaction, orchestration giữa nhiều repository.
  - `repository`: chỉ thao tác DB bằng Spring Data JPA (hoặc query cụ thể).
- **Tổ chức package**
  - Cân nhắc tổ chức theo feature, ví dụ:
    - `student`, `classroom`, `subject`, `teacher`, `department`, `schedule`, `mark`, `semester`, `schoolyear`, `majoring`…
  - Hoặc kết hợp: `student/controller`, `student/service`, `student/repository`, `student/model`, v.v.
- **Domain model**
  - Rà soát các entity trong `model`: quan hệ giữa `Student`, `Class`, `Subject`, `Teacher`, `Department`, `Mark`, `Semester`, `SchoolYear`, `Majoring`, `ScheduleExam`.
  - Chuẩn hóa annotation JPA (OneToMany, ManyToOne, ManyToMany, JoinTable, v.v.).
  - Đảm bảo không nhét business logic phức tạp vào controller; chuyển về service.
- **DTO & mapper**
  - Tách rõ `Entity` và `DTO` (request, response) trong `payload`.
  - Dùng MapStruct hoặc mapper thủ công rõ ràng (tránh logic nặng trong controller).

**Kỹ năng luyện**

- Thiết kế kiến trúc, tư duy domain-driven (ở mức pragmatic), SOLID ở level module/class.

---

## Phase 2 – Code quality & cross-cutting concerns (ưu tiên cao)

**Mục tiêu**

- Code sạch, dễ đọc, dễ test, nhất quán.
- Cross-cutting như validation, exception handling, logging được làm bài bản.

**Công việc chính**

- **Clean code & SOLID**
  - Refactor method quá dài, tách nhỏ theo trách nhiệm.
  - Loại bỏ duplication; gom logic dùng chung vào service/helper hợp lý.
  - Dùng interface cho service chính (nếu phù hợp) để dễ mock/test.
- **Validation**
  - Dùng Bean Validation (`@Valid`, `@NotNull`, `@NotBlank`, `@Size`, `@Email`, …) trên DTO.
  - Đảm bảo mọi input từ client đều được validate, đặc biệt cho các API tạo/sửa dữ liệu.
- **Exception handling chuẩn**
  - Tạo `@ControllerAdvice` global để xử lý exception → HTTP status + body thống nhất.
  - Định nghĩa error response chuẩn: ví dụ `{ code, message, details, timestamp, path }`.
  - Phân biệt rõ validation error, business error, server error.
- **Logging**
  - Dùng SLF4J (`log.info`, `log.warn`, `log.error`), không dùng `System.out.println`.
  - Log các sự kiện domain quan trọng (tạo/sửa/xóa sinh viên, phân lớp, nhập điểm, v.v.).
  - Tránh log thông tin nhạy cảm (password, token).

**Kỹ năng luyện**

- Clean code thực chiến, exception handling, logging – nền tảng bắt buộc ở senior backend.

---

## Phase 3 – Persistence & dữ liệu (ưu tiên vừa, nên làm sớm)

**Mục tiêu**

- Tầng dữ liệu ổn định, dễ migrate, dễ mở rộng, hiệu quả.

**Công việc chính**

- **Migration tool**
  - Tích hợp Flyway hoặc Liquibase để quản lý schema DB bằng script versioned.
  - Di chuyển cấu trúc bảng hiện tại sang các migration chính thức.
- **JPA/Hibernate tối ưu**
  - Kiểm tra lazy/eager loading để tránh N+1 queries.
  - Dùng `@EntityGraph` hoặc `join fetch` khi cần load nhiều quan hệ.
  - Thêm pagination (`Pageable`) cho các API list lớn (danh sách sinh viên, lớp, môn…).
- **Index & query**
  - Xác định các truy vấn thường xuyên (search theo mã sinh viên, tên, lớp, khoa…).
  - Bổ sung index ở DB nếu cần để tối ưu tốc độ truy vấn.

**Kỹ năng luyện**

- Làm việc chuẩn với DB trong app thực tế, hiểu trade-off khi dùng ORM.

---

## Phase 4 – Thiết kế API & tài liệu (ưu tiên vừa)

**Mục tiêu**

- API REST rõ ràng, nhất quán, dễ dùng cho frontend.
- Có tài liệu tự sinh để dễ trao đổi với FE/QA.

**Công việc chính**

- **Chuẩn hóa REST**
  - Đặt URL, HTTP method, status code theo best practice (GET/POST/PUT/PATCH/DELETE).
  - Tránh đặt tên endpoint theo kiểu RPC (vd: `/createStudent`), chuyển sang resource-based (`/students`).
- **API versioning**
  - Chuẩn hóa prefix (vd: `/api/v1/...`) để sau này dễ nâng version.
- **OpenAPI/Swagger**
  - Tích hợp Springdoc OpenAPI để generate tài liệu endpoint.
  - Bổ sung description, schema rõ ràng cho các DTO chính (StudentDTO, ClassDTO, SubjectDTO, …).

**Kỹ năng luyện**

- Thiết kế API cho client team, giao tiếp backend–frontend giống môi trường doanh nghiệp.

---

## Phase 5 – Testing (unit & integration) (ưu tiên rất cao nếu hiện tại thiếu test)

**Mục tiêu**

- Có bộ test tối thiểu cho business quan trọng, đủ tự tin refactor.

**Công việc chính**

- **Unit test**
  - Dùng JUnit 5 + Mockito để test service layer (đăng ký lớp, tính điểm, phân lớp, v.v.).
  - Ưu tiên test cho các rule phức tạp, dễ sai logic.
- **Integration test**
  - Test REST controller với `@SpringBootTest` hoặc `@WebMvcTest`.
  - Dùng Testcontainers (MySQL) hoặc H2 in-memory để test flow end-to-end.
- **Coverage mục tiêu**
  - Đặt target thực tế (vd: 60–70% cho core domain) thay vì 100%.

**Kỹ năng luyện**

- Viết test thực dụng, phân tầng test, design code để dễ test – kỹ năng thể hiện sự trưởng thành của senior.

---

## Phase 6 – Bảo mật (security & hardening) (ưu tiên cao nếu có người dùng thật)

**Mục tiêu**

- Bảo vệ API: authentication, authorization, bảo vệ dữ liệu nhạy cảm.

**Công việc chính**

- **Spring Security**
  - Rà soát & chuẩn hóa cấu hình: filter chain, JWT filter, login endpoint, v.v.
  - Đảm bảo mọi endpoint nhạy cảm đều được bảo vệ; chỉ mở public những gì thật sự cần.
- **Authorization**
  - Phân quyền theo role (`ADMIN`, `TEACHER`, `STUDENT`, …).
  - Dùng annotation `@PreAuthorize`, `@Secured` hoặc xử lý trong service.
- **Input & data security**
  - Đảm bảo validation đầy đủ, tránh SQL injection (khi dùng JPQL/raw SQL).
  - Không trả thừa thông tin nhạy cảm trong response (vd: password hash).
  - Ẩn thông tin nhạy cảm khỏi log (token, mật khẩu, secret).

**Kỹ năng luyện**

- Security mindset, Spring Security – bước nhảy rõ rệt từ mid lên senior.

---

## Phase 7 – Observability & cấu hình môi trường (ưu tiên vừa)

**Mục tiêu**

- App dễ theo dõi, debug, cấu hình cho nhiều môi trường (dev/test/prod).

**Công việc chính**

- **Spring Boot Actuator**
  - Bật các endpoint health, info, metrics cần thiết.
- **Profiles**
  - Tách config `application-dev.yml`, `application-test.yml`, `application-prod.yml`.
  - Dùng biến môi trường cho credential (DB password, JWT secret).
- **Logging & metrics**
  - Chuẩn hóa pattern log, thêm correlation id (nếu cần).
  - Gửi log/metrics tới tool bên ngoài (tương lai: ELK, Prometheus, Grafana…).

**Kỹ năng luyện**

- Vận hành ứng dụng, tư duy production – điều mà senior thường phải chịu trách nhiệm.

---

## Phase 8 – Performance & scalability (ưu tiên sau khi core đã ổn)

**Mục tiêu**

- Hiểu bottleneck và chuẩn bị cho scale nếu cần.

**Công việc chính**

- **Monitoring cơ bản**
  - Dùng metrics + log để tìm endpoint chậm, query nặng.
- **Caching**
  - Dùng Spring Cache (`@Cacheable`, `@CacheEvict`) cho dữ liệu đọc nhiều, ít thay đổi (vd: danh mục khoa, chuyên ngành, môn học).
- **Async & batch**
  - Với tác vụ nặng (import nhiều sinh viên, xuất báo cáo, tính điểm hàng loạt) dùng async/batch thay vì xử lý trực tiếp trong request.
- **Load test**
  - Dùng JMeter/Gatling/K6 để test vài scenario chính (search sinh viên, xem bảng điểm, phân lớp, v.v.).

**Kỹ năng luyện**

- Performance tuning, đọc metric, thiết kế để scale – plus point lớn cho senior.

---

## Phase 9 – Docker hóa & CI/CD (ưu tiên vừa, rất tốt cho portfolio)

**Mục tiêu**

- App build–test–chạy theo cách gần giống production, thể hiện kỹ năng DevOps cơ bản.

**Công việc chính**

- **Docker hóa**
  - Viết `Dockerfile` tối ưu (multi-stage build) cho Spring Boot 2.7 + Java 11.
  - Tạo `docker-compose.yml` để chạy app + MySQL local nhanh chóng.
- **CI/CD**
  - Dùng GitHub Actions/GitLab CI để tạo pipeline:
    - Bước build + test.
    - (Tùy chọn) chạy static analysis (SonarQube).
    - (Tùy chọn) build Docker image và push lên registry.

**Kỹ năng luyện**

- Hiểu pipeline thực tế, làm việc mượt với DevOps team.

---

## Phase 10 – Tài liệu kiến trúc & hoàn thiện portfolio

**Mục tiêu**

- Đóng gói project như một sản phẩm hoàn chỉnh, đủ tự tin đưa vào CV/portfolio.

**Công việc chính**

- **Architecture doc**
  - Viết 1–2 file markdown (vd: `docs/architecture.md`) mô tả:
    - Bối cảnh bài toán & yêu cầu business.
    - Kiến trúc backend (các module chính, data flow).
    - Các quyết định kỹ thuật quan trọng (auth, DB, caching, security, v.v.).
- **API docs**
  - Dùng Swagger UI/OpenAPI đã tích hợp ở Phase 4, link trong `README.md`.
- **Hướng dẫn phát triển**
  - Hướng dẫn cách chạy project, cách setup môi trường, convention code.
  - Gợi ý quy trình thêm feature mới (từ model → repository → service → controller → test).

**Kỹ năng luyện**

- Communication & documentation – yếu tố rất quan trọng khi đánh giá senior.

---

## Thứ tự ưu tiên khuyến nghị

1. **Phase 0–2**: Dọn dẹp, kiến trúc, code quality, exception/logging.
2. **Phase 5**: Testing (có thể làm song song với Phase 1–2 cho phần refactor nhiều).
3. **Phase 3 & 4**: Persistence/migration + thiết kế & tài liệu API.
4. **Phase 6 & 7**: Security + observability & cấu hình môi trường.
5. **Phase 8 & 9**: Performance & Docker + CI/CD.
6. **Phase 10**: Tài liệu kiến trúc, polish cho portfolio.

