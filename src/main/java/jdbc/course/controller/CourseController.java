package jdbc.course.controller;

import jdbc.course.dto.CourseResponseDto;
import jdbc.course.dto.CourseSaveRequestDto;
import jdbc.course.dto.CourseUpdateRequestDto;
import jdbc.course.service.CourseService;

import java.sql.SQLException;
import java.util.List;

public class CourseController {

    private final CourseService courseService;

    // 의존성 주입 (Service를 받아옴)
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    // 1. 등록 (Create)
    // View가 포장해준 DTO를 그대로 Service로 넘기고, 결과를 반환합니다.
    public CourseResponseDto createCourse(CourseSaveRequestDto requestDto) throws SQLException {
        return courseService.registerCourse(requestDto);
    }

    // 2. 단일 조회 (Read)
    public CourseResponseDto findCourse(Long courseId) throws SQLException {
        return courseService.getCourse(courseId);
    }

    // 3. 전체 조회 (Read All)
    public List<CourseResponseDto> findAllCourses() throws SQLException {
        return courseService.getAllCourses();
    }

    // 4. 수정 (Update)
    // 어떤 강의(ID)를, 어떤 내용(DTO)으로 바꿀지 전달합니다.
    public CourseResponseDto editCourse(Long courseId, CourseUpdateRequestDto requestDto) throws SQLException {
        return courseService.updateCourse(courseId, requestDto);
    }

    // 5. 삭제 (Delete)
    // 삭제는 보통 반환값이 없으므로 void를 유지합니다.
    public void removeCourse(Long courseId) throws SQLException {
        courseService.deleteCourse(courseId);
    }
}
