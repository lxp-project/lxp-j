package jdbc.course.repository;

import jdbc.course.domain.Course;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseRepository {

    private final DataSource dataSource;

    public CourseRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // 1. 등록 (Insert)
    public Course save(Course course) throws SQLException {
        String sql = "INSERT INTO Courses (user_id, category_id, course_name, course_time, price, thumbnail_url, difficult_level) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setLong(1, course.getUserId());
            pstmt.setLong(2, course.getCategoryId());
            pstmt.setString(3, course.getCourseName());
            pstmt.setInt(4, course.getCourseTime());

            // 가격이 null일 수 있으므로 처리
            if (course.getPrice() != null) {
                pstmt.setLong(5, course.getPrice());
            } else {
                pstmt.setNull(5, Types.BIGINT);
            }
            pstmt.setString(6, course.getThumbnailUrl());
            pstmt.setString(7, course.getDifficultLevel());

            pstmt.executeUpdate(); // DB에 쿼리 전송

            // DB에서 자동 생성된 PK(course_id)와 등록일시(created_at) 가져오기
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    Long generatedId = rs.getLong("course_id");
                    Timestamp createdAt = rs.getTimestamp("created_at");
                    // DB가 만들어준 값을 포함하여 완벽한 Entity로 조립 후 반환
                    return new Course(generatedId, course.getUserId(), course.getCategoryId(),
                            course.getCourseName(), course.getCourseTime(), course.getPrice(),
                            course.getThumbnailUrl(), createdAt, null, course.getDifficultLevel());
                }
            }
        }
        throw new SQLException("강의 등록 실패");
    }

    // 2. 단건 조회 (Select One)
    public Course findById(Long courseId) throws SQLException {
        String sql = "SELECT * FROM Courses WHERE course_id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, courseId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapRowToCourse(rs);
                }
            }
        }
        return null;
    }

    // 3. 전체 조회 (Select All)
    public List<Course> findAll() throws SQLException {
        String sql = "SELECT * FROM Courses ORDER BY course_id DESC";
        List<Course> courses = new ArrayList<>();

        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                courses.add(mapRowToCourse(rs));
            }
        }
        return courses;
    }
    //수장
    public void update(Long courseId, Course course) throws SQLException {
        String sql = "UPDATE Courses SET course_name = ?, course_time = ?, price = ?, difficult_level = ? WHERE course_id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, course.getCourseName());
            pstmt.setInt(2, course.getCourseTime());
            if (course.getPrice() != null) pstmt.setLong(3, course.getPrice());
            else pstmt.setNull(3, Types.BIGINT);
            pstmt.setString(4, course.getDifficultLevel());
            pstmt.setLong(5, courseId);

            pstmt.executeUpdate();
        }
    }

    // 4. 삭제 (Delete)
    public void deleteById(Long courseId) throws SQLException {
        String sql = "DELETE FROM Courses WHERE course_id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, courseId);
            pstmt.executeUpdate();
        }
    }

    // 중복되는 ResultSet -> Entity 매핑 로직을 하나의 메서드로 분리 (유지보수 용이)
    private Course mapRowToCourse(ResultSet rs) throws SQLException {
        return new Course(
                rs.getLong("course_id"),
                rs.getLong("user_id"),
                rs.getLong("category_id"),
                rs.getString("course_name"),
                rs.getInt("course_time"),
                rs.getLong("price") == 0 && rs.wasNull() ? null : rs.getLong("price"),
                rs.getString("thumbnail_url"),
                rs.getTimestamp("created_at"),
                rs.getTimestamp("modified_at"),
                rs.getString("difficult_level")
        );
    }
}