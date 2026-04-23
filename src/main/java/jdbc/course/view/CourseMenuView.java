package jdbc.course.view;

import java.io.BufferedReader;

public class CourseMenuView {
    private final BufferedReader br;

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
        System.out.println("\n=================================================");
        System.out.println("       🚀 LXP 강의 관리 시스템에 오신 것을 환영합니다.       ");
        System.out.println("=================================================");

        try {
            while (true) {
                System.out.println("\n-------------------------------------------------");
                System.out.println("  1. 강의 등록   |  2. 단일 조회  |  3. 전체 조회  ");
                System.out.println("  4. 강의 수정   |  5. 강의 삭제  |  0. 시스템 종료  ");
                System.out.println("-------------------------------------------------");
                System.out.print("👉 원하시는 메뉴 번호를 입력하세요: ");

                String temp = br.readLine();

                switch (temp) {
                    case "1": createView.execute(); break;
                    case "2": findOneView.execute(); break;
                    case "3": findAllView.execute(); break;
                    case "4": updateView.execute(); break;
                    case "5": deleteView.execute(); break;
                    case "0":
                        System.out.println("\n[시스템] 프로그램을 안전하게 종료합니다. 이용해 주셔서 감사합니다.");
                        return; // while문 탈출
                    default:
                        System.out.println("\n[❌ 오류] 잘못된 번호입니다. 0~5 사이의 숫자를 입력해주세요.");
                }
            }
        } catch (Exception e) {
            System.out.println("\n[❌ 치명적 시스템 오류] " + e.getMessage());
        }
    }
}
