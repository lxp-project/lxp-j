package jdbc.course.view;

import jdbc.course.controller.CourseController;
import java.io.BufferedReader;
import java.io.IOException;

public class CourseCreateView {
    private final CourseController controller;
    private final BufferedReader br;

    public CourseCreateView(CourseController controller, BufferedReader br) {
        this.controller = controller;
        this.br = br;
    }

    public void execute() throws IOException {
        System.out.println("\n[새로운 강의 등록]");
        System.out.print("1. 유저 ID: ");
        Long userId = Long.parseLong(br.readLine());
        System.out.print("2. 카테고리 ID: ");
        Long categoryId = Long.parseLong(br.readLine());
        System.out.print("3. 강의명: ");
        String courseName = br.readLine();
        System.out.print("4. 강의 시간(초): ");
        Integer courseTime = Integer.parseInt(br.readLine());
        System.out.print("5. 가격(원): ");
        Long price = Long.parseLong(br.readLine());
        System.out.print("6. 난이도(초급/중급/고급): ");
        String difficultLevel = br.readLine();

        controller.createCourse(userId, categoryId, courseName, courseTime, price, difficultLevel);
    }
}
