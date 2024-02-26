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
## 4. 프록시 패턴과 데코레이터 패턴
* 프록시패턴 : 접근 제어가 목적
* 데코레이터 패턴 : 새로운 기능 추가가 목적
* 인터페이스 기반 프록시가 편하지만 항상 편한것은 아님
* 로직은 동일하지만 적용 대상 클래스만 달라짐 -> 동적 프록시 적용 필요
---
## 5. 동적 프록시 기술
### JDK 동적 프록시 (v2_dynamicproxy)
* `InvocationHandler` 를 이용하고 `Proxy.newProxyInstance` 를 이용하여 동적으로 생성가능
* 인터페이스와 구현체 사이에 Proxy를 하나하나 만드는 것에서 InvocationHandler 로 통일
* 인터페이스가 필수이며, 클래스만 있는 경우는 바이트코드 레벨 조작인 CGLIB 사용해야함
### CGLIB(test/cglib)
* code generator library
* 실제 사용하는 경우는 거의 없으며, 스프링에서 사용하니 알고 있으면 좋음
* 기본생성자가 필요하고, 의존관계를 setter로 처리하는 등의 제약사항이 있기에 `ProxyFactory` 사용
---

