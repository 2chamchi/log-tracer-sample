package com.example.log.app.v1;

import com.example.log.trace.TraceStatus;
import com.example.log.trace.hellotrace.HelloTraceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV1 {

    private final HelloTraceV1 traceV1;

    public void save(String itemId){

        TraceStatus status = null;
        try {
            status = traceV1.begin("OrderRepository.save()");
            if("ex".equals(itemId)){
                throw new IllegalStateException("예외 발생!!");
            }
            sleep(1000);
            traceV1.end(status);
        } catch (Exception exception){
            traceV1.exception(status, exception);
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
