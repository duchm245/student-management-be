package com.example.studentmanagement.service.impl;

import com.example.studentmanagement.dto.MarkDto;
import com.example.studentmanagement.dto.MarkTeacherDto;
import com.example.studentmanagement.dto.MessageResponse;
import com.example.studentmanagement.model.Mark;
import com.example.studentmanagement.model.Student;
import com.example.studentmanagement.model.Subject;
import com.example.studentmanagement.repository.MarkRepository;
import com.example.studentmanagement.repository.StudentRepository;
import com.example.studentmanagement.repository.SubjectRepository;
import com.example.studentmanagement.service.MarkService;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

@Log4j2
@Service
public class MarkServiceImpl implements MarkService {
    private final MarkRepository markRepository;
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;

    public MarkServiceImpl(MarkRepository markRepository, StudentRepository studentRepository, SubjectRepository subjectRepository) {
        this.markRepository = markRepository;
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
    }

//    public MessageResponse getMarkByFilter(int clazzId, int subjectId, int studentId) {
//        Optional<Student> student = studentRepository.findStudentById(studentId);
//        Optional<Subject> subject = subjectRepository.findById(subjectId);
//        // Lấy ra các bản ghi theo clazzId, studentId và subjectId :
//        if (student.isEmpty()) {
//            return new MessageResponse("Hoc sinh nay k ton tai", 200, null);
//        }
//        Specification<Mark> specification = Specification.where(null);
//        if (clazzId != 0) {
//            specification = specification.and((root, query, criteriaBuilder) ->
//                    criteriaBuilder.equal(root.get("classId"), clazzId));
//        }
//        if (studentId != 0) {
//            specification = specification.and((root, query, criteriaBuilder) ->
//                    criteriaBuilder.equal(root.get("studentId"), studentId));
//        }
//        if (subjectId != 0) {
//            specification = specification.and((root, query, criteriaBuilder) ->
//                    criteriaBuilder.equal(root.get("subjectId"), subjectId));
//        }
//        var mark = markRepository.findAll(specification);
//
//        // Lọc bản ghi theo điểm kiểm tra miệng và add vào list tương ứng :
//        MarkDto markDto = new MarkDto();
//        double mark1;
//        double mark2;
//        double mark3;
//        List<Float> markList1 = new ArrayList<>();
//        List<Float> markList2 = new ArrayList<>();
//        List<Float> markList3 = new ArrayList<>();
//
//        subject.ifPresent(name -> markDto.setSubjectName(name.getName()));
//        for (Mark item : mark) {
//            if (item.getType() == 1) {
//                markList1.add(item.getMark());
//            }
//            if (item.getType() == 2) {
//                markList2.add(item.getMark());
//            }
//            if (item.getType() == 3) {
//                markList3.add(item.getMark());
//            }
//        }
//        // tính điểm trung bình
////        for (float item : markList1) {
////            mark1 += item;
////        }
////        mark1 = mark1 / markList1.size();
////        for (float item : markList2) {
////            mark2 += item;
////        }
////        mark2 = mark2 / markList2.size();
////        for (float item : markList3) {
////            mark3 += item;
////        }
////        mark3 = mark3 / markList3.size();
//        mark1 = markList1.stream().mapToDouble(Float::doubleValue).average().orElse(0);
//        mark2 = markList2.stream().mapToDouble(Float::doubleValue).average().orElse(0);
//        mark3 = markList3.stream().mapToDouble(Float::doubleValue).average().orElse(0);
//
//        var avg = mark1 * 0.2 + mark2 * 0.3 + mark3 * 0.5;
//
//        markDto.setExam1(markList1);
//        markDto.setExam2(markList2);
//        markDto.setExam3(markList3);
//        markDto.setAvg(avg);
//        return new MessageResponse("Get all mark by filter successful", 200, markDto);
//    }

    /**
     * Hàm này dùng để xem điểm theo danh sách môn học của một học sinh
     *
     * @param clazzId
     * @param studentId
     * @return MessageResponse chứa danh sách điểm
     */
    public MessageResponse getMarkByFilter2(int clazzId, int studentId) {
        Optional<Student> student = studentRepository.findStudentById(studentId);

        // Lấy ra các bản ghi theo clazzId, studentId :
        if (student.isEmpty()) {
            return new MessageResponse("Hoc sinh nay k ton tai", 200, null);
        }
        Specification<Mark> specification = Specification.where(null);
        if (clazzId != 0) {
            specification = specification.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("classId"), clazzId));
        }
        if (studentId != 0) {
            specification = specification.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("studentId"), studentId));
        }
        var mark = markRepository.findAll(specification);

        // Bóc tách danh sách môn học
        Set<Integer> set = new HashSet<>();
        mark.forEach(item -> {
            set.add(item.getSubjectId());
        });

        List<MarkDto> markDtos = new ArrayList<>();
        set.forEach(i -> {
            var findBySubjectId = mark.stream().filter(item -> item.getSubjectId() == i).collect(Collectors.toList());

            // Lọc bản ghi theo từng loại và add vào list tương ứng :
            MarkDto markDto = new MarkDto();
            double mark1;
            double mark2;
            double mark3;
            List<Float> markList1 = new ArrayList<>();
            List<Float> markList2 = new ArrayList<>();
            List<Float> markList3 = new ArrayList<>();

            Optional<Subject> subject = subjectRepository.findById(i);
            subject.ifPresent(name -> markDto.setSubjectName(name.getName()));
            for (Mark item : findBySubjectId) {
                if (item.getType() == 1) {
                    markList1.add(item.getMark());
                }
                if (item.getType() == 2) {
                    markList2.add(item.getMark());
                }
                if (item.getType() == 3) {
                    markList3.add(item.getMark());
                }
            }
            mark1 = markList1.stream().mapToDouble(Float::doubleValue).average().orElse(0);
            mark2 = markList2.stream().mapToDouble(Float::doubleValue).average().orElse(0);
            mark3 = markList3.stream().mapToDouble(Float::doubleValue).average().orElse(-1);

            markDto.setExam1(markList1);
            markDto.setExam2(markList2);
            var avg = 0d;
            if (mark3 != -1) {
                markDto.setExam3(markList3);
                avg = mark1 * 0.2 + mark2 * 0.3 + mark3 * 0.5;

//                markDto.setAvg(avg);
                DecimalFormat df = new DecimalFormat("#.##");
                markDto.setAvg(Double.parseDouble(df.format(avg)));
            }
            markDtos.add(markDto);
        });
        return new MessageResponse("Get all mark by filter successful", 200, markDtos);
    }

    /**
     * Hàm này dùng để xem điểm của một lớp theo từng môn
     *
     * @param clazzId
     * @param subjectId
     * @return
     */
    public MessageResponse getMarkByFilter3(int clazzId, int subjectId) {
        Specification<Mark> specification = Specification.where(null);
        if (clazzId != 0) {
            specification = specification.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("classId"), clazzId));
        }
        if (subjectId != 0) {
            specification = specification.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("subjectId"), subjectId));
        }
        var mark = markRepository.findAll(specification);

        // Bóc tách danh sách học sinh
        Set<Integer> set = new HashSet<>();
        mark.forEach(item -> {
            set.add(item.getStudentId());
        });

        List<MarkTeacherDto> markDtos = new ArrayList<>();
        set.forEach(i -> {
            var findByStudentId = mark.stream().filter(item -> item.getStudentId() == i).collect(Collectors.toList());

            // Lọc bản ghi theo từng loại và add vào list tương ứng :
            MarkTeacherDto markDto = new MarkTeacherDto();
            double mark1;
            double mark2;
            double mark3;
            List<Float> markList1 = new ArrayList<>();
            List<Float> markList2 = new ArrayList<>();
            List<Float> markList3 = new ArrayList<>();

            Optional<Student> student = studentRepository.findById(i);
            student.ifPresent(name -> markDto.setStudentName(name.getFirstName() + " " + name.getLastName()));
            for (Mark item : findByStudentId) {
                if (item.getType() == 1) {
                    markList1.add(item.getMark());
                }
                if (item.getType() == 2) {
                    markList2.add(item.getMark());
                }
                if (item.getType() == 3) {
                    markList3.add(item.getMark());
                }
            }
            mark1 = markList1.stream().mapToDouble(Float::doubleValue).average().orElse(0);
            mark2 = markList2.stream().mapToDouble(Float::doubleValue).average().orElse(0);
            mark3 = markList3.stream().mapToDouble(Float::doubleValue).average().orElse(-1);

            markDto.setExam1(markList1);
            markDto.setExam2(markList2);
            var avg = 0d;
            if (mark3 != -1) {
                markDto.setExam3(markList3);
                avg = mark1 * 0.2 + mark2 * 0.3 + mark3 * 0.5;
//                markDto.setAvg(avg);
                DecimalFormat df = new DecimalFormat("#.##");
                markDto.setAvg(Double.parseDouble(df.format(avg)));
            }
            markDtos.add(markDto);
        });
        return new MessageResponse("Get all mark by filter successful", 200, markDtos);
    }


}
