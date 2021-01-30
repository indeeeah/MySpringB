# MySpringB
-----
## Contents
1. [설계의 주안점](#설계의-주안점)
2. [Using](#Using)
3. [MySpringB 기능 설명](#MySpringB-기능-설명)
4. [추후 구현 예정 기능](#추후-구현-예정-기능)
-----
## 설계의 주안점
1. Spring MVC 방식을 사용한 CRUD 기능
2. 검색, 댓글, 조회수 기능
3. 원하는 폴더에 업로드된 파일 저장
4. 페이징 처리
5. AOP 사용
----
## Using
1. FrontEnd - HTML5, CSS3, JS, jQuery
2. BackEnd - Java(JDK 1.8), Servlet, Spring
3. OS - MacOs
4. Library&API - junit, mockito, ojdbc, mybatis commons-fileupload, commons-dbcp, json-simple
5. IDE - Spring Toll Suite 3
6. Server - Tomcat(v8.5)
7. Cl - Github, git
8. DataBase - Oracle DataBase 11g
----
## MySpringB 기능 설명
### [게시판 목록 페이지]
<img width="754" alt="스크린샷 2021-01-31 오전 12 37 20" src="https://user-images.githubusercontent.com/72774483/106361066-4116fa80-635f-11eb-8420-ac6dcd5e1f80.png">

1. 게시판 목록 조회 기능
> + 한 페이지에 10개의 글씩 페이징 기능
2. 게시판 검색 기능

<img width="753" alt="스크린샷 2021-01-31 오전 12 37 35" src="https://user-images.githubusercontent.com/72774483/106361077-4bd18f80-635f-11eb-8e16-2bb881ed1631.png">

### [게시판 입력 페이지]
<img width="456" alt="스크린샷 2021-01-31 오전 12 38 10" src="https://user-images.githubusercontent.com/72774483/106361128-9f43dd80-635f-11eb-87b4-9098ef612c97.png">

> + 게시글 입력 기능
> + 단일 파일 업로드 기능

### [단일 게시글 조회 기능]
1. 단일 게시글 조회 기능
<img width="1445" alt="스크린샷 2021-01-31 오전 12 38 26" src="https://user-images.githubusercontent.com/72774483/106361163-cdc1b880-635f-11eb-9781-27427f265ae0.png">

> + 글 삭제 기능

2. 댓글 작성 기능
<img width="1440" alt="스크린샷 2021-01-31 오전 12 38 41" src="https://user-images.githubusercontent.com/72774483/106361195-ff3a8400-635f-11eb-89d8-895410cd6215.png">
<img width="1446" alt="스크린샷 2021-01-31 오전 12 39 04" src="https://user-images.githubusercontent.com/72774483/106361197-019cde00-6360-11eb-98e3-f8dcc219a911.png">

3. 댓글 수정 및 삭제 기능
<img width="1447" alt="스크린샷 2021-01-31 오전 12 39 13" src="https://user-images.githubusercontent.com/72774483/106361206-0cf00980-6360-11eb-8d4b-320aabd9504a.png">

> + 비밀번호 확인 시 수정 및 삭제 가능

### [게시글 수정 기능]
<img width="638" alt="스크린샷 2021-01-31 오전 12 39 20" src="https://user-images.githubusercontent.com/72774483/106361217-1bd6bc00-6360-11eb-8082-40ed325e0e59.png">

> + 비밀번호 확인 후 수정 가능
----
## 추후 구현 예정 기능
1. UI 개선
2. 검색 결과 페이징 처리 개선
3. 사진 업로드 시 미리보기 제공 & 단일 게시글에 사진 띄우기