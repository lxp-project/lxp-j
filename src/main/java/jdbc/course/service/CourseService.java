package jdbc.course.service;

import jdbc.course.domain.Course;
import jdbc.course.dto.CourseResponseDto;
import jdbc.course.dto.CourseSaveRequestDto;
import jdbc.course.dto.CourseUpdateRequestDto;
import jdbc.course.repository.CourseRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseService {

    private final CourseRepository courseRepository;

    // 의존성 주입 (Repository를 받아옴)
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    // 1. 등록 흐름: DTO 받음 -> Entity 변환 -> DB 저장 -> Entity 반환받음 -> DTO로 변환하여 리턴
    public CourseResponseDto registerCourse(CourseSaveRequestDto request) throws SQLException {
        // [변환 1] DTO -> Entity (ID와 시간은 아직 없으므로 null)
        Course course = new Course(
                null, request.getUserId(), request.getCategoryId(), request.getCourseName(),
                request.getCourseTime(), request.getPrice(), request.getThumbnailUrl(),
                null, null, request.getDifficultLevel()
        );

        // Repository를 통해 DB에 저장 (이때 ID와 생성시간이 채워진 Entity가 돌아옴)
        Course savedCourse = courseRepository.save(course);

        // [변환 2] Entity -> Response DTO (클라이언트에게 안전하게 보낼 데이터만 포장)
        return convertToResponseDto(savedCourse);
    }

    // 2. 단건 조회
    public CourseResponseDto getCourse(Long courseId) throws SQLException {
        Course course = courseRepository.findById(courseId);
        if (course == null) {
            throw new IllegalArgumentException("존재하지 않는 강의입니다.");
        }
        return convertToResponseDto(course);
    }

    // 3. 전체 조회
    public List<CourseResponseDto> getAllCourses() throws SQLException {
        List<Course> courses = courseRepository.findAll();
        List<CourseResponseDto> responses = new ArrayList<>();

        for (Course course : courses) {
            responses.add(convertToResponseDto(course)); // 각각의 Entity를 DTO로 변환하여 리스트에 담음
        }
        return responses;
    }
    public CourseResponseDto updateCourse(Long courseId, CourseUpdateRequestDto request) throws SQLException {
        // 1. 기존 데이터 조회
        Course course = courseRepository.findById(courseId);
        if (course == null) {
            throw new IllegalArgumentException("수정하려는 강의가 존재하지 않습니다.");
        }

        // 2. 엔티티 데이터 업데이트 (비즈니스 로직 호출)
        course.updateCourseInfo(
                request.getCourseName(),
                request.getCourseTime(),
                request.getPrice(),
                request.getDifficultLevel()
        );

        // 3. DB 반영
        courseRepository.update(courseId, course);

        // 4. 결과 반환
        return convertToResponseDto(course);
    }

    // 4. 삭제
    public void deleteCourse(Long courseId) throws SQLException {
        courseRepository.deleteById(courseId);
    }

    // 내부에서만 사용하는 공통 변환 메서드 (Entity -> Response DTO)
    private CourseResponseDto convertToResponseDto(Course course) {
        return new CourseResponseDto(
                course.getCourseId(),
                course.getCourseName(),
                course.getCourseTime(),
                course.getPrice(),
                course.getDifficultLevel(),
                course.getCreatedAt()
        );
    }
}