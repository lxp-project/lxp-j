package jdbc.course.view;

import jdbc.course.controller.CourseController;
import jdbc.course.difficulty.Difficulty;
import jdbc.course.dto.CourseResponseDto;
import jdbc.course.dto.CourseSaveRequestDto;
import jdbc.course.util.ConsoleInputUtil;
import jdbc.course.exception.UserCancelException;
import java.io.BufferedReader;

public class CourseCreateView {
    private final CourseController controller;
    private final BufferedReader br;

    public CourseCreateView(CourseController controller, BufferedReader br) {
        this.controller = controller;
        this.br = br;
    }

    public void execute() {
        System.out.println("\n=================================================");
        System.out.println("                 [ 📝 새로운 강의 등록 ]                 ");
        System.out.println("  * 입력을 취소하고 메뉴로 돌아가려면 'q'를 입력하세요.");
        System.out.println("-------------------------------------------------");
        System.out.println("  [💡 입력 범위 안내]");
        System.out.println("   - 강의 시간 (Integer) : 최대 약 21억");
        System.out.println("   - ID 및 가격 (Long)   : 최대 약 922경(거의 무한)");
        System.out.println("=================================================\n");

        try {
            // 💡 ConsoleInputUtil을 사용해서 단 한 줄로 안전한 입력을 처리! (br 객체를 같이 넘겨줌)
            Long userId = ConsoleInputUtil.getValidLong(br, "🔹 유저 ID: ");
            Long categoryId = ConsoleInputUtil.getValidLong(br, "🔹 카테고리 ID: ");
            String name = ConsoleInputUtil.getValidString(br, "🔹 강의명: ");
            Integer time = ConsoleInputUtil.getValidInteger(br, "🔹 강의 시간(초): ");
            Long price = ConsoleInputUtil.getValidLong(br, "🔹 가격(원): ");
            String url = ConsoleInputUtil.getValidString(br, "🔹 썸네일 URL: ");
            // 💡 텍스트 입력 대신 Enum 선택 메서드 호출
            Difficulty difficulty = ConsoleInputUtil.getValidDifficulty(br);
            String level = difficulty.getDescription(); // DB에는 "초보" 등의 문자열로 저장

            CourseSaveRequestDto dto = new CourseSaveRequestDto(userId, categoryId, name, time, price, url, level);

            CourseResponseDto response = controller.createCourse(dto);
            System.out.println("\n[✅ 강의 등록 성공] 정상적으로 처리되었습니다.");
            System.out.println(" -> 등록된 강의명: " + response.toString());

        } catch (UserCancelException e) {
            System.out.println("\n[🛑 중단] " + e.getMessage());

        } catch (Exception e) {
            System.out.println("\n[❌ 처리 실패] " + e.getMessage());
        }
    }
}
