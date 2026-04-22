package jdbc.course.view;

import jdbc.course.controller.CourseController;
import jdbc.course.dto.CourseResponseDto;
import java.io.BufferedReader;
import java.io.IOException;

public class CourseFindOneView {
    private final CourseController controller;
    private final BufferedReader br;

    public CourseFindOneView(CourseController controller, BufferedReader br) {
        this.controller = controller;
        this.br = br;
    }

    public void execute() {
        try {
            System.out.print("\n조회할 강의 ID를 입력하세요: ");
            Long courseId = Long.parseLong(br.readLine());

            // Controller 호출 및 반환된 DTO 출력
            CourseResponseDto response = controller.findCourse(courseId);
            System.out.println("[🔍 강의 조회 결과] " + response.toString());

        } catch (NumberFormatException e) {
            System.out.println("[❌ 입력 오류] 강의 ID는 반드시 숫자로 입력해 주세요.");
        } catch (IOException e) {
            System.out.println("[❌ 시스템 오류] 입력을 처리하는 중 문제가 발생했습니다.");
        } catch (Exception e) {
            // 없는 강의를 조회했을 때 Service에서 던지는 예외 메시지 출력
            System.out.println("[❌ 조회 실패] " + e.getMessage());
        }
    }
}
