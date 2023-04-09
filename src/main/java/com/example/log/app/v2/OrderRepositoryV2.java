package com.example.log.app.v2;

import com.example.log.trace.TraceId;
import com.example.log.trace.TraceStatus;
import com.example.log.trace.hellotrace.HelloTraceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV2 {

    private final HelloTraceV2 traceV2;

    public void save(TraceStatus beforeStatus, String itemId){
        TraceId nextId = beforeStatus.getTraceId().createNextId();
        TraceStatus status = null;
        try {
            status = traceV2.beginSync(nextId, "OrderRepository.save()");
            if("ex".equals(itemId)){
                throw new IllegalStateException("예외 발생!!");
            }
            sleep(1000);
            traceV2.end(status);
        } catch (Exception exception){
            traceV2.exception(status, exception);
            throw exception;
        }
    }

    private void sleep(int millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
