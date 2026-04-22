package jdbc.course.view;

import jdbc.course.controller.CourseController;

public class CourseFindAllView {
    private final CourseController controller;

    public CourseFindAllView(CourseController controller) {
        this.controller = controller;
    }

    // 입력받을 게 없으므로 BufferedReader가 필요 없습니다.
    public void execute() {
        System.out.println("\n[전체 강의 목록 조회]");
        controller.findAllCourses();
    }
}
