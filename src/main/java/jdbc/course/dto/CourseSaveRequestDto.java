package jdbc.course.dto;

public class CourseSaveRequestDto {

    /// (클라이언트 ➔ 서버: 등록할 때 넘어오는 데이터)

    private Long userId;
    private Long categoryId;
    private String courseName;
    private Integer courseTime;
    private Long price;
    private String thumbnailUrl;
    private String difficultLevel;

    public CourseSaveRequestDto(Long userId, Long categoryId, String courseName, Integer courseTime, Long price, String thumbnailUrl, String difficultLevel) {
        this.userId = userId;
        this.categoryId = categoryId;
        this.courseName = courseName;
        this.courseTime = courseTime;
        this.price = price;
        this.thumbnailUrl = thumbnailUrl;
        this.difficultLevel = difficultLevel;
    }

    // Getter
    public Long getUserId() { return userId; }
    public Long getCategoryId() { return categoryId; }
    public String getCourseName() { return courseName; }
    public Integer getCourseTime() { return courseTime; }
    public Long getPrice() { return price; }
    public String getThumbnailUrl() { return thumbnailUrl; }
    public String getDifficultLevel() { return difficultLevel; }



}