package com.example.studentmanagement.service.impl;

import com.example.studentmanagement.constants.ClassConstant;
import com.example.studentmanagement.constants.Status;
import com.example.studentmanagement.constants.StudentConstant;
import com.example.studentmanagement.constants.SubjectConstant;
import com.example.studentmanagement.dto.ClassStudentDto;
import com.example.studentmanagement.dto.ClazzDto;
import com.example.studentmanagement.dto.MessageResponse;
import com.example.studentmanagement.model.Class;
import com.example.studentmanagement.model.*;
import com.example.studentmanagement.repository.*;
import com.example.studentmanagement.service.ClassService;
import com.example.studentmanagement.utils.DateTimeUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClassServiceImpl implements ClassService {
    private final MessageResponse messageResponse;
    private final ClassRepository classRepository;
    private final CourseRepository courseRepository;
    private final ClassStudentRepository classStudentRepository;
    private final StudentRepository studentRepository;
    private final ClassCourseRepository classCourseRepository;
    private final ObjectMapper objectMapper;
    private final ClassSubjectRepository classSubjectRepository;
    private final SubjectRepository subjectRepository;
    private final ClassSemesterRepository classSemesterRepository;

    public ClassServiceImpl(MessageResponse messageResponse, ClassRepository classRepository, CourseRepository courseRepository, ClassStudentRepository classStudentRepository, StudentRepository studentRepository, ClassCourseRepository classCourseRepository, ObjectMapper objectMapper,
                            ClassSubjectRepository classSubjectRepository,
                            SubjectRepository subjectRepository,
                            ClassSemesterRepository classSemesterRepository) {
        this.messageResponse = messageResponse;
        this.classRepository = classRepository;
        this.courseRepository = courseRepository;
        this.classStudentRepository = classStudentRepository;
        this.studentRepository = studentRepository;
        this.classCourseRepository = classCourseRepository;
        this.objectMapper = objectMapper;
        this.classSubjectRepository = classSubjectRepository;
        this.subjectRepository = subjectRepository;
        this.classSemesterRepository = classSemesterRepository;
    }

    /**
     * Hàm này dùng để tạo mới một lớp học
     *
     * @param clazz
     * @return MessageResponse
     */
    @Override
    public MessageResponse saveClazz(Class clazz) {
        Class clazz1 = classRepository.save(clazz);
        // thay vì set từng trường như này thì có thể trả ra một new MessageResponse khởi tạo bởi constructor
        messageResponse.setMessage("Create class successful");
        messageResponse.setData(clazz1);
        return messageResponse;
    }

    /**
     * Hàm này dùng để lấy ra tất cả danh sách lớp
     *
     * @return MessageResponse chứa danh sách lớp
     */
    public MessageResponse getAllClazz() {
        List<Class> classes = classRepository.findAll();
        return new MessageResponse("okk", 200, classes);
    }

    /**
     * Hàm này dùng để lấy ra một lớp học theo id
     *
     * @param classId
     * @return MessageResponse
     */
    public MessageResponse getClassById(int classId) {
        Optional<Class> classes = classRepository.findById(classId);
        if (classes.isEmpty()) {
            return new MessageResponse("fail", 400, null);
        }
        Class clazz = classes.get();
        ClazzDto clazzDto = convertToClazzDto(clazz, "yyyy-MM-dd");
        return new MessageResponse("Get clazz by id successful", 200, clazzDto);
    }

    /**
     * Hàm này dùng để lấy ra danh sách clazz theo bộ lọc filter
     *
     * @param name
     * @param type
     * @param status
     * @param sortSelected
     * @param page
     * @param pageSize
     * @return MessageResponse
     */
    public MessageResponse getAllClazzByFilter(String name, int type, int status, String sortSelected, int page, int pageSize) {
        var page1 = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.ASC, sortSelected));

        Specification<Class> specification = Specification.where(null);
        if (StringUtils.isNotBlank(name)) {
            specification = specification.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
        }
        if (type != 2) {
            specification = specification.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("type"), type));
        }
        if (status != 2) {
            specification = specification.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("status"), status));
        }
        var classes = classRepository.findAll(specification, page1);

        List<ClazzDto> result = new ArrayList<>();
        List<Class> classes1 = classes.getContent();
        classes1.forEach(clazz -> {
            ClazzDto clazzDto = convertToClazzDto(clazz, "dd/MM/yyyy");
            result.add(clazzDto);
        });
        return new MessageResponse("Get all clazz by filter successful", 200, result, classes.getTotalPages(), classes.getTotalElements());
    }

    // read clazz
    @Override
    public MessageResponse getAllClazzByCourseId(int courseId) {
//        List<?> studentList = classRepository.findAll();
        Optional<Course> courseOpt = courseRepository.findById(courseId);
        if (courseOpt.isEmpty()) {
            messageResponse.setData(null);
            messageResponse.setMessage("Course does not exist");
            return messageResponse;
        }
        List<ClassCourse> classCourses = classCourseRepository.findAllByCourseId(courseId);
        if (classCourses.isEmpty()) {
            messageResponse.setData(null);
            messageResponse.setMessage("Dont have clazz in course");
            return messageResponse;
        }
        // cach 1: truyen vao 1 list classIds de lay ra lop
        List<Integer> classIds = new ArrayList<>();
        classCourses.forEach((classCourse) -> {
            classIds.add(classCourse.getClassId());
        });
        List<Class> classes1 = classRepository.findAllByIdIn(classIds);

        // cach 2: vong lap nhung se truy suat nhieu vao db
        // khong uu tien
        List<Class> classes2 = new ArrayList<>();
        classCourses.forEach((classCourse) -> {
            Optional<Class> classOpt = classRepository.findById(classCourse.getClassId());
            classOpt.ifPresent(classes2::add);
        });

        messageResponse.setData(classes1);
        messageResponse.setMessage("Get all class successful");
        return messageResponse;
    }

    // get all class by SemesterId
    public MessageResponse getAllClazzBySemesterId(int semesterId) {
        List<ClassSemester> classSemesters = classSemesterRepository.findAllBySemesterId(semesterId);
        if (classSemesters.isEmpty()) {
            return new MessageResponse("Không tồn tại lớp trong class_semester", 400, null);
        }
        // lấy ra list classId từ classSemesters
        List<Integer> classIds = new ArrayList<>();
        classSemesters.forEach(classSemester -> {
            classIds.add(classSemester.getClassId());
        });
        List<Class> classes = classRepository.findAllByIdIn(classIds);
        return new MessageResponse("Get all class successful", 200, classes);
    }


    /**
     * Hàm này dùng để chỉnh sửa một lớp học theo id
     *
     * @param clazzDto
     */
    @Override
    public MessageResponse updateClazzById(ClazzDto clazzDto) {
        Class updateClazz = classRepository.findById(Integer.valueOf(clazzDto.getId())).orElse(null);
        if (updateClazz == null) {
            return new MessageResponse("class id does not exist", 400, null);
        }
        Class clazz = new Class();
        clazz.setId(Integer.valueOf(clazzDto.getId()));
        clazz.setName(clazzDto.getName());
        clazz.setCode(clazzDto.getCode());
        clazz.setNumber(Integer.valueOf(clazzDto.getNumber()));
        clazz.setLimitClass(Integer.valueOf(clazzDto.getLimitClass()));
        if (clazzDto.getType().equals("Offline")) {
            clazz.setType(1);
        } else {
            clazz.setType(0);
        }
        if (clazzDto.getStatus().equals("Hoạt động")) {
            clazz.setStatus(Status.HOAT_DONG);
        } else {
            clazz.setStatus(Status.valueOf("KHONG_HOAT_DONG"));
        }
        clazz.setAddress(clazzDto.getAddress());
        clazz.setStartDate(DateTimeUtil.stringToDate(clazzDto.getStartDate()));
        clazz.setEndDate(DateTimeUtil.stringToDate(clazzDto.getEndDate()));

        classRepository.save(clazz);
        return new MessageResponse("update class successful", 200, clazz);
    }

    /**
     * Hàm này sẽ xóa một lớp khỏi cơ sở dữ liệu
     *
     * @param id
     * @return MessageResponse
     */
    @Override
    public MessageResponse deleteClazzById(int id) {
        Optional<Class> clazz = classRepository.findById(id);
        if (clazz.isEmpty()) {
            return new MessageResponse("class id does not exist", 400, clazz);
        }
        classRepository.deleteById(id);
        return new MessageResponse("delete class successful", 200, null);
    }

    /**
     * Hàm này dùng để xóa mềm một lớp học (chỉ thay đổi trạng thái -> không hoạt dộng)
     *
     * @param id
     * @return MessageResponse
     */
    public MessageResponse deleteSoftClazzById(int id) {
        Optional<Class> clazz = classRepository.findById(id);
        if (clazz.isEmpty()) {
            return new MessageResponse("this clazz does not exist", 400, null);
        }
        Class clazzSoft = clazz.get();
        clazzSoft.setStatus(Status.KHONG_HOAT_DONG);
        classRepository.save(clazzSoft);
        return new MessageResponse("Delete soft successfully", 200, clazzSoft);
    }

    // lấy thông tin từ 2 bảng
    public MessageResponse getInfoSubjectAndClass(int classId) {
        List<ObjectNode> result = new ArrayList<>();

        List<Class> classes = classRepository.findAll();

        classes.forEach(clazz -> {
            ObjectNode node = objectMapper.createObjectNode();
            node.put(ClassConstant.classStartDate, String.valueOf(clazz.getStartDate()));
            node.put(ClassConstant.classEndDate, String.valueOf(clazz.getEndDate()));
            node.put(ClassConstant.classAddress, clazz.getAddress());

            List<ClassSubject> classSubjects = classSubjectRepository.findAllByClassIdOrderByIdDesc(classId);
            if (!classSubjects.isEmpty()) {
                List<Integer> classIds = new ArrayList<>();
                for (int i = 0; i < classSubjects.size(); i++) {
                    Optional<Subject> subjectFound = subjectRepository.findById(classSubjects.get(i).getSubjectId());
                    subjectFound.ifPresent(subject -> node.put(SubjectConstant.subjectName, subject.getName()));
                }
            } else {
                node.put(SubjectConstant.subjectName, "This subject does not have class");
            }
            result.add(node);
        });
        return new MessageResponse("Success", 200, result);
    }

    public MessageResponse getInfoClassAndStudent() {
        List<ObjectNode> result = new ArrayList<>();
        List<Class> classes = classRepository.findAll();

        classes.forEach(clazz -> {
            ObjectNode node = objectMapper.createObjectNode();
            node.put(ClassConstant.className, clazz.getName());

            List<ClassStudent> classStudents = classStudentRepository.findAll();
            if (classStudents.isEmpty()) {
                node.put(StudentConstant.studentName, "This student does not have class");
            } else {
                for (ClassStudent classStudent : classStudents) {
                    Optional<Student> studentFound = studentRepository.findById(classStudent.getStudentId());
                    if (studentFound.isPresent()) {
                        node.put(StudentConstant.studentCode, studentFound.get().getCode());
                        node.put(StudentConstant.studentName, studentFound.get().getFirstName() + " " + studentFound.get().getLastName());
                        node.put(StudentConstant.studentGender, studentFound.get().getSex().getDescription());
                        node.put(StudentConstant.studentEmail, studentFound.get().getEmail());
                        node.put(StudentConstant.studentPhone, studentFound.get().getPhone());
                        // !!!!!!!!
                        node.put(StudentConstant.studentStatus, studentFound.get().getStatus().getDescription());
                        node.put(StudentConstant.studentBirthday, String.valueOf(studentFound.get().getBirthday()));
                    }
                }
            }
            result.add(node);
        });
        return new MessageResponse("Success", 200, result);
    }

    public MessageResponse getInfoClassAndStudent2() {
        List<ClassStudentDto> result = new ArrayList<>();
        List<Class> classes = classRepository.findAll();

        classes.forEach(clazz -> {
            ClassStudentDto classStudentDto = new ClassStudentDto();
            classStudentDto.setClassName(clazz.getName());

            List<ClassStudent> classStudents = classStudentRepository.findAll();
            if (classStudents.isEmpty()) {
                classStudentDto.setStudentName("This student does not have class");
            } else {
                for (int i = 0; i < classStudents.size(); i++) {
                    Optional<Student> studentFound = studentRepository.findById(classStudents.get(i).getStudentId());
                    if (studentFound.isPresent()) {
                        classStudentDto.setStudentCode(studentFound.get().getCode());
                        classStudentDto.setStudentName(studentFound.get().getFirstName() + " " + studentFound.get().getLastName());
                        classStudentDto.setStudentGender(String.valueOf(studentFound.get().getSex()));
                        classStudentDto.setStudentEmail(studentFound.get().getEmail());
                        classStudentDto.setStudentPhone(studentFound.get().getPhone());
                        classStudentDto.setStudentStatus(String.valueOf(studentFound.get().getStatus()));
                        classStudentDto.setStudentBirthday(String.valueOf(studentFound.get().getBirthday()));
                    }
                }
            }
            result.add(classStudentDto);
        });
        return new MessageResponse("Success", 200, result);
    }

    /**
     * Lấy ra danh sách lớp học theo tên lớp (người dùng chỉ cần nhập chuỗi có chứa trong className, không phân biệt hoa thường )
     * ví dụ: tên lớp là : Lớp 10a3 thì người dùng chỉ cần nhập 1 hoặc a hoặc 10A thì vẫn sẽ hiển thị kết quả
     */
    public MessageResponse getAllClazzByFilterLikeClassName(String className) {
        List<Class> classes = classRepository.findByNameContainingIgnoreCaseOrderByNameDesc(className);
        return new MessageResponse("Get clazz by name success", 200, classes);
    }

    /**
     * Hàm này dùng để chuyển đổi từ Class -> ClazzDto
     *
     * @param claz
     * @param format
     * @return clazzDto
     */
    private ClazzDto convertToClazzDto(Class claz, String format) {
        ClazzDto clazzDto = new ClazzDto();
        clazzDto.setId(String.valueOf(claz.getId()));
        clazzDto.setName(claz.getName());
        clazzDto.setCode(claz.getCode());
        clazzDto.setNumber(String.valueOf(claz.getNumber()));
        clazzDto.setLimitClass(String.valueOf(claz.getLimitClass()));
        clazzDto.setType(claz.getType());
        clazzDto.setStatus(String.valueOf(claz.getStatus().getDescription()));
        clazzDto.setAddress(claz.getAddress());
        clazzDto.setStartDate(DateTimeUtil.dateToString(claz.getStartDate(), format));
        clazzDto.setEndDate(DateTimeUtil.dateToString(claz.getEndDate(), format));
        return clazzDto;
    }

}
