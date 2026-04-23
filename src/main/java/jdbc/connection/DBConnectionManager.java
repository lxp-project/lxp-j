package jdbc.connection;

import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;

public class DBConnectionManager {

    // 애플리케이션 실행 동안 유지될 단일 데이터소스 인스턴스
    private static HikariDataSource dataSource;

    // 객체 생성을 막기 위한 private 생성자 (유틸리티 클래스화)
    private DBConnectionManager() {}

    // DataSource를 요청할 때 생성되어 있지 않으면 생성 후 반환 (지연 초기화)
    public static DataSource getDataSource() {
        if (dataSource == null) {
            dataSource = new HikariDataSource();
            dataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/jdbcconsolepg");
            dataSource.setUsername("min");
            dataSource.setPassword("min");

            // 옵션: 커넥션 풀 사이즈 명시적 지정 (기본값은 10)
            dataSource.setMaximumPoolSize(10);
        }
        return dataSource;
    }

    // 애플리케이션 종료 시 커넥션 풀을 안전하게 닫아주는 메서드
    public static void close() {
        if (dataSource != null && !dataSource.isClosed()) {
            dataSource.close();
            System.out.println("=== DB 커넥션 풀 종료 완료 ===");
        }
    }
}
