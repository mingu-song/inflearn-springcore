# 스프링 핵심 원리 - 고급편
> [인프런 강의 바로가기](https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81-%ED%95%B5%EC%8B%AC-%EC%9B%90%EB%A6%AC-%EA%B3%A0%EA%B8%89%ED%8E%B8)
>
> Spring boot 2.6.4 / Java 11

## 0. 소개
* 
---
## 1. 예제 만들기
### app/v0
* 깔끔한 Controller - Service - Repository
### app/v1, HelloTraceV1
* 에러를 남기기 위해 `try-catch` 사용
* `traceId` 가 연동되지 않는 문제
### app/v2, HelloTraceV2
* `traceId` 연동을 위해 각 함수 파라미터로 전달
* 가장 지저분한 소스가 되어버린 상태
---
## 2. 쓰레드 로컬 - ThreadLocal