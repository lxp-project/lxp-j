package jdbc.course.dto;

import java.sql.Timestamp;

public class CourseResponseDto {
    /// (서버 ➔ 클라이언트: 조회 후 내보내는 데이터)

    private Long courseId;
    private String courseName;
    private Integer courseTime;
    private Long price;
    private String difficultLevel;
    private Timestamp createdAt;

    public CourseResponseDto(Long courseId, String courseName, Integer courseTime, Long price, String difficultLevel, Timestamp createdAt) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseTime = courseTime;
        this.price = price;
        this.difficultLevel = difficultLevel;
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Course [ID=" + courseId + ", 이름=" + courseName + ", 시간=" + courseTime + "초, 가격=" + price + "원, 난이도=" + difficultLevel + ", 등록일=" + createdAt + "]";
    }

    public Long getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public Integer getCourseTime() {
        return courseTime;
    }

    public Long getPrice() {
        return price;
    }

    public String getDifficultLevel() {
        return difficultLevel;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }
}
