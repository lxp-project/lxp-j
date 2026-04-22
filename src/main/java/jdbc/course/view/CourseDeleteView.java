package jdbc.course.view;

import jdbc.course.controller.CourseController;
import java.io.BufferedReader;
import java.io.IOException;

public class CourseDeleteView {
    private final CourseController controller;
    private final BufferedReader br;

    public CourseDeleteView(CourseController controller, BufferedReader br) {
        this.controller = controller;
        this.br = br;
    }

    public void execute() {
        try {
            System.out.print("\n삭제할 강의 ID를 입력하세요: ");
            Long courseId = Long.parseLong(br.readLine());

            // Controller 호출 (삭제는 보통 반환값이 없으므로 출력만 수행)
            controller.removeCourse(courseId);
            System.out.println("[🗑️ 강의 삭제 완료] 삭제된 강의 ID : " + courseId);

        } catch (NumberFormatException e) {
            System.out.println("[❌ 입력 오류] 삭제할 강의 ID는 숫자로 입력해 주세요.");
        } catch (IOException e) {
            System.out.println("[❌ 시스템 오류] 입력을 처리하는 중 문제가 발생했습니다.");
        } catch (Exception e) {
            // 없는 강의 삭제를 시도하거나 외래키 문제 등이 생겼을 때 방어
            System.out.println("[❌ 삭제 실패] " + e.getMessage());
        }
    }
}
