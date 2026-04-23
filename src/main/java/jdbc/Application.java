package jdbc;

import com.zaxxer.hikari.HikariDataSource;
import jdbc.connection.DBConnectionManager;
import jdbc.course.controller.CourseController;
import jdbc.course.repository.CourseRepository;
import jdbc.course.repository.JdbcCourseRepository;
import jdbc.course.service.CourseService;
import jdbc.course.view.*;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Application {

    public static void main(String[] args) {
        // DBConnectionManager에게 커넥션 풀(DataSource)을 받아옴
        DataSource dataSource = DBConnectionManager.getDataSource();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            // 1. 코어(백엔드) 객체 조립
            CourseRepository courseRepository = new JdbcCourseRepository(dataSource);
            CourseService courseService = new CourseService(courseRepository);
            CourseController courseController = new CourseController(courseService);

            // 2. 뷰(프론트엔드) 객체 조립 - 5개의 뷰 생성
            CourseCreateView createView = new CourseCreateView(courseController, br);
            CourseFindOneView findOneView = new CourseFindOneView(courseController, br);
            CourseFindAllView findAllView = new CourseFindAllView(courseController);
            CourseUpdateView updateView = new CourseUpdateView(courseController, br);
            CourseDeleteView deleteView = new CourseDeleteView(courseController, br);

            // 3. 메뉴판(라우터) 조립
            CourseMenuView menuView = new CourseMenuView(br, createView, findOneView, findAllView, updateView, deleteView);

            // 4. 실행!
            menuView.start();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 메인에서 직접 dataSource를 닫지 않고, 매니저에게 종료 책임을 위임
            DBConnectionManager.close();
        }
    }
}
