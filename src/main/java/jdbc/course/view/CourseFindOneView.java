package jdbc.course.view;

import jdbc.course.controller.CourseController;
import java.io.BufferedReader;
import java.io.IOException;

public class CourseFindOneView {
    private final CourseController controller;
    private final BufferedReader br;

    public CourseFindOneView(CourseController controller, BufferedReader br) {
        this.controller = controller;
        this.br = br;
    }

    public void execute() throws IOException {
        System.out.print("\n조회할 강의 ID를 입력하세요: ");
        Long courseId = Long.parseLong(br.readLine());
        controller.findCourse(courseId);
    }
}
