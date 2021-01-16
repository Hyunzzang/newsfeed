# 타임라인 서비스 api

## 개발 프레임워크 
* Java8
* Spring boot
* Spring data jpa
* Spring data redis
* H2
* embedded redis
* lombok
* apache commons-lang3
* maven

## 빌드 및 실행 방법
  - java8 이상 필요

  * 소스를 내려 받는다.
  ```bash
  $ git clone https://github.com/Hyunzzang/newsfeed.git
  ```

  * 프로젝트 디렉토리의 mvnw으로 빌드 (mac os)
  ```bash
  $ cd newsfeed
  $ ./mvnw clean package
  ```
  * mvnw으로 빌드 (윈도우)
  ```bash
  ./mvnw.cmd clean install
  ```

  * 빌드 후 java -jar 으로 실행 방법 
  ```bash
  $ java -jar target/newsfeed-0.0.1-SNAPSHOT.jar
  ```
  * mvnw으로 spring boot 실행 방법
  ```bash
  $ ./mvnw spring-boot:run
  ```

### api 설명
#### 1. 유저 가입 
  *  유저의 회원가입 처리
  
  > [POST] /api/user/join

#### 2. 팔로우 하기
  * 다른 유저 친구 맺기
  
  > [POST] /api/relation/follow

#### 3. 유저 정보 조회(팔로우, 팔로워)
  * 팔로우 및 팔로워 대상을 알음 수 있음 
  
  > [GET] /api/user/{email}

#### 4. 뉴스등록
  * 뉴스를 등록
  * 팔로워 들에게 뉴스 발송
  
  > [POST] /api/news/register

#### 5. 뉴스보기 
  * 팔로우한 유저의 뉴스 보기
  
  > [GET] /api/news/{email}


### api 테스트 시나리오
```
-------- 등록 --------
curl -X POST http://localhost:8080/api/user/join -H 'cache-control: no-cache' -H 'content-type: application/json' -d '{ "email":"test@test.com", "pw":"123456" }'

curl -X POST http://localhost:8080/api/user/join -H 'cache-control: no-cache' -H 'content-type: application/json' -d '{ "email":"test2@test.com", "pw":"123456" }'

-------- 팔로우 --------
curl -X POST http://localhost:8080/api/relation/follow -H 'cache-control: no-cache' -H 'content-type: application/json' -d '{ "myEmail":"test@test.com", "targetEmail":"test2@test.com" }'

-------- 조회 --------
curl -X GET http://localhost:8080/api/user/test@test.com -H 'cache-control: no-cache' -H 'content-type: application/json' 

curl -X GET http://localhost:8080/api/user/test2@test.com -H 'cache-control: no-cache' -H 'content-type: application/json' 

-------- 뉴스등록 --------
curl -X POST http://localhost:8080/api/news/register -H 'cache-control: no-cache' -H 'content-type: application/json' -d '{ "createEmail":"test2@test.com", "title":"제목", "contents":"내용" }'

-------- 뉴스보기 --------
curl -X GET http://localhost:8080/api/news/test@test.com -H 'cache-control: no-cache' -H 'content-type: application/json' 
```