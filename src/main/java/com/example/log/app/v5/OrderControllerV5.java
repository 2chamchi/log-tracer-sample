package com.example.log.app.v5;


import com.example.log.trace.callback.TraceTemplate;
import com.example.log.trace.logtrace.LogTrace;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderControllerV5 {

    private final OrderServiceV5 orderService;
    private final TraceTemplate template;

    public OrderControllerV5(OrderServiceV5 serviceV5, LogTrace trace){
        this.orderService = serviceV5;
        this.template = new TraceTemplate(trace);
    }

    @GetMapping("/v5/request")
    public String request(String itemId){
        String result = template.execute("OrderController.request()", () -> {
            orderService.orderItem(itemId);
            return "OK";
        });
        return result;
    }
}
