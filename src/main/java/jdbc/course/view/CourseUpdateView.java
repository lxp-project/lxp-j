package jdbc.course.view;

import jdbc.course.controller.CourseController;
import jdbc.course.difficulty.Difficulty;
import jdbc.course.dto.CourseResponseDto;
import jdbc.course.dto.CourseUpdateRequestDto;
import jdbc.course.exception.UserCancelException;
import jdbc.course.util.ConsoleInputUtil;

import java.io.BufferedReader;
import java.io.IOException;

import static jdbc.course.util.ConsoleInputUtil.*;

public class CourseUpdateView {
    private final CourseController controller;
    private final BufferedReader br;

    public CourseUpdateView(CourseController controller, BufferedReader br) {
        this.controller = controller;
        this.br = br;
    }

    public void execute() {
        System.out.println("\n=================================================");
        System.out.println("                 [ 🔄 강의 정보 수정 ]                 ");
        System.out.println("  * 입력을 취소하고 메뉴로 돌아가려면 'q'를 입력하세요.");
        System.out.println("-------------------------------------------------");
        System.out.println("  [💡 입력 범위 안내]");
        System.out.println("   - 강의 시간 (Integer) : 최대 약 21억");
        System.out.println("   - ID 및 가격 (Long)   : 최대 약 922경(거의 무한)");
        System.out.println("=================================================\n");

        try {
            // 1. 헬퍼 메서드로 안전하게 입력받음 ('q' 누르면 예외 터짐)
            Long courseId = getValidLong(br,"🔹 수정할 강의 ID: ");
            String name = getValidString(br,"🔹 새 강의명: ");
            Integer time = getValidInteger(br,"🔹 새 강의 시간(초): ");
            Long price = getValidLong(br,"🔹 새 가격(원): ");
            // 💡 텍스트 입력 대신 Enum 선택 메서드 호출
            Difficulty difficulty = ConsoleInputUtil.getValidDifficulty(br);
            String level = difficulty.getDescription(); // DB에는 "초보" 등의 문자열로 저장

            // 2. DTO 포장
            CourseUpdateRequestDto dto = new CourseUpdateRequestDto(name, time, price, level);

            // 3. Controller 호출
            CourseResponseDto response = controller.editCourse(courseId, dto);
            System.out.println("\n[✅ 강의 수정 성공] 정상적으로 변경되었습니다.");
            System.out.println(" -> 수정된 강의명: " + response.toString());

        } catch (UserCancelException e) {
            // 사용자가 'q'를 눌러 던진 예외를 여기서 안전하게 받아냄
            System.out.println("\n[🛑 중단] " + e.getMessage());

        } catch (Exception e) {
            // DB에 없는 ID거나 비즈니스 로직 에러
            System.out.println("\n[❌ 수정 실패] " + e.getMessage());
        }
    }
}
