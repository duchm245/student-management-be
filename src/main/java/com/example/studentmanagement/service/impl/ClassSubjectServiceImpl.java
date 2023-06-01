package com.example.studentmanagement.service.impl;

import com.example.studentmanagement.dto.MessageResponse;
import com.example.studentmanagement.dto.TimeLineDayDto;
import com.example.studentmanagement.dto.TimeLineDto;
import com.example.studentmanagement.dto.TimeLineDto2;
import com.example.studentmanagement.model.ClassSubject;
import com.example.studentmanagement.model.Subject;
import com.example.studentmanagement.repository.ClassRepository;
import com.example.studentmanagement.repository.ClassSubjectRepository;
import com.example.studentmanagement.repository.SubjectRepository;
import com.example.studentmanagement.service.ClassSubjectService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ClassSubjectServiceImpl implements ClassSubjectService {
    private final ClassSubjectRepository classSubjectRepository;
    private final ObjectMapper objectMapper;
    private final ClassRepository classRepository;
    private final SubjectRepository subjectRepository;

    public ClassSubjectServiceImpl(ClassSubjectRepository classSubjectRepository, ObjectMapper objectMapper,
                                   ClassRepository classRepository, SubjectRepository subjectRepository) {
        this.classSubjectRepository = classSubjectRepository;
        this.objectMapper = objectMapper;
        this.classRepository = classRepository;
        this.subjectRepository = subjectRepository;
    }

    /**
     * Hàm này dùng để tạo thời khóa biểu
     *
     * @param tl
     * @return MessageResponse
     */
    public MessageResponse createTimeLine(TimeLineDto2 tl) {
        List<ClassSubject> resultSave = new ArrayList<>();

        List<Integer> subjectList = new ArrayList<>();
        subjectList.add(tl.getSubjectId1());
        subjectList.add(tl.getSubjectId2());
        subjectList.add(tl.getSubjectId3());
        subjectList.add(tl.getSubjectId4());
        subjectList.add(tl.getSubjectId5());

        for (int i = 1; i < 6; i++) {
            ClassSubject clasSubNew = new ClassSubject(tl.getClassId(), subjectList.get(i - 1), tl.getDay(), i);
            resultSave.add(clasSubNew);
        }
        classSubjectRepository.saveAll(resultSave);
        return new MessageResponse("update time line success", 200, resultSave);
    }

    /**
     * Hàm này dùng để lấy ra thông tin thời khóa biểu theo lớp
     *
     * @param classId
     * @return MessageResponse chứa list thông tin TKB
     */
    public MessageResponse getTimeLineByFilter(int classId) {
        List<TimeLineDto> result = new ArrayList<>();
        List<ClassSubject> classSubjects = classSubjectRepository.findAllByClassId(classId);
        if (classSubjects.isEmpty()) {
            return new MessageResponse("fails", 400, null);
        }
//        for (int i = 0; i < 5; i++) {
//            TimeLineDto timeLineDto = new TimeLineDto();
//
//            List<ClassSubject> classSubjects1 = classSubjectRepository.findAllByLesson(i + 1);
//            timeLineDto.setLesson(i + 1);
//            for (ClassSubject cs : classSubjects1) {
//                if (cs.getDay() == 2) {
//                    Optional<ClassSubject> classSubject2 = classSubjectRepository.findAllByClassIdAndLessonAndDay(classId, i + 1, 2);
//                    if (classSubject2.isPresent()) {
//                        int subjectId = classSubject2.get().getSubjectId();
//                        Optional<Subject> subject = subjectRepository.findById(subjectId);
//                        subject.ifPresent(sub -> timeLineDto.setMonday(sub.getName()));
//                    }
//                }
//                if (cs.getDay() == 3) {
//                    Optional<ClassSubject> classSubject2 = classSubjectRepository.findAllByClassIdAndLessonAndDay(classId, i + 1, 3);
//                    if (classSubject2.isPresent()) {
//                        Optional<Subject> subject = subjectRepository.findById(classSubject2.get().getSubjectId());
//                        subject.ifPresent(sub -> timeLineDto.setTuesday(sub.getName()));
//                    }
//                }
//                if (cs.getDay() == 4) {
//                    Optional<ClassSubject> classSubject2 = classSubjectRepository.findAllByClassIdAndLessonAndDay(classId, i + 1, 4);
//                    if (classSubject2.isPresent()) {
//                        Optional<Subject> subject = subjectRepository.findById(classSubject2.get().getSubjectId());
//                        subject.ifPresent(sub -> timeLineDto.setWednesday(sub.getName()));
//                    }
//                }
//                if (cs.getDay() == 5) {
//                    Optional<ClassSubject> classSubject2 = classSubjectRepository.findAllByClassIdAndLessonAndDay(classId, i + 1, 5);
//                    if (classSubject2.isPresent()) {
//                        Optional<Subject> subject = subjectRepository.findById(classSubject2.get().getSubjectId());
//                        subject.ifPresent(sub -> timeLineDto.setThursday(sub.getName()));
//                    }
//                }
//                if (cs.getDay() == 6) {
//                    Optional<ClassSubject> classSubject2 = classSubjectRepository.findAllByClassIdAndLessonAndDay(classId, i + 1, 6);
//                    if (classSubject2.isPresent()) {
//                        Optional<Subject> subject = subjectRepository.findById(classSubject2.get().getSubjectId());
//                        subject.ifPresent(sub -> timeLineDto.setFriday(sub.getName()));
//                    }
//                }
//                if (cs.getDay() == 7) {
//                    Optional<ClassSubject> classSubject2 = classSubjectRepository.findAllByClassIdAndLessonAndDay(classId, i + 1, 7);
//                    if (classSubject2.isPresent()) {
//                        Optional<Subject> subject = subjectRepository.findById(classSubject2.get().getSubjectId());
//                        subject.ifPresent(sub -> timeLineDto.setSaturday(sub.getName()));
//                    }
//                }
//            }
//            result.add(timeLineDto);
//        }

        // Dùng switch-case
        for (int i = 1; i < 6; i++) {
            TimeLineDto timeLineDto = new TimeLineDto();

            int finalI = i;
            var findByLesion = classSubjects.stream().filter(item -> item.getLesson() == finalI).collect(Collectors.toList());
            timeLineDto.setLesson(i);

            findByLesion.forEach(item -> {
                Optional<Subject> subject = subjectRepository.findById(item.getSubjectId());
                switch (item.getDay()) {
                    case 2:
                        subject.ifPresent(sub -> timeLineDto.setMonday(sub.getName()));
                        break;
                    case 3:
                        subject.ifPresent(sub -> timeLineDto.setTuesday(sub.getName()));
                        break;
                    case 4:
                        subject.ifPresent(sub -> timeLineDto.setWednesday(sub.getName()));
                        break;
                    case 5:
                        subject.ifPresent(sub -> timeLineDto.setThursday(sub.getName()));
                        break;
                    case 6:
                        subject.ifPresent(sub -> timeLineDto.setFriday(sub.getName()));
                        break;
                    case 7:
                        subject.ifPresent(sub -> timeLineDto.setSaturday(sub.getName()));
                        break;
                    default:
                        break;
                }
            });
            result.add(timeLineDto);
        }
        return new MessageResponse("get time line by filter successfully", 200, result);
    }

    /**
     * Hàm này dùng để update TKB của lớp theo thứ
     *
     * @param tl
     * @return MessageResponse chứa danh sách bản ghi được update
     */
    public MessageResponse updateTimeLine(TimeLineDto2 tl) {
        List<ClassSubject> resultSave = new ArrayList<>();

        // Lấy ra các bản ghi từ bảng class_subject theo classId
        List<ClassSubject> classSubjects = classSubjectRepository.findAllByClassId(tl.getClassId());
        if (classSubjects.isEmpty()) {
            return new MessageResponse("fails", 400, null);
        }

        // Lọc các bản ghi theo thứ từ list classSubjects
        var findByDay = classSubjects.stream().filter(item -> item.getDay() == tl.getDay()).collect(Collectors.toList());
//        findByDay.forEach(item -> {
//            switch (item.getLesson()) {
//                case 1:
//                    item.setSubjectId(tl.getSubjectId1());
//                    resultSave.add(item);
//                    break;
//                case 2:
//                    item.setSubjectId(tl.getSubjectId2());
//                    resultSave.add(item);
//                    break;
//                case 3:
//                    item.setSubjectId(tl.getSubjectId3());
//                    resultSave.add(item);
//                    break;
//                case 4:
//                    item.setSubjectId(tl.getSubjectId4());
//                    resultSave.add(item);
//                    break;
//                case 5:
//                    item.setSubjectId(tl.getSubjectId5());
//                    resultSave.add(item);
//                    break;
//                default:
//                    break;
//            }
//        });

        // Đưa danh sách môn học của từng thứ vào một list mới
        // Cách 1: dùng List
//        List<Integer> subjectList = new ArrayList<>();
//        subjectList.add(tl.getSubjectId1());
//        subjectList.add(tl.getSubjectId2());
//        subjectList.add(tl.getSubjectId3());
//        subjectList.add(tl.getSubjectId4());
//        subjectList.add(tl.getSubjectId5());

        // Cách 2: dùng Map
        Map<Integer, Integer> mapSubject = new HashMap<>();
        mapSubject.put(1, tl.getSubjectId1());
        mapSubject.put(2, tl.getSubjectId2());
        mapSubject.put(3, tl.getSubjectId3());
        mapSubject.put(4, tl.getSubjectId4());
        mapSubject.put(5, tl.getSubjectId5());

        for (int i = 1; i < 6; i++) {
            int finalI = i;
            // Lọc ra các bản ghi theo ca học từ list findByDay và lấy ra phần tử đầu tiên
            Optional<ClassSubject> findByLesson = findByDay.stream().filter(item -> item.getLesson() == finalI).findFirst();

            // cách 1: dùng List
//            if (subjectList.get(i - 1) != 0) { // Nếu có môn học của ca thứ i được gửi xuống
//                if (findByLesson.isPresent()) { // Nếu môn học đã tồn tại thì update lại, ngược lại thì tạo mới
//                    findByLesson.get().setSubjectId(subjectList.get(i - 1));
//                    resultSave.add(findByLesson.get());
//                } else {
//                    ClassSubject clasSubNew = new ClassSubject(tl.getClassId(), subjectList.get(i - 1), tl.getDay(), i);
//                    classSubjectRepository.save(clasSubNew);
//                }
//            }

            // cách 2: dùng Map
            if (findByLesson.isPresent()) { // Nếu môn học đã tồn tại thì update lại, ngược lại thì tạo mới
                findByLesson.get().setSubjectId(mapSubject.get(finalI));
                resultSave.add(findByLesson.get());
            } else {
                ClassSubject clasSubNew = new ClassSubject(tl.getClassId(), mapSubject.get(finalI), tl.getDay(), i);
                classSubjectRepository.save(clasSubNew);
            }
        }
        classSubjectRepository.saveAll(resultSave);
        return new MessageResponse("update time line success", 200, resultSave);
    }

    /**
     * Lấy ra thời khóa biểu của thứ 2
     * @param clazzId
     * @return MessageResponse
     */
    public MessageResponse getTimeLineMonday(int clazzId) {
        List<ClassSubject> classSubjects = classSubjectRepository.findAllByClassIdAndDay(clazzId, 2);
        TimeLineDayDto timeLineDayDto = new TimeLineDayDto();
        classSubjects.forEach(item -> {
            Optional<Subject> subject = subjectRepository.findById(item.getSubjectId());
            switch (item.getLesson()) {
                case 1:
                    subject.ifPresent(sub -> timeLineDayDto.setLesson1(sub.getId()));
                    break;
                case 2:
                    subject.ifPresent(sub -> timeLineDayDto.setLesson2(sub.getId()));
                    break;
                case 3:
                    subject.ifPresent(sub -> timeLineDayDto.setLesson3(sub.getId()));
                    break;
                case 4:
                    subject.ifPresent(sub -> timeLineDayDto.setLesson4(sub.getId()));
                    break;
                case 5:
                    subject.ifPresent(sub -> timeLineDayDto.setLesson5(sub.getId()));
                    break;
                default:
                    break;
            }
        });
        return new MessageResponse("get time line monday successfully", 200, timeLineDayDto);
    }

}
