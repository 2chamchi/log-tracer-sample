package com.example.log.app.v2;

import com.example.log.trace.TraceId;
import com.example.log.trace.TraceStatus;
import com.example.log.trace.hellotrace.HelloTraceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV2 {

    private final OrderRepositoryV2 orderRepository;
    private final HelloTraceV2 traceV2;

    public void orderItem(TraceStatus beforeStatus, String itemId){
        TraceId nextId = beforeStatus.getTraceId().createNextId();
        TraceStatus status = null;
        try {
            status = traceV2.beginSync(nextId, "OrderService.orderItem()");
            orderRepository.save(status, itemId);
            traceV2.end(status);
        } catch (Exception exception){
            traceV2.exception(status, exception);
            throw exception;
        }
    }
}
