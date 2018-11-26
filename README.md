# 예약관리 시스템
회의실, 날짜, 사용 시간을 입력하여 회의실을 예약하는 회의실 예약 웹 애플리케이션

## 개발 환경
* Spring Boot
    * reservation-server : 2.1.0.RELEASE
    * reservation-ui : 1.5.10.RELEASE
* Gradle : 4.6

## 문제해결 전략
* jquery-timepicker로 프론트엔드에서 시작시간, 종료시간을 정시로 부터 30분 단위 입력만 가능하도록 구현
* 서버에서 DB에 저장하기 전에 입력받은 시작시간, 종료시간 사이에 동일한 회의실의 예약이 있는지 조회
    * 예약이 있을경우 수행하지 않음
    * 주단위 반복 예약일 경우 해당 날짜를 제외한 예약 가능한 날짜에 대해 예약 수행
* 예약을 수행하는 서비스 로직에 대해 ```synchronized``` 동기화로 리퀘스트 순서 보장
* 예약 및 조회는 AJAX 비동기 통신 사용

## 빌드 및 실행
* repository clone 및 디렉토리 이동  
    ```$ git clone https://github.com/ssipflow/reservation_system.git && cd reservation_system```
* gradle 빌드 및 실행
    * API 서버 실행  
        ```$ cd reservation-server && ./gradlew bootRun --parallel```
    * UI 프로젝트 실행  
        ```$ cd reservation-ui && ./gradlew bootRun --parallel```
* 실행이 완료되면 [http://localhost:8080/index](http://localhost:8080/index) 에서 예약 및 조회