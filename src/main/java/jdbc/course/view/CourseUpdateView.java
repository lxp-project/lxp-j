package jdbc.course.view;

import jdbc.course.controller.CourseController;
import jdbc.course.dto.CourseResponseDto;
import jdbc.course.dto.CourseUpdateRequestDto;
import jdbc.course.exception.UserCancelException;
import jdbc.course.util.ConsoleInputUtil;

import java.io.BufferedReader;

import static jdbc.course.util.ConsoleInputUtil.*;

public class CourseUpdateView {
    private final CourseController controller;
    private final BufferedReader br;

    public CourseUpdateView(CourseController controller, BufferedReader br) {
        this.controller = controller;
        this.br = br;
    }

    public void execute() {
        while (true) {
            System.out.println("\n=================================================");
            System.out.println("                 [ 🔄 강의 정보 수정 ]                 ");
            System.out.println("  * 입력을 취소하고 메뉴로 돌아가려면 'q'를 입력하세요.");
            System.out.println("-------------------------------------------------");
            System.out.println("  [💡 팁] 항목 수정 시 'n' 또는 엔터를 누르면 기존 값이 유지됩니다.");
            System.out.println("=================================================\n");

            try {
                // 1. 유효한 ID 먼저 받기 (ConsoleInputUtil의 기본 메서드 사용)
                Long courseId = ConsoleInputUtil.getValidLong(br, "🔹 수정할 강의 ID: ");

                // 2. 기존 데이터 조회 (없으면 Service에서 예외 발생)
                CourseResponseDto existingCourse = controller.findCourse(courseId);

                System.out.println("\n  [현재 강의 정보]");
                System.out.println("  - 강의명: " + existingCourse.getCourseName());
                System.out.println("  - 시간: " + existingCourse.getCourseTime() + "초");
                System.out.println("  - 가격: " + existingCourse.getPrice() + "원");
                System.out.println("  - 난이도: " + existingCourse.getDifficultLevel());
                System.out.println("-------------------------------------------------\n");

                // 3. 💡 ConsoleInputUtil의 수정(Update) 전용 메서드 호출
                String name = getUpdateString(br, "🔹 새 강의명", existingCourse.getCourseName());
                Integer time = getUpdateInteger(br, "🔹 새 강의 시간(초)", existingCourse.getCourseTime());
                Long price = getUpdateLong(br, "🔹 새 가격(원)", existingCourse.getPrice());
                String level = getUpdateDifficulty(br, "🔹 새 난이도", existingCourse.getDifficultLevel());

                // 4. DTO 포장 및 컨트롤러 호출
                CourseUpdateRequestDto dto = new CourseUpdateRequestDto(name, time, price, level);
                CourseResponseDto response = controller.editCourse(courseId, dto);

                System.out.println("\n[✅ 강의 수정 성공] 정상적으로 변경되었습니다.");
                System.out.println(" -> 최종 강의명: " + response.getCourseName());

                break; // 수정 성공 시 루프 탈출

            } catch (UserCancelException e) {
                System.out.println("\n[🛑 중단] " + e.getMessage());
                return;

            } catch (Exception e) {
                System.out.println("\n[❌ 수정 실패] " + e.getMessage());
                System.out.println("다시 시도해 주세요.");
            }
        }
    }
}
