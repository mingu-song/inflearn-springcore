//package mingu.inflearn.springcore.app.v3;
//
//import lombok.RequiredArgsConstructor;
//import mingu.inflearn.springcore.trace.TraceStatus;
//import mingu.inflearn.springcore.trace.logtrace.LogTrace;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequiredArgsConstructor
//public class OrderControllerV3 {
//
//    private final OrderServiceV3 orderService;
//    private final LogTrace trace;
//
//    @GetMapping("/v3/request")
//    public String request(String itemId) {
//        TraceStatus status = null;
//        try {
//            status = trace.begin("OrderController.request()");
//            orderService.orderItem(itemId);
//            trace.end(status);
//            return "ok";
//        } catch (Exception e) {
//            trace.exception(status, e);
//            throw e;
//        }
//    }
//}
