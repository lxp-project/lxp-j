package jdbc.course.view;

import jdbc.course.controller.CourseController;
import jdbc.course.dto.CourseResponseDto;
import jdbc.course.util.ConsoleInputUtil;
import jdbc.course.exception.UserCancelException;
import java.io.BufferedReader;

public class CourseFindOneView {
    private final CourseController controller;
    private final BufferedReader br;

    public CourseFindOneView(CourseController controller, BufferedReader br) {
        this.controller = controller;
        this.br = br;
    }

    public void execute() {
        System.out.println("\n=================================================");
        System.out.println("                 [ 🔍 단일 강의 조회 ]                 ");
        System.out.println("  * 입력을 취소하고 메뉴로 돌아가려면 'q'를 입력하세요.");
        System.out.println("-------------------------------------------------");
        System.out.println("  [💡 입력 범위 안내]");
        System.out.println("   - ID 및 가격 (Long)   : 최대 약 922경(거의 무한)");
        System.out.println("=================================================\n");

        try {
            // 💡 ConsoleInputUtil을 사용하여 안전하게 입력 및 취소 기능 적용
            Long courseId = ConsoleInputUtil.getValidLong(br, "🔹 조회할 강의 ID: ");

            // Controller 호출 및 반환된 DTO 출력
            CourseResponseDto response = controller.findCourse(courseId);
            System.out.println("\n[✅ 강의 조회 성공]");
            System.out.println("  " + response.toString());

        } catch (UserCancelException e) {
            // 'q' 입력 시 즉시 메뉴로 복귀
            System.out.println("\n[🛑 중단] " + e.getMessage());

        } catch (Exception e) {
            // 없는 강의를 조회했을 때 Service에서 던지는 예외
            System.out.println("\n[❌ 조회 실패] " + e.getMessage());
        }
    }
}
