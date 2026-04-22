package jdbc.user.controller;

import jdbc.user.dto.UsersDto;
import jdbc.user.service.UsersService;

import java.sql.SQLException;

public class UsersController {

    private final UsersService usersService;

    // 생성자 주입
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    /**
     * 회원 가입 요청 처리
     */
    public void createUser(String name, String role) {
        try {
            // 1. 입력을 DTO로 포장 (가방 담기)
            UsersDto.SaveRequest request = new UsersDto.SaveRequest(name, role);

            // 2. 서비스 호출
            UsersDto.Response response = usersService.join(request);

            // 3. 결과 출력 (원래는 JSON이나 HTML로 응답함)
            System.out.println("[Controller] 회원 가입 성공!");
            System.out.println("ID: " + response.getUserId() + ", 이름: " + response.getName());

        } catch (SQLException e) {
            System.err.println("[Controller] DB 에러 발생: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("[Controller] 잘못된 요청: " + e.getMessage());
        }
    }

    /**
     * 회원 조회 요청 처리
     */
    public void printUserInfo(Long userId) {
        try {
            // 1. 서비스 호출
            UsersDto.Response response = usersService.findOne(userId);

            // 2. 결과 출력
            System.out.println("----- 회원 정보 조회 결과 -----");
            System.out.println("ID   : " + response.getUserId());
            System.out.println("이름 : " + response.getName());
            System.out.println("역할 : " + response.getRole());
            System.out.println("----------------------------");

        } catch (Exception e) {
            System.err.println("[조회 실패] " + e.getMessage());
        }
    }
}