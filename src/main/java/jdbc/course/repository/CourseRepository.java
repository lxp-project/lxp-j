package jdbc.course.repository;

import jdbc.course.domain.Course;
import java.sql.SQLException;
import java.util.List;

public interface CourseRepository {

    // 1. 등록
    Course save(Course course) throws SQLException;

    // 2. 단건 조회
    Course findById(Long courseId) throws SQLException;

    // 3. 전체 조회
    List<Course> findAll() throws SQLException;

    // 4. 수정
    void update(Long courseId, Course course) throws SQLException;

    // 5. 삭제
    void deleteById(Long courseId) throws SQLException;
}
