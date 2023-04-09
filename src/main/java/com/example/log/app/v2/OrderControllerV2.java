package com.example.log.app.v2;


import com.example.log.trace.TraceStatus;
import com.example.log.trace.hellotrace.HelloTraceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV2 {

    private final OrderServiceV2 orderService;
    private final HelloTraceV2 traceV2;

    @GetMapping("/v2/request")
    public String request(String itemId){
        TraceStatus status = null;
        try {
            status = traceV2.begin("OrderController.request()");
            orderService.orderItem(status, itemId);
            traceV2.end(status);
            return "OK";
        } catch (Exception exception){
            traceV2.exception(status, exception);
            throw exception;
        }
    }
}
