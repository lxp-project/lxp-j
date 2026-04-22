package jdbc.course.domain;

import java.sql.Timestamp;

public class Course {
    private Long courseId;
    private Long userId;
    private Long categoryId;
    private String courseName;
    private Integer courseTime;
    private Long price;
    private String thumbnailUrl;
    private Timestamp createdAt;
    private Timestamp modifiedAt;
    private String difficultLevel;

    // Entity는 주로 DB에서 값을 가져올 때와 넣을 때 사용하므로 생성자를 만듭니다.
    public Course(Long courseId, Long userId, Long categoryId, String courseName,
                  Integer courseTime, Long price, String thumbnailUrl,
                  Timestamp createdAt, Timestamp modifiedAt, String difficultLevel) {
        this.courseId = courseId;
        this.userId = userId;
        this.categoryId = categoryId;
        this.courseName = courseName;
        this.courseTime = courseTime;
        this.price = price;
        this.thumbnailUrl = thumbnailUrl;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.difficultLevel = difficultLevel;
    }


    // Getter 메서드들 (생략하지 말고 모두 만들어주세요)
    public Long getCourseId() { return courseId; }
    public Long getUserId() { return userId; }
    public Long getCategoryId() { return categoryId; }
    public String getCourseName() { return courseName; }
    public Integer getCourseTime() { return courseTime; }
    public Long getPrice() { return price; }
    public String getThumbnailUrl() { return thumbnailUrl; }
    public Timestamp getCreatedAt() { return createdAt; }
    public Timestamp getModifiedAt() { return modifiedAt; }
    public String getDifficultLevel() { return difficultLevel; }

    public void updateCourseInfo(String courseName, Integer courseTime, Long price, String difficultLevel) {
        this.courseName = courseName;
        this.courseTime = courseTime;
        this.price = price;
        this.difficultLevel = difficultLevel;
        // this.modifiedAt = new Timestamp(System.currentTimeMillis());  <-- 잠시 주석으로 냅둠
    }
}