package jdbc.course.view;

import jdbc.course.controller.CourseController;
import jdbc.course.exception.UserCancelException;

import java.io.BufferedReader;
import java.io.IOException;

import static jdbc.course.util.ConsoleInputUtil.getValidLong;

public class CourseDeleteView {
    private final CourseController controller;
    private final BufferedReader br;

    public CourseDeleteView(CourseController controller, BufferedReader br) {
        this.controller = controller;
        this.br = br;
    }

    public void execute() {
        System.out.println("\n=================================================");
        System.out.println("                 [ 🗑️ 강의 정보 삭제 ]                 ");
        System.out.println("  * 입력을 취소하고 메뉴로 돌아가려면 'q'를 입력하세요.");
        System.out.println("-------------------------------------------------");
        System.out.println("  [💡 입력 범위 안내]");
        System.out.println("   - ID 및 가격 (Long)   : 최대 약 922경(거의 무한)");
        System.out.println("=================================================\n");

        try {
            Long courseId = getValidLong(br,"🔹 삭제할 강의 ID: ");

            // Controller 호출
            controller.removeCourse(courseId);
            System.out.println("\n[✅ 강의 삭제 완료] 삭제된 강의 ID : " + courseId);

        } catch (UserCancelException e) {
            System.out.println("\n[🛑 중단] " + e.getMessage());

        } catch (Exception e) {
            System.out.println("\n[❌ 삭제 실패] " + e.getMessage());
        }
    }

}
