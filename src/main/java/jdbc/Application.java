package jdbc;

import com.zaxxer.hikari.HikariDataSource;
import jdbc.course.controller.CourseController;
import jdbc.course.repository.CourseRepository;
import jdbc.course.service.CourseService;
import jdbc.user.controller.UsersController;
import jdbc.user.repository.UsersRepository;
import jdbc.user.service.UsersService;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Application {

    public static void main(String[] args) {
        // 1. 공통 DB 커넥션 풀 생성
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/jdbcconsolepg");
        dataSource.setUsername("min");
        dataSource.setPassword("min"); // 비밀번호 설정 확인!

        try {
            // ==========================================
            // 2. 객체 조립 (Dependency Injection)
            // ==========================================

            // [User 도메인 조립]
//            UsersRepository usersRepository = new UsersRepository(dataSource);
//            UsersService usersService = new UsersService(usersRepository);
//            UsersController usersController = new UsersController(usersService);

            // [Course 도메인 조립] - user와 같은 dataSource를 공유합니다!
            CourseRepository courseRepository = new CourseRepository(dataSource);
            CourseService courseService = new CourseService(courseRepository);
            CourseController courseController = new CourseController(courseService);

            // ==========================================
            // 3. 기능 실행 (테스트)
            // ==========================================

            System.out.println("🚀 [강의 시스템 테스트 시작]");

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            while (true){
                System.out.println("(등록 1, 단일 조회 2, 전체 조회3 , 수정 4, 삭제 5, 종료 6) 원하는 키워드의 숫자를 정확히 입력하세요");

                String temp = br.readLine();
                if(temp.equals("1")){
                    // 등록 (주의: DB에 user_id=1, category_id=1 데이터가 미리 있어야 합니다!)
                    courseController.createCourse(1L, 1L, "자바스크립트 기초", 3600, 50000L, "초급");

                }else if(temp.equals("2")){
                    // 단일 조회
                    courseController.findCourse(2L);
                }else if(temp.equals("3")){
                    // 전체 조회
                    courseController.findAllCourses();
                }else if(temp.equals("4")){
                    //수정
                    courseController.editCourse(2L, "자바 마스터이", 5000, 55000L, "중급");
                }else if(temp.equals("5")){
                    // 삭제
                    courseController.removeCourse(2L);
                    // 삭제 후 다시 전체 조회 확인
                    courseController.findAllCourses();
                }else if(temp.equals("0")){
                    break;
                }else System.out.println("다시 ㄱ");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 4. 안전하게 자원 반납
            if (dataSource != null) {
                dataSource.close();
                System.out.println("=== DB 커넥션 풀 종료 완료 ===");
            }
        }
    }
}