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

    public void execute() throws IOException {
        System.out.print("\n삭제할 강의 ID를 입력하세요: ");
        Long courseId = Long.parseLong(br.readLine());
        controller.removeCourse(courseId);
        controller.findAllCourses(); // 삭제 후 목록 다시 보여주기
    }
}
