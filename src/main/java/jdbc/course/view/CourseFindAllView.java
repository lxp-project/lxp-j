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
        System.out.println("\n=================================================");
        System.out.println("                 [ 📚 전체 강의 목록 ]                 ");
        System.out.println("=================================================\n");

        try {
            List<CourseResponseDto> responses = controller.findAllCourses();

            if (responses.isEmpty()) {
                System.out.println("  [알림] 등록된 강의가 아직 없습니다.");
                return;
            }

            System.out.println("  총 " + responses.size() + "개의 강의가 검색되었습니다.\n");

            // 목록 출력 시 들여쓰기를 추가하여 가독성 향상
            for (CourseResponseDto response : responses) {
                System.out.println("  " + response.toString());
            }

            System.out.println("\n=================================================");

        } catch (Exception e) {
            System.out.println("\n[❌ 목록 조회 실패] 서버 통신 중 문제가 발생했습니다: " + e.getMessage());
        }
    }
}
