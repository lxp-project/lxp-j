# 📚 Course Management System (강의 관리 시스템)
> 순수 Java와 JDBC를 활용한 콘솔 기반 MVC 아키텍처 구현 프로젝트

## 1. 프로젝트 소개
- 본 프로젝트는 스프링 부트(Spring Boot) 같은 프레임워크 없이, **순수 자바(Pure Java)만을 이용하여 MVC 패턴을 직접 구현**한 강의 관리 시스템입니다.
- 백엔드 애플리케이션의 핵심인 의존성 주입(DI), 데이터베이스 커넥션 풀링, 그리고 계층형 아키텍처의 동작 원리를 깊이 있게 학습하기 위해 제작되었습니다.

## 2. 기술 스택
- **Language**: Java
- **Database**: PostgreSQL
- **Database Connection**: JDBC (Java Database Connectivity)
- **Connection Pool**: HikariCP
- **UI**: Console (System.in / out)

## 3. 아키텍처 및 핵심 개념
프레임워크의 자동 설정에 의존하지 않고, `Application.main`에서 핵심 객체들을 직접 생성하고 조립(조율)합니다.

* **View**: 사용자의 콘솔 입출력을 담당하며, 입력값을 DTO로 변환하여 Controller로 전달합니다. (`CourseCreateView`, `CourseMenuView` 등)
* **Controller**: View와 Service 사이의 요청/응답을 연결합니다.
* **Service**: 비즈니스 로직을 처리하고, DTO와 Entity 간의 변환을 수행합니다. (`CourseService`)
* **Repository**: 순수 JDBC를 사용하여 PostgreSQL 데이터베이스와 직접 통신하며 실제 CRUD 쿼리를 실행합니다. (`JdbcCourseRepository`)

## 4. 핵심 기능 (CRUD)
콘솔 인터페이스를 통해 다음 기능들을 제공합니다.

| 메뉴 번호 | 기능 | 설명 | Repository 주요 SQL |
| :---: | :--- | :--- | :--- |
| **1** | **등록** | 새로운 강의 정보 등록 | `INSERT INTO Courses ...` |
| **2** | **단일 조회** | 강의 ID로 특정 강의 상세 조회 | `SELECT * FROM Courses WHERE ...` |
| **3** | **전체 조회** | 등록된 모든 강의 목록 조회 | `SELECT * FROM Courses ORDER BY ...` |
| **4** | **수정** | 기존 강의의 정보 업데이트 | `UPDATE Courses SET ...` |
| **5** | **삭제** | 특정 강의 정보 삭제 | `DELETE FROM Courses WHERE ...` |

## 5. 실행 방법
1. PostgreSQL 데이터베이스를 실행하고 `jdbcconsolepg` 데이터베이스를 생성합니다.
2. `Application` 클래스의 `main` 메서드를 실행합니다.
3. 콘솔창에 출력되는 `[강의 시스템 테스트 시작]` 메시지와 메뉴판(1~5번)의 안내에 따라 시스템을 이용합니다.

## 6. 트러블슈팅 및 학습 포인트
- **순수 자바로 의존성 주입(DI) 직접 구현**
  - 프레임워크(Spring)의 `@Autowired` 마법에 의존하지 않고, `Application` 클래스의 `main` 메서드에서 `Repository -> Service -> Controller -> View` 순서로 객체를 직접 생성하고 조립했습니다.
  - 이를 통해 각 계층(Layer) 간의 결합도를 낮추고, 외부에서 의존성을 주입받는 DI(Dependency Injection)의 핵심 원리와 필요성을 체득했습니다.

- **안전한 DB 자원 관리 (try-with-resources)**
  - 순수 JDBC를 사용할 때 가장 주의해야 할 메모리/커넥션 누수(Leak)를 방지하기 위해 심혈을 기울였습니다.
  - 쿼리 실행 후 `Connection`, `PreparedStatement`, `ResultSet` 자원이 반드시 반납되도록 `try-with-resources` 구문을 일관되게 적용하여 안정성을 높였습니다.

- **DTO와 Entity의 엄격한 분리**
  - 데이터베이스와 직접 매핑되는 영속성 객체(`Course` Entity)와 클라이언트 요청/응답 객체(`CourseSaveRequestDto`, `CourseResponseDto`)를 철저히 분리했습니다.
  - 이를 통해 화면(View)에서 필요한 데이터만 안전하게 전달하고, 향후 DB 스키마가 변경되더라도 뷰 계층에 미치는 파급 효과를 최소화하는 설계의 중요성을 배웠습니다.

- **커넥션 풀(HikariCP) 적용 및 최적화**
  - 사용자가 요청을 보낼 때마다 DB 연결을 맺고 끊는 비용을 줄이기 위해 HikariCP 커넥션 풀을 도입했습니다.
  - `application.yml` 없이 순수 자바 코드로 DataSource 설정을 제어하며 커넥션 풀의 동작 방식을 이해했습니다.

- **계층별 맞춤형 예외 처리**
  - 단순히 에러를 던지는 것에 그치지 않고, 콘솔 입력 과정의 오류(`NumberFormatException`)와 비즈니스/DB 계층의 오류(`SQLException`, `IllegalArgumentException`)를 분리했습니다.
  - 에러 발생 시 프로그램이 강제 종료되지 않고, 사용자에게 원인(예: "숫자만 입력해야 합니다", "존재하지 않는 강의입니다")을 정확히 안내한 후 이전 메뉴로 복귀하도록 UX를 개선했습니다.
