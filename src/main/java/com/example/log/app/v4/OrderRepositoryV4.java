package com.example.log.app.v4;

import com.example.log.trace.TraceStatus;
import com.example.log.trace.logtrace.LogTrace;
import com.example.log.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV4 {

    private final LogTrace trace;

    public void save(String itemId){

        AbstractTemplate<Void> template = new AbstractTemplate<Void>(trace) {
            @Override
            public Void call() {
                try {
                    if("ex".equals(itemId)){
                        throw new IllegalStateException("예외 발생!!");
                    }
                    sleep(1000);
                } catch (Exception exception){
                    throw exception;
                }
                return null;
            }
        };
        template.execute("OrderRepository.save()");
    }

    private void sleep(int millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
