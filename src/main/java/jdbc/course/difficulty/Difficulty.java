package jdbc.course.difficulty;

import java.util.Arrays;

public enum Difficulty {
    BEGINNER(1, "초보"),
    INTERMEDIATE(2, "중급"),
    ADVANCED(3, "상급");

    private final int code;
    private final String description;

    Difficulty(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    // 번호를 입력받아 해당하는 Enum을 찾아주는 정적 메서드
    public static Difficulty fromCode(int code) {
        return Arrays.stream(Difficulty.values())
            .filter(d -> d.code == code)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("잘못된 난이도 번호입니다."));
    }
}
