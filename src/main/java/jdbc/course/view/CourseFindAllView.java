package jdbc.course.view;

import jdbc.course.controller.CourseController;
import jdbc.course.dto.CourseResponseDto;
import java.util.List;

public class CourseFindAllView {
    private final CourseController controller;

    public CourseFindAllView(CourseController controller) {
        this.controller = controller;
    }

    public void execute() {
        System.out.println("\n[전체 강의 목록 조회]");
        try {
            List<CourseResponseDto> responses = controller.findAllCourses();

            if (responses.isEmpty()) {
                System.out.println("등록된 강의가 없습니다.");
                return;
            }

            System.out.println("=== 📚 전체 강의 목록 (" + responses.size() + "개) ===");
            for (CourseResponseDto response : responses) {
                System.out.println(response.toString());
            }
            System.out.println("====================================");

        } catch (Exception e) {
            System.out.println("[❌ 목록 조회 실패] 서버 통신 중 문제가 발생했습니다: " + e.getMessage());
        }
    }
}
