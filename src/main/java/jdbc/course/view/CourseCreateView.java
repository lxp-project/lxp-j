package jdbc.course.view;

import jdbc.course.controller.CourseController;
import jdbc.course.dto.CourseResponseDto;
import jdbc.course.dto.CourseSaveRequestDto;
import java.io.BufferedReader;
import java.io.IOException;

public class CourseCreateView {
    private final CourseController controller;
    private final BufferedReader br;

    public CourseCreateView(CourseController controller, BufferedReader br) {
        this.controller = controller;
        this.br = br;
    }

    public void execute() {
        System.out.println("\n[새로운 강의 등록]");

        try {
            // 1. 입력 단계 (여기서 NumberFormatException, IOException 발생 가능)
            System.out.print("유저 ID: "); Long userId = Long.parseLong(br.readLine());
            System.out.print("카테고리 ID: "); Long categoryId = Long.parseLong(br.readLine());
            System.out.print("강의명: "); String name = br.readLine();
            System.out.print("강의 시간(초): "); Integer time = Integer.parseInt(br.readLine());
            System.out.print("가격(원): "); Long price = Long.parseLong(br.readLine());
            System.out.print("썸네일: "); String url = br.readLine();
            System.out.print("난이도: "); String level = br.readLine();

            // 2. DTO 매핑
            CourseSaveRequestDto dto = new CourseSaveRequestDto(userId, categoryId, name, time, price, url, level);

            // 3. Controller 호출 및 성공 응답(ResponseDto) 처리
            // Service쪽에서 문제가 생기면 여기서 에러가 던져집니다.
            CourseResponseDto response = controller.createCourse(dto);
            System.out.println("[✅ 강의 등록 성공] " + response.toString());

        } catch (NumberFormatException e) {
            // 시나리오 A: 타입 변환 실패 (오타)
            System.out.println("[❌ 입력 오류] 숫자만 입력해야 하는 항목에 문자가 포함되었습니다. 다시 시도해 주세요.");

        } catch (IOException e) {
            // 키보드 입력 자체가 끊겼을 때
            System.out.println("[❌ 시스템 오류] 입력을 읽는 중 문제가 발생했습니다.");

        } catch (Exception e) {
            // 시나리오 B: 비즈니스 에러 또는 DB 에러 (Service에서 던진 에러)
            // ex) "강의 등록 실패", "중복된 이름입니다" 등
            System.out.println("[❌ 처리 실패] " + e.getMessage());
        }
    }
}
