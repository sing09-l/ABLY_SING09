# 에이블리코퍼레이션 과제전형 // 이성중
## :computer: 개발환경
     Java Version 52.0 (JDK 8.0) 
     Java SPRING 2.6.4
     Database h2database / Mybatis
     Intellij / Windows 10 / coolSMS API
## :clipboard: 기능 요구사항
  * :heavy_check_mark: 회원가입
    * 전화번호 인증 필수
  * :heavy_check_mark: 로그인 
    * 식별 가능 정보 ( 아이디, 이메일, 전화번호 + 비밀번호 )
  * :heavy_check_mark: 로그인 시 내 가입 정보만 표기
  * :heavy_check_mark: 비밀번호 (찾기) 재설정
    * 로그인이 되어있지 않은 상태에서,, 
    * 전화번호 인증 후 비밀번호 재설정
## :arrow_forward: 실행 방법
:one: cmd, gitbash 프로젝트 경로로 이동  **/sing09/build/libs/**    
:two: jar 빌드 파일 실행 명령어  **java -jar sing09-0.0.1-SNAPSHOT.jar**    
:three: 정상 실행 시   
http://localhost:8080/  이동 시 로그인 메인 페이지 조회.    
http://localhost:8080/h2-console  이동 시 h2db 조회 가능.     
Driver Class: org.h2.Driver / JDBC URL: jdbc:h2:mem:test / User Name: sing09 / Password: sing09   
## :dart: 기능 설명
* 최초 회원가입 후 이용 가능
* 회원가입 시 휴대전화 인증 필수
* 회원가입 시 입력한 식별 가능 정보로 로그인 가능 
     * 아이디+비밀번호, 이메일+비밀번호, 전화번호+비밀번호 (선택형)
* 로그인 시 내 정보 조회
* 비밀번호 찾기(재설정) 기능 이용 시 휴대전화 인증 필수.
     * 변경된 비밀번호로 로그인 가능
     
