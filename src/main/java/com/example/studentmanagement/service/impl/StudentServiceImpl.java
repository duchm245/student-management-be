package com.example.studentmanagement.service.impl;

import com.example.studentmanagement.constants.ClassConstant;
import com.example.studentmanagement.constants.Status;
import com.example.studentmanagement.constants.StudentConstant;
import com.example.studentmanagement.dto.ClassStudentDto;
import com.example.studentmanagement.dto.MessageResponse;
import com.example.studentmanagement.model.Class;
import com.example.studentmanagement.model.ClassStudent;
import com.example.studentmanagement.model.Student;
import com.example.studentmanagement.repository.*;
import com.example.studentmanagement.service.StudentService;
import com.example.studentmanagement.utils.DateTimeUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class StudentServiceImpl implements StudentService {

    private final MessageResponse messageResponse;
    private final StudentRepository studentRepository;
    private final ClassRepository classRepository;
    private final ClassStudentRepository classStudentRepository;
    private final ObjectMapper objectMapper;


    public StudentServiceImpl(MessageResponse messageResponse, StudentRepository studentRepository, CourseRepository courseRepository, ClassRepository classRepository, ClassStudentRepository classStudentRepository, ClassCourseRepository classCourseRepository, ObjectMapper objectMapper, EntityManager entityManager) {
        this.messageResponse = messageResponse;
        this.studentRepository = studentRepository;
        this.classRepository = classRepository;
        this.classStudentRepository = classStudentRepository;
        this.objectMapper = objectMapper;
    }

    /**
     * Hàm này để tạo mới một student
     *
     * @param student
     * @return MessageResponse chứa thông tin học sinh vừa tạo
     */
    @Override
    public MessageResponse saveStudent(Student student) {
        // xử lý thêm validate:  xem email đã được sử dụng chưa -> chưa có mới cho lưu
        // ...
        Student student1 = studentRepository.save(student);
        String datetest = "2023-04-15";
        student1.setBirthday(DateTimeUtil.parseDateCommon(datetest));
        messageResponse.setMessage("Create a student successful");
        messageResponse.setData(student1);
        return messageResponse;
    }

    // create list student
    @Override
    public MessageResponse saveListStudent(List<Student> students) {
        List<Student> studentList = studentRepository.saveAll(students);
        messageResponse.setData(studentList);
        messageResponse.setMessage("Create list student successful");
        return messageResponse;
    }

    /**
     * Hàm này dùng để lấy ra một student theo id
     *
     * @param id
     * @return MessageResponse chứa một student
     */
    @Override
    public MessageResponse getStudent(int id) {
        Optional<Student> student1 = studentRepository.findById(id);
        if (student1.isEmpty()) {
            return new MessageResponse("fails", 400, null);
        }
        return new MessageResponse("Get a student successful", 200, student1);
    }

    @Override
    public MessageResponse getAllStudents() {
        List<Student> students = studentRepository.findAll();
        messageResponse.setMessage("Get all student successful");
        messageResponse.setData(students);
        return messageResponse;
    }

//    public MessageResponse getStudentNameAndClassname() {
//        // cach 1: list json
//        List<Student> students = studentRepository.findAll();
//        List<ObjectNode> result = new ArrayList<>();
//        students.forEach(student -> {
//            ObjectNode node = objectMapper.createObjectNode();
//            node.put(StudentConstant.studentCode, student.getCode());
//            node.put(StudentConstant.studentName, student.getFirstName() + " " + student.getLastName());
//            List<ClassStudent> classStudents = classStudentRepository.findAllByStudentIdOrderByIdDesc(student.getId());
//            if (!classStudents.isEmpty()) {
//                Optional<Class> classFound = classRepository.findById(classStudents.get(0).getClassId());
//                if (classFound.isPresent()) {
//                    node.put(ClassConstant.className, classFound.get().getName());
//                } else {
//                    node.put(ClassConstant.className, "Class are deleted or not found");
//                }
//            } else {
//                node.put(ClassConstant.className, "This student does not have class");
//            }
//            result.add(node);
//        });
//        return new MessageResponse("Success", 200, result);
//
//    }

//    public MessageResponse getAllStudentsByCourseIdAndClassId(int classId) {
//        Optional<Class> classOptional = classRepository.findById(classId);
//        if (classOptional.isEmpty()) {
//            return new MessageResponse("Class khong ton tai", 400, null);
//        }
//
//        List<ClassStudent> classStudents = classStudentRepository.findAllByClassId(classId);
//        if (classStudents.isEmpty()) {
//            return new MessageResponse("Student khong ton tai", 400, null);
//        }
//
//        List<Integer> studentIds = new ArrayList<>();
//        classStudents.forEach(data -> {
//            studentIds.add(data.getStudentId());
//        });
//
//        // lấy ra danh sách học sinh theo list id
//        List<Student> students = studentRepository.findAllByIdIn(studentIds);
//
//        return new MessageResponse("Get all student by class successful", 200, students);
//    }

//    public MessageResponse getAllStudentByStatus(int status) {
//        List<Student> students = studentRepository.findAllByStatus(status);
//        return new MessageResponse("Get all student by status successful", 200, students);
//    }

    /**
     * Hàm này dùng để lấy tất cả học sinh theo filter
     *
     * @param classId
     * @param gender
     * @param status
     * @param name
     * @param sortSelected
     * @param page
     * @param pageSize
     * @return MessageResponse chứa một list các classStudentDto, tổng số page và element
     */
    public MessageResponse getAllStudentByFilter(int classId, int gender, int status, String name, String sortSelected, int page, int pageSize) {
        var page1 = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.ASC, sortSelected));
        // Bóc tách studentId từ các bản ghi của bảng class_student vào trong một list
        List<Integer> studentIds = new ArrayList<>();
        List<ClassStudent> classStudents = classStudentRepository.findAllByClassId(classId);
        if (!classStudents.isEmpty()) {
            classStudents.forEach(data -> {
                studentIds.add(data.getStudentId());
            });
        }
        // Tạo đối tượng Specification để xây dựng câu truy vấn động
        Specification<Student> specification = Specification.where(null);
        if (StringUtils.isNotBlank(name)) {
            specification = specification.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("lastName")), "%" + name.toLowerCase() + "%"));
        }
        if (gender != 2) {
            specification = specification.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("sex"), gender));
        }
        if (status != 2) {
            specification = specification.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("status"), status));
        }
        if (!studentIds.isEmpty() || classId > 0) {
            specification = specification.and((root, query, criteriaBuilder) ->
                    root.get("id").in(studentIds));
        }
        var students = studentRepository.findAll(specification, page1); // Thực hiện truy vấn với các điều kiện đã xây dựng
        List<Student> students1 = students.getContent();

        // Chuyển list students1 vào ClassStudentDto để hiển thị
        List<ClassStudentDto> result = new ArrayList<>();
        students1.forEach(st -> {
            ClassStudentDto classStudentDto = new ClassStudentDto();
            classStudentDto.setStudentId(st.getId());
            classStudentDto.setStudentCode(st.getCode());
            classStudentDto.setStudentName(st.getFirstName() + " " + st.getLastName());
            classStudentDto.setStudentGender(String.valueOf(st.getSex().getDescription()));
            classStudentDto.setStudentEmail(st.getEmail());
            classStudentDto.setStudentPhone(st.getPhone());
            classStudentDto.setStudentStatus(String.valueOf(st.getStatus().getDescription()));
            classStudentDto.setStudentBirthday(DateTimeUtil.dateToString(st.getBirthday(), "dd/MM/yyyy"));

            // Set lớp đang học cho học sinh
            List<ClassStudent> classStudent2 = classStudentRepository.findByStudentId(st.getId());
            Optional<Class> clazz = Optional.empty();
            for (ClassStudent cl : classStudent2) {
                if (classId == 0) { // Nếu không truyền xuống tên lớp cụ thể
                    if (cl.getStatus() != null && cl.getStatus() == 1) {
                        clazz = classRepository.findById(cl.getClassId());
                    }
                } else { // Nếu truyền xuống tên lớp cụ thể
                    if (cl.getStatus() != null && cl.getStatus() == 1 && cl.getClassId() == classId) {
                        clazz = classRepository.findById(cl.getClassId());
                    }
                }
            }
            clazz.ifPresent(c -> {
                classStudentDto.setClassName(c.getName());
                classStudentDto.setClassId(c.getId());
//                result.add(classStudentDto); // nếu để đây thì khi tìm kiếm theo lớp chỉ những học sinh hiện tại đang học lớp đó mới hiển thị
            });
            result.add(classStudentDto); // nếu để ở đây thì khi tìm kiếm theo lớp sẽ hiển thị cả những học sinh đã từng học lớp đó
        });
        return new MessageResponse("Get all student by filter successful", 200, result, students.getTotalPages(), students.getTotalElements());
    }

//    public MessageResponse getAllStudentByFilter(String name, int gender, int status, int classId, int pageNumber, int sizeNumber) {
//
//        // cach 1: dung Example
//        var studentMatch = new Student();
//        if (!"".equals(name)) studentMatch.setLastName(name); // uc1
//        if (status != 0) studentMatch.setStatus(status);
//        if (gender != 0) studentMatch.setSex(gender);
//
//        var exampleMatcher = ExampleMatcher.matchingAll().withMatcher("lastName", ExampleMatcher.GenericPropertyMatcher::contains);
//        var studentExample = Example.of(studentMatch, exampleMatcher);
//        var page = PageRequest.of(pageNumber, sizeNumber, Sort.by(Sort.Direction.DESC, "id"));
//        var students = studentRepository.findAll(studentExample, page);
//
//        log.info(students.getTotalElements());
//        log.info(students.getTotalPages());
//
//
//        // cach 2: dung native
//        var sql = new StringBuilder();
//        sql.append("select * from student where 1=1 ");
//        if (!"".equals(name)) sql.append(" and last_name like '%").append(name).append("%' ");
//        if (status != 0) sql.append("and status = ").append(status);
//        if (gender != 0) sql.append(" and sex = ").append(gender);
//
//        var students2 = new ArrayList<>();
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//        ResultSet rs = null;
//        try {
//            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_management", "root", "1234");
//            preparedStatement = connection.prepareStatement(sql.toString());
//            rs = preparedStatement.executeQuery();
//            while (rs.next()) {
//                var student = new Student();
//                student.setId(rs.getInt("student_id"));
//                student.setFirstName(rs.getString("first_name"));
//                student.setLastName(rs.getString("last_name"));
//                students2.add(student);
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } finally {
//            try {
//                if (connection != null) {
//                    connection.close();
//                }
//                if (preparedStatement != null) {
//                    preparedStatement.close();
//                }
//                if (rs != null) {
//                    rs.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//
//        // cach 3 :specification
//        var builder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Student> query = builder.createQuery(Student.class);
//        Root<Student> root = query.from(Student.class);
//        var isGenderLargtherThan = builder.greaterThanOrEqualTo(root.get("sex"), 1);
//        var isStatusGreatherThan = builder.greaterThan(root.get("status"), 0);
//        query.where(builder.and(isGenderLargtherThan, isStatusGreatherThan));
//        var students3 = entityManager.createQuery(query.select(root)).getResultList();
//
//
//        // cach 4:Specification Sử dụng Jpa lấy ra danh dách học sinh theo classId, name, gender, status
//        Optional<Class> classOptional = classRepository.findById(classId);
//        if (classOptional.isEmpty()) {
//            return new MessageResponse("Class khong ton tai", 400, null);
//        }
//
//        List<ClassStudent> classStudents = classStudentRepository.findAllByClassId(classId);
//        if (classStudents.isEmpty()) {
//            return new MessageResponse("Student khong ton tai", 400, null);
//        }
//
//        List<Integer> studentIds = new ArrayList<>();
//        classStudents.forEach(data -> {
//            studentIds.add(data.getStudentId());
//        });
//
//        // lay ra danh sach hoc sinh theo studentIds, name, gender, status
//        var students5 = studentRepository.findStudentByIdInAndLastNameContainingAndStatusAndSex(studentIds, name, status, gender, page);
//        return new MessageResponse("Get all student by filter successful", 200, students5, students.getTotalPages());
//
////        return new MessageResponse("Get all student by status successful", 200, students4.getContent(), students.getTotalPages());
//
//    }


    /**
     * Hàm này dùng để update một student theo id
     *
     * @param student
     * @param id
     * @return MessageResponse chứa student đã được update
     */
    @Override
    public MessageResponse updateStudentById(Student student, int id) {
        Student student1 = studentRepository.findStudentById(id).orElse(null);
        if (student1 == null) {
            return new MessageResponse("Student id does not exist", 400, null);
        }
        // xử lý thêm điều kiện validate...
        student1.setFirstName(student.getFirstName());
        student1.setLastName(student.getLastName());
        student1.setCode(student.getCode());
        student1.setSex(student.getSex());
        student1.setBirthday(student.getBirthday());
        student1.setEmail(student.getEmail());
        student1.setPhone(student.getPhone());
        student1.setAddress(student.getAddress());
        student1.setStatus(student.getStatus());
        studentRepository.save(student1);
        return new MessageResponse("Update student successful", 200, student1);
    }

    /**
     * Hàm này dùng để xóa một học sinh khỏi cơ sở dữ liệu
     *
     * @param id
     */
    @Override
    public MessageResponse deleteStudentById(int id) {
        Optional<Student> deleteStudent = studentRepository.findStudentById(id);
        if (deleteStudent.isEmpty()) {
            return new MessageResponse("Student id does not exist", 400, null);
        }
        studentRepository.deleteById(id);
        return new MessageResponse("Delete student successful", 200, null);

    }

    /**
     * Hàm này sẽ xóa mềm, chỉ thay đổi trường status -> không hoạt động
     *
     * @param id
     */
    public MessageResponse deleteSoftStudentById(int id) {
        Optional<Student> deleteStudent = studentRepository.findStudentById(id);
        if (deleteStudent.isEmpty()) {
            return new MessageResponse("Student id does not exist", 400, null);
        }
        deleteStudent.get().setStatus(Status.KHONG_HOAT_DONG);
        studentRepository.save(deleteStudent.get());
        return new MessageResponse("Delete soft student successful", 200, null);
    }

    /**
     * Hàm này để kiểm tra xem email đã được sử dụng chưa
     *
     * @param email
     */
    public MessageResponse checkEmailExist(String email) {
        boolean check = true;
        List<Student> students = studentRepository.findAllByEmailIgnoreCase(email);
        if (students.isEmpty()) {
            check = false;
        }
        return new MessageResponse("true: exist && false: no exist", 200, check);
    }
}
