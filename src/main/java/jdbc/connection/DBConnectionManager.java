package jdbc.connection;

import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.io.InputStream;
import java.util.Properties;

public class DBConnectionManager {

    private static HikariDataSource dataSource;

    private DBConnectionManager() {}

    public static DataSource getDataSource() {
        if (dataSource == null) {
            try {
                // 1. properties 파일 불러오기
                Properties props = new Properties();

                // 클래스 로더를 통해 resources 폴더 안의 db.properties 파일을 읽음
                InputStream in = DBConnectionManager.class.getClassLoader().getResourceAsStream("db.properties");

                if (in == null) {
                    throw new RuntimeException("resources 폴더에서 db.properties 파일을 찾을 수 없습니다.");
                }

                // 파일 내용을 Properties 객체에 로드
                props.load(in);

                // 2. 읽어온 값으로 HikariDataSource 설정
                dataSource = new HikariDataSource();
                dataSource.setJdbcUrl(props.getProperty("db.url"));
                dataSource.setUsername(props.getProperty("db.username"));
                dataSource.setPassword(props.getProperty("db.password"));

                dataSource.setMaximumPoolSize(10);

            } catch (Exception e) {
                // 파일 읽기 실패나 DB 설정 실패 시 프로그램이 명확하게 종료되도록 예외 처리
                throw new RuntimeException("DB 커넥션 풀 초기화 실패: 설정 파일을 확인해주세요.", e);
            }
        }
        return dataSource;
    }

    public static void close() {
        if (dataSource != null && !dataSource.isClosed()) {
            dataSource.close();
            System.out.println("=== DB 커넥션 풀 종료 완료 ===");
        }
    }
}
