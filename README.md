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
### app/v1, trace/hellotrace/HelloTraceV1
* 에러를 남기기 위해 `try-catch` 사용
* `traceId` 가 연동되지 않는 문제
### app/v2, trace/hellotrace/HelloTraceV2
* `traceId` 연동을 위해 각 함수 파라미터로 전달
* 가장 지저분한 소스가 되어버린 상태
---
## 2. 쓰레드 로컬 - ThreadLocal
### app/v3, trace/logtrace/**
* interface 를 이용하여 구현하며, traceIdHolder 변수(String)를 이용하여 처리 (동시성 이슈 발생)
* LogTraceConfig 사용하여 FieldLogTrace or ThreadLocalLogTrace 사용
### 주의사항(!!)
* threadpool을 사용하는 경우 remove() 를 하지 않으면 threadlocal이 남아 있어 문제가 발생할 수 있음
---
## 3. 템플릿 메서드 패턴과 콜백 패턴