package com.example.log.app.v3;

import com.example.log.trace.TraceId;
import com.example.log.trace.TraceStatus;
import com.example.log.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV3 {

    private final OrderRepositoryV3 orderRepository;
    private final LogTrace trace;

    public void orderItem(String itemId){
        TraceStatus status = null;
        try {
            status = trace.begin("OrderService.orderItem()");
            orderRepository.save(itemId);
            trace.end(status);
        } catch (Exception exception){
            trace.exception(status, exception);
            throw exception;
        }
    }
}
