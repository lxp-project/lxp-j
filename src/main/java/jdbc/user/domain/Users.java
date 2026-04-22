package jdbc.user.domain;

public class Users {

    /// 1. Entity 만들기

    private Long userId;
    private String name;
    private String role;

    //기본 생성자 (필수)
    public Users() {
    }

    public Users(Long userId, String name, String role) {
        this.userId = userId;
        this.name = name;
        this.role = role;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
