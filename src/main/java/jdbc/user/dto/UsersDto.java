package jdbc.user.dto;

public class UsersDto {
    /// 회원가입 요청시 사용하는 객체 (userId가 없음)

    public static class SaveRequest{
        private String name;
        private String role;

        public SaveRequest() {
        }

        public SaveRequest(String name, String role) {
            this.name = name;
            this.role = role;
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

    public static class Response{
        private Long userId;
        private String name;
        private String role;
        public Response() {

        }

        public Response(Long userId, String name, String role) {
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
}
