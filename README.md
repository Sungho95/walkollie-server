# walk ollie

`walk ollie`는 사용자의 활동량(걸음수)을 기반으로 캐릭터("올리")를 육성하고, 아이템 및 칭호를 획득하며 다른 사용자와 소통할 수 있는 기능을 제공하는 Spring Boot 기반의 Kotlin 애플리케이션 서버입니다.
관리자 기능을 통해 사용자 문의 관리, 아이템 및 칭호 관리가 가능합니다.

## 🚀 주요 기술 스택

-   **언어:** Kotlin
-   **프레임워크:** Spring Boot 3.0.9
-   **데이터베이스:** JPA (MySQL Connector J 사용), Querydsl
-   **보안:** Spring Security, JWT
-   **API 문서화:** Springdoc OpenAPI
-   **배치 작업:** Spring Batch, Quartz
-   **캐싱:** Redis
-   **클라우드 서비스:** AWS S3 (파일 업로드), Firebase (푸시 알림)
-   **테스트:** Kotest, MockK
-   **빌드 도구:** Gradle

## ✨ 주요 기능

-   **🚶 사용자 활동 관리 (Calendar):**
    -   일별 걸음 수 기록 및 조회
    -   월별 캘린더 정보 조회
    -   총 걸음 수 조회
    -   걸음 수에 따른 포인트 및 올리 점수 부여
-   **👤 사용자 계정 관리 (Member):**
    -   회원가입 (디바이스 ID 기반) 및 로그인
    -   회원 정보 조회 및 알림 설정 변경
    -   회원 탈퇴
    -   JWT 기반 인증 및 로그아웃, 토큰 재발급
-   **🤖 올리 (Ollie) 캐릭터 관리:**
    -   올리 정보 (이름, 점수, 상태) 조회
    -   올리 이름 변경
    -   올리 아이템 착용/변경/해제
    -   올리 칭호 사용/변경/해제
    -   아이템 구매 및 칭호 획득
-   **👑 칭호 (Title) 관리:**
    -   사용자 칭호 정보 (획득 여부, 사용 여부 등) 리스트 및 상세 조회
    -   획득 가능한 칭호 조회
-   **🛍️ 아이템 (Item) 관리:**
    -   사용자 아이템 정보 (획득 여부, 착용 여부 등) 리스트 및 상세 조회
-   **🙋 문의 (Inquiry) 관리:**
    -   사용자 문의 생성, 조회, 수정, 삭제
    -   문의에 대한 관리자 답변 조회
-   **⚙️ 관리자 (Admin) 기능:**
    -   **문의 관리:** 사용자 문의 리스트 조회, 상세 조회, 답변 생성/수정/삭제
    -   **아이템 관리:** 아이템 생성, 조회, 수정, 삭제 (타입별 조회 가능)
    -   **칭호 관리:** 칭호 생성, 조회, 수정, 삭제 (카테고리 및 타입별 조회 가능)
-   **☁️ 파일 업로드 (File Upload):**
    -   S3를 이용한 이미지 파일 업로드 (문의, 아이템, 칭호 이미지 등)
-   **🔔 알림 (Notification):**
    -   Firebase (FCM)를 이용한 푸시 알림 발송 (리마인더, 칭호/아이템 획득 등)
    -   스케줄러를 통한 정기적인 리마인더 알림 발송
-   **🔐 보안 (Security):**
    -   Spring Security 및 JWT를 통한 API 인증 및 인가 처리
-   **📄 API 문서화 (API Documentation):**
    -   Springdoc OpenAPI를 이용한 API 문서 자동 생성

## 구조

프로젝트는 크게 다음과 같은 패키지 구조로 구성되어 있습니다.

-   `com.richbasoft.ollie.admin`: 관리자 기능 관련 도메인, 서비스, 컨트롤러
    -   `domain`: 관리자 기능별 DTO, 리포지토리, 서비스 인터페이스 및 구현체
    -   `interfaces`: 관리자 기능 API 컨트롤러
-   `com.richbasoft.ollie.common`: 공통으로 사용되는 설정, 유틸리티, 예외 처리 등
    -   `base`: 기본 컨트롤러, DTO, 엔티티, 서비스 클래스
    -   `config`: 각종 설정 클래스 (OpenAPI, Querydsl, Redis, S3, Security, Web 등)
    -   `exception`: 비즈니스 로직 예외 및 글로벌 예외 핸들러
    -   `fileupload`: 파일 업로드 관련 서비스 및 컨트롤러
    -   `firebase`: Firebase 연동 관련 DTO 및 서비스
    -   `redis`: Redis 연동 DAO
    -   `scheduler`: 스케줄링 서비스
    -   `security`: Spring Security 관련 설정, 필터, 핸들러, JWT 프로바이더 등
    -   `utils`: 로깅, JSON 직렬화, 어노테이션 등 각종 유틸리티
-   `com.richbasoft.ollie.domain`: 핵심 비즈니스 로직 관련 도메인, 서비스, 컨트롤러
    -   `calendar`: 캘린더(활동량) 관련
    -   `inquiry`: 사용자 문의 관련
    -   `item`: 아이템 관련
    -   `member`: 회원 관련
    -   `notification`: 알림 관련
    -   `ollie`: 올리(캐릭터) 관련
    -   `title`: 칭호 관련
-   `com.richbasoft.ollie.interfaces`: 외부 및 내부 API 인터페이스 (컨트롤러)
    -   `external`: 외부 연동 API (알림 등)
    -   `internal`: 앱 내부에서 사용되는 API
      
## 🛠️ 빌드 및 실행

1.  **MySQL 데이터베이스 설정:** `application.yml` (또는 해당 프로파일의 yml) 파일에 MySQL 접속 정보를 설정합니다. (해당 파일은 제공되지 않았으므로, 일반적인 Spring Boot 프로젝트 구성을 따릅니다.)
2.  **AWS S3 및 Firebase 설정:** `application.yml` 에 관련 Access Key, Secret Key, Bucket 정보 및 `firebase-key.json` 파일을 `resources/firebase` 경로에配置합니다.
3.  **빌드:** Gradle을 사용하여 프로젝트를 빌드합니다.
    ```bash
    ./gradlew build
    ```
4.  **실행:** 빌드된 JAR 파일을 실행합니다. (개발 환경에 따라 프로파일을 지정할 수 있습니다.)
    ```bash
    java -jar build/libs/ollie-server-0.0.1-SNAPSHOT.jar --spring.profiles.active=develop
    ```
    또는 `scripts/deploy.sh` 스크립트를 참고하여 배포 환경에 맞게 실행할 수 있습니다.

## 🔗 API

주요 API 엔드포인트는 다음과 같습니다. 상세 내용은 OpenAPI 문서를 참고하십시오. (애플리케이션 실행 후 `/swagger-ui/index.html` 경로)

-   **Admin API:** `/api/v1/admin/**`
    -   문의 관리: `/api/v1/admin/inquiry/**`
    -   아이템 관리: `/api/v1/admin/item/**`
    -   칭호 관리: `/api/v1/admin/title/**`
-   **Auth API:** `/api/v1/member/login`, `/api/v1/member/logout`, `/api/v1/member/refresh`
-   **Member API:** `/api/v1/member/**`
-   **Calendar API:** `/api/v1/calendar/**`
-   **Ollie API:** `/api/v1/ollie/**`
-   **Item API:** `/api/v1/item/**`
-   **Title API:** `/api/v1/title/**`
-   **Inquiry API:** `/api/v1/inquiry/**`
-   **File API:** `/api/v1/file/**`
-   **Notification API (External):** `/api/v1/notification/**`
