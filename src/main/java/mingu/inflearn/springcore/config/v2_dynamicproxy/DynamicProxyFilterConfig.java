//package mingu.inflearn.springcore.config.v2_dynamicproxy;
//
//import mingu.inflearn.springcore.config.v2_dynamicproxy.handler.LogTraceFilterHandler;
//import mingu.inflearn.springcore.proxyapp.v1.*;
//import mingu.inflearn.springcore.trace.logtrace.LogTrace;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.lang.reflect.Proxy;
//
//@Configuration
//public class DynamicProxyFilterConfig {
//
//    private static final String[] PATTERNS = { "request*", "order*", "save*" };
//
//    @Bean
//    public OrderRepositoryV1 orderRepositoryV1(LogTrace logTrace) {
//        OrderRepositoryV1 orderRepository = new OrderRepositoryV1Impl();
//
//        return (OrderRepositoryV1) Proxy.newProxyInstance(OrderRepositoryV1.class.getClassLoader(),
//                new Class[] { OrderRepositoryV1.class },
//                new LogTraceFilterHandler(orderRepository, logTrace, PATTERNS));
//    }
//
//    @Bean
//    public OrderServiceV1 orderServiceV1(LogTrace logTrace) {
//        OrderServiceV1 orderService = new OrderServiceV1Impl(orderRepositoryV1(logTrace));
//
//        return (OrderServiceV1) Proxy.newProxyInstance(OrderServiceV1.class.getClassLoader(),
//                new Class[] { OrderServiceV1.class },
//                new LogTraceFilterHandler(orderService, logTrace, PATTERNS));
//    }
//
//    @Bean
//    public OrderControllerV1 orderControllerV1(LogTrace logTrace) {
//        OrderControllerV1 orderController = new OrderControllerV1Impl(orderServiceV1(logTrace));
//
//        return (OrderControllerV1) Proxy.newProxyInstance(OrderControllerV1.class.getClassLoader(),
//                new Class[] { OrderControllerV1.class },
//                new LogTraceFilterHandler(orderController, logTrace, PATTERNS));
//    }
//}
