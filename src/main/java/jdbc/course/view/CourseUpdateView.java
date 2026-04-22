package jdbc.course.view;

import jdbc.course.controller.CourseController;
import jdbc.course.dto.CourseResponseDto;
import jdbc.course.dto.CourseUpdateRequestDto;
import java.io.BufferedReader;
import java.io.IOException;

public class CourseUpdateView {
    private final CourseController controller;
    private final BufferedReader br;

    public CourseUpdateView(CourseController controller, BufferedReader br) {
        this.controller = controller;
        this.br = br;
    }

    // throws IOException을 제거하고 내부에서 모두 처리합니다.
    public void execute() {
        System.out.println("\n[강의 정보 수정]");

        try {
            // 1. 입력 받기 (NumberFormatException 위험 구간)
            System.out.print("수정할 강의 ID: ");
            Long courseId = Long.parseLong(br.readLine());

            System.out.print("새 강의명: ");
            String name = br.readLine();

            System.out.print("새 강의 시간(초): ");
            Integer time = Integer.parseInt(br.readLine());

            System.out.print("새 가격(원): ");
            Long price = Long.parseLong(br.readLine());

            System.out.print("새 난이도(초급/중급/고급): ");
            String level = br.readLine();

            // 2. DTO 포장
            CourseUpdateRequestDto dto = new CourseUpdateRequestDto(name, time, price, level);

            // 3. Controller 호출 및 성공 결과 출력
            // Controller가 수정된 강의 정보를 담은 ResponseDto를 반환한다고 가정합니다.
            CourseResponseDto response = controller.editCourse(courseId, dto);
            System.out.println("[✅ 강의 수정 성공] " + response.toString());

        } catch (NumberFormatException e) {
            // ID, 시간, 가격에 숫자가 아닌 문자를 입력했을 때 방어!
            System.out.println("[❌ 입력 오류] ID, 시간, 가격은 반드시 숫자로 입력해 주세요.");

        } catch (IOException e) {
            // 키보드 입력 스트림에 문제가 생겼을 때 방어!
            System.out.println("[❌ 시스템 오류] 입력을 처리하는 중 문제가 발생했습니다.");

        } catch (Exception e) {
            // DB에 해당 ID의 강의가 없거나, SQL 문법이 틀렸을 때 방어!
            // (Service 계층에서 예외 전환으로 던진 에러를 여기서 잡습니다)
            System.out.println("[❌ 수정 실패] " + e.getMessage());
        }
    }
}
