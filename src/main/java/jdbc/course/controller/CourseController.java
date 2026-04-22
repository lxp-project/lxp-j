package jdbc.course.controller;

import jdbc.course.dto.CourseResponseDto;
import jdbc.course.dto.CourseSaveRequestDto;
import jdbc.course.dto.CourseUpdateRequestDto;
import jdbc.course.service.CourseService;

import java.util.List;

public class CourseController {

    private final CourseService courseService;

    // 의존성 주입 (Service를 받아옴)
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    // 사용자의 입력을 받아 DTO로 묶어서 Service로 넘김(등록)
    public void createCourse(Long userId, Long categoryId, String courseName, Integer courseTime, Long price, String difficultLevel) {
        try {
            CourseSaveRequestDto request = new CourseSaveRequestDto(userId, categoryId, courseName, courseTime, price, null, difficultLevel);
            CourseResponseDto response = courseService.registerCourse(request);

            System.out.println("[✅ 강의 등록 성공] " + response.toString());
        } catch (Exception e) {
            System.out.println("[❌ 강의 등록 실패] " + e.getMessage());
        }
    }
    // 조회
    public void findCourse(Long courseId) {
        try {
            CourseResponseDto response = courseService.getCourse(courseId);
            System.out.println("[🔍 강의 조회 결과] " + response.toString());
        } catch (Exception e) {
            System.out.println("[❌ 강의 조회 실패] " + e.getMessage());
        }
    }
    // 조회(전체)
    public void findAllCourses() {
        try {
            List<CourseResponseDto> responses = courseService.getAllCourses();
            System.out.println("\n=== 📚 전체 강의 목록 (" + responses.size() + "개) ===");
            for (CourseResponseDto response : responses) {
                System.out.println(response.toString());
            }
            System.out.println("====================================\n");
        } catch (Exception e) {
            System.out.println("[❌ 목록 조회 실패] " + e.getMessage());
        }
    }
    //수정
    public void editCourse(Long courseId, String courseName, Integer courseTime, Long price, String difficultLevel) {
        try {
            CourseUpdateRequestDto request = new CourseUpdateRequestDto(courseName, courseTime, price, difficultLevel);
            CourseResponseDto response = courseService.updateCourse(courseId, request);
            System.out.println("[✅ 강의 수정 성공] " + response.toString());
        } catch (Exception e) {
            System.out.println("[❌ 강의 수정 실패] " + e.getMessage());
        }
    }

    // 제거
    public void removeCourse(Long courseId) {
        try {
            courseService.deleteCourse(courseId);
            System.out.println("[🗑️ 강의 삭제 완료] 강의 ID : " + courseId);
        } catch (Exception e) {
            System.out.println("[❌ 강의 삭제 실패] " + e.getMessage());
        }
    }
}