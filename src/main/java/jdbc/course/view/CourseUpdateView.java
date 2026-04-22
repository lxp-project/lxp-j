package jdbc.course.view;

import jdbc.course.controller.CourseController;
import java.io.BufferedReader;
import java.io.IOException;

public class CourseUpdateView {
    private final CourseController controller;
    private final BufferedReader br;

    public CourseUpdateView(CourseController controller, BufferedReader br) {
        this.controller = controller;
        this.br = br;
    }

    public void execute() throws IOException {
        System.out.println("\n[강의 정보 수정]");
        System.out.print("수정할 강의 ID: ");
        Long courseId = Long.parseLong(br.readLine());
        System.out.print("새 강의명: ");
        String courseName = br.readLine();
        System.out.print("새 강의 시간(초): ");
        Integer courseTime = Integer.parseInt(br.readLine());
        System.out.print("새 가격(원): ");
        Long price = Long.parseLong(br.readLine());
        System.out.print("새 난이도: ");
        String difficultLevel = br.readLine();

        controller.editCourse(courseId, courseName, courseTime, price, difficultLevel);
    }
}
