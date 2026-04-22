package jdbc.user.repository;

import jdbc.user.domain.Users;
import org.springframework.jdbc.support.JdbcUtils;

import javax.sql.DataSource;
import java.sql.*;
import java.util.NoSuchElementException;

public class UsersRepository {

    private final DataSource dataSource;

    public UsersRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // 1. 저장 (자동 생성된 PK를 가져오는 게 핵심!)
    public Users save(Users user) throws SQLException {
        String sql = "insert into Users(name, role) values(?, ?)";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = dataSource.getConnection();

            // Statement.RETURN_GENERATED_KEYS : "DB야, 네가 방금 만든 PK 값 좀 돌려줘!"
            pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getRole());
            pstmt.executeUpdate();

            // 방금 생성된 PK 값 꺼내기
            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                Long generatedId = rs.getLong(1);
                user.setUserId(generatedId); // 자바 객체에 ID 채워넣기
            }
            return user;

        } catch (SQLException e) {
            System.err.println("db error: " + e.getMessage());
            throw e;
        } finally {
            JdbcUtils.closeResultSet(rs);
            JdbcUtils.closeStatement(pstmt);
            JdbcUtils.closeConnection(con); // 풀에 반납
        }
    }

    // 2. 단건 조회
    public Users findById(Long userId) throws SQLException {
        String sql = "select * from Users where user_id = ?";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setLong(1, userId);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                Users user = new Users();
                user.setUserId(rs.getLong("user_id"));
                user.setName(rs.getString("name"));
                user.setRole(rs.getString("role"));
                return user;
            } else {
                throw new NoSuchElementException("user not found = " + userId);
            }

        } catch (SQLException e) {
            System.err.println("db error: " + e.getMessage());
            throw e;
        } finally {
            JdbcUtils.closeResultSet(rs);
            JdbcUtils.closeStatement(pstmt);
            JdbcUtils.closeConnection(con);
        }
    }
}