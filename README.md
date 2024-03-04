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
---
## 6. 스프링이 지원하는 프록시
* `ProxyFactory` - JDK 동적 프록시와 CGLIB 를 동시에 적용할 필요가 있을 경우
* `Advice` - InvocationHandler와 MehtodIntercetor 에서 공통으로 처리할 로직
* `Pointcut` - 특정 부가 기능을 어디에 추가할 것인지 필터링 로직
* `Advisor` - Advice + Pointcut
* `AspectJExpressionPointcut` 을 가장 많이 사용
* target 에 여러 advisor 를 적용하더라도, proxy는 하나만 생성
---
## 7. 빈 후처리기
* `aspectJ` - AOP 관련 클래스를 자동으로 스프링 빈 등록
* `AutoProxyCreator` - 자동설정으로 `AnnotationAwareAspectAutoProxyCreator` 후처리 빈 등록, Advisor 찾아서 pointcut 과 advice 적용(@AspectJ 포함)
* **포인트컷 사용 용도**
  * 프록시 적용 여부 (생성) : 후처리기에서 advisor의 포인트컷을 이용하여 빈 메소를 모두 체크하여 결정
  * 어드바이스 적용 여부 (사용) : 프록시가 호출되면 포인트컷을 이용하여 어드바이스 적용 체크 후 target 호출
---
## 8. @Aspect AOP
* 어노테이션을 이용한 어드바이저(pointcut + advice) 생성
---
## 9. 스프링 AOP 개념
* `애스펙트` - 애플리케이션을 바라보는 관점을 하나하나의 기능에서 횡단 관심사(cross-cutting concerns) 관점으로 달리 보는 것이
* https://eclipse.dev/aspectj/
* 스프링은 에스펙트 기능중 일부를 사용 (proxy를 통한 AOP) -> 메소드 실행 지점에만 사용가능 (런타임)
* 용어정리
  * `조인 포인트(Join point)` - aop 적용 가능한 **모든** 위치
  * `포인트컷(Pointcut)` - 조인 포인트 중 실제 어드바이스가 적용될 특정 위치
  * `타켓(Target)` - 포인트컷으로 결정된 객체
  * `어드바이스(Advice)` - 부가 기능 자체
  * `애스펙트(Aspect)` - 어드바이스 + 포인트컷을 모듈화 한 것
  * `어드바이저(Advisor)` - 하나의 어드바이스와 하나의 포인트 컷(스프링 AOP에서만 사용되는 용어)
  * `위빙(Weaving)` - 포인트컷으로 결정한 타켓의 조인 포인트에 어드바이스를 적용하는 것
  * `AOP 프록시` - AOP 기능을 구현하기 위해 만든 프록시 객체(jdk동적프록시 or CGLIB프록시)
---
## 10. 스프링 AOP 구현 (test/aop/AopTest.java)
* `@Around` - 메서드 호출 전후에 수행, 가장 강력한 어드바이스, 조인 포인트 실행 여부 선택, 반환 값 변환, 예외 변환 등이 가능 
* `@Before` - 조인 포인트 실행 이전에 실행 
* `@AfterReturning` - 조인 포인트가 정상 완료후 실행
* `@AfterThrowing` - 메서드가 예외를 던지는 경우 실행
* `@After` - 조인 포인트가 정상 또는 예외에 관계없이 실행(finally)
* 추상화 되고 제약이 있을수록 좋은 설계가 됨
  * 타겟호출을 할 이유가 없다면 Before 를 사용하여 제약을 둠
---
## 11. 스프링 AOP - 포인트컷(test/pointcut/*.java)
* `execution` - 메소드 실행 조인 포인트를 매칭. 스프링 AOP에서 가장 많이 사용하고, 기능도 복잡
  * `()` - 파라미터가 없어야 함
  * `(*)` - 정확히 하나의 파라미터, 단 모든 타입을 허용
  * `(*, *)` - 정확히 두 개의 파라미터, 단 모든 타입을 허용
  * `(..)` - 숫자와 무관하게 모든 파라미터, 모든 타입을 허용한다. 참고로 파라미터가 없어도 됨
  * `(String, ..)` - String 타입으로 시작해야 한다. 숫자와 무관하게 모든 파라미터, 모든 타입을 허용
* `within` - 특정 타입 내의 조인 포인트를 매칭
  * 인터페이스 설정 불가
* `args` - 인자가 주어진 타입의 인스턴스인 조인 포인트
* `@target` - 실행 객체의 클래스에 주어진 타입의 애노테이션이 있는 조인 포인트 (부모도 적용)
* `@within` - 주어진 애노테이션이 있는 타입 내 조인 포인트 (부모 미적용)
* `@annotation` - 메서드가 주어진 애노테이션을 가지고 있는 조인 포인트를 매칭 (가끔사용됨)
* `@args` - 전달된 실제 인수의 런타임 타입이 주어진 타입의 애노테이션을 갖는 조인 포인트
* `bean` - 스프링 전용 포인트컷 지시자, 빈의 이름으로 포인트컷을 지정한다.
* 런타임에 판단하는 지시자들(args , @args , @target)은 프록시가 없기에 사용할 수 없음 그래서 execution 을 통해 대상을 한정하여 프록시 생성 필요
* `this` - 스프링 빈 객체(스프링 AOP 프록시)를 대상으로 하는 조인 포인트
* `target` - Target 객체(스프링 AOP 프록시가 가리키는 실제 대상)를 대상으로 하는 조인 포인트
* `this, target`
  * CGLIB 프록시는 구체를 대상으로 생성
    * 인터페이스 지정 - 모두 AOP 대상
    * 구체 클래스 지정 - 모두 AOP 대상
  * JDK 동적 프록시는 인터페이스를 대상으로 생성
    * 인터페이스 지정 - 모두 AOP 대상
    * 구체 클래스 지정 - this 는 인터페이스이므로 AOP 대상 안됨
