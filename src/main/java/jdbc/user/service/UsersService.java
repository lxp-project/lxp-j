package jdbc.user.service;

import jdbc.user.domain.Users;
import jdbc.user.dto.UsersDto;
import jdbc.user.repository.UsersRepository;

import java.sql.SQLException;

public class UsersService {

    private final UsersRepository usersRepository;

    // 생성자 주입 (의존성 주입)
    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    /**
     * 회원 가입 로직
     */
    public UsersDto.Response join(UsersDto.SaveRequest request) throws SQLException {
        // 1. 비즈니스 검증 (예시: 이름은 2글자 이상이어야 함)
        if (request.getName() == null || request.getName().length() < 2) {
            throw new IllegalArgumentException("이름은 최소 2글자 이상이어야 합니다.");
        }

        // 2. DTO -> Entity 변환
        // DB에 넣기 전에는 아직 ID가 없으므로 null을 넣음
        Users user = new Users(null, request.getName(), request.getRole());

        // 3. Repository 호출 (DB 저장)
        Users savedUser = usersRepository.save(user);

        // 4. Entity -> DTO 변환 후 반환 (응답용 가방에 담기)
        return new UsersDto.Response(
                savedUser.getUserId(),
                savedUser.getName(),
                savedUser.getRole()
        );
    }

    /**
     * 회원 조회 로직
     */
    public UsersDto.Response findOne(Long userId) throws SQLException {
        // Repository 호출
        Users user = usersRepository.findById(userId);

        // Entity -> DTO 변환 후 반환
        return new UsersDto.Response(
                user.getUserId(),
                user.getName(),
                user.getRole()
        );
    }
}