package jdbc.course.dto;

public class CourseUpdateRequestDto {
    private String courseName;
    private Integer courseTime;
    private Long price;
    private String difficultLevel;

    public CourseUpdateRequestDto(String courseName, Integer courseTime, Long price, String difficultLevel) {
        this.courseName = courseName;
        this.courseTime = courseTime;
        this.price = price;
        this.difficultLevel = difficultLevel;
    }

    // Getter들
    public String getCourseName() { return courseName; }
    public Integer getCourseTime() { return courseTime; }
    public Long getPrice() { return price; }
    public String getDifficultLevel() { return difficultLevel; }
}