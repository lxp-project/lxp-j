package jdbc.course.util;

import jdbc.course.exception.UserCancelException;
import java.io.BufferedReader;
import java.io.IOException;

public class ConsoleInputUtil {

    // 💡 유틸리티 클래스는 객체로 생성할 필요가 없으므로 생성자를 private으로 막아두는 것이 정석!
    private ConsoleInputUtil() {}

    public static Long getValidLong(BufferedReader br, String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = br.readLine();
                checkCancel(input);
                return Long.parseLong(input);
            } catch (NumberFormatException e) {
                System.out.println("  [주의] 숫자만 입력 가능합니다. 다시 시도해 주세요.");
            } catch (IOException e) {
                System.out.println("  [오류] 입력 시스템에 문제가 발생했습니다.");
            }
        }
    }

    public static Integer getValidInteger(BufferedReader br, String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = br.readLine();
                checkCancel(input);// q 검사
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("  [주의] 숫자만 입력 가능합니다. 다시 시도해 주세요.");
            } catch (IOException e) {
                System.out.println("  [오류] 입력 시스템에 문제가 발생했습니다.");
            }
        }
    }

    public static String getValidString(BufferedReader br, String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = br.readLine();
                checkCancel(input);
                if (input == null || input.trim().isEmpty()) {
                    System.out.println("  [주의] 빈 값은 입력할 수 없습니다.");
                    continue;
                }
                return input;
            } catch (IOException e) {
                System.out.println("  [오류] 입력 시스템에 문제가 발생했습니다.");
            }
        }
    }

    private static void checkCancel(String input) {
        if ("q".equalsIgnoreCase(input)) {
            // 이제 독립된 예외 클래스를 던집니다.
            throw new UserCancelException("작업이 취소되어 메인 메뉴로 돌아갑니다.");
        }
    }
}
