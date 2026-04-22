package jdbc.course.view;

import java.io.BufferedReader;

public class CourseMenuView {
    private final BufferedReader br;

    // 5개의 전담 View 객체들을 가지고 있습니다.
    private final CourseCreateView createView;
    private final CourseFindOneView findOneView;
    private final CourseFindAllView findAllView;
    private final CourseUpdateView updateView;
    private final CourseDeleteView deleteView;

    public CourseMenuView(BufferedReader br, CourseCreateView createView, CourseFindOneView findOneView,
                          CourseFindAllView findAllView, CourseUpdateView updateView, CourseDeleteView deleteView) {
        this.br = br;
        this.createView = createView;
        this.findOneView = findOneView;
        this.findAllView = findAllView;
        this.updateView = updateView;
        this.deleteView = deleteView;
    }

    public void start() {
        System.out.println("🚀 [강의 시스템 테스트 시작]");

        try {
            while (true) {
                System.out.println("\n(등록 1, 단일 조회 2, 전체 조회 3, 수정 4, 삭제 5, 종료 0)");
                System.out.print("원하는 키워드의 숫자를 정확히 입력하세요: ");

                String temp = br.readLine();

                switch (temp) {
                    case "1": createView.execute(); break;      // 등록 뷰 실행
                    case "2": findOneView.execute(); break;     // 조회 뷰 실행
                    case "3": findAllView.execute(); break;     // 전체조회 뷰 실행
                    case "4": updateView.execute(); break;      // 수정 뷰 실행
                    case "5": deleteView.execute(); break;      // 삭제 뷰 실행
                    case "0":
                        System.out.println("시스템을 종료합니다.");
                        return; // while문 탈출
                    default:
                        System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
                }
            }
        } catch (Exception e) {
            System.out.println("오류 발생: " + e.getMessage());
        }
    }
}
