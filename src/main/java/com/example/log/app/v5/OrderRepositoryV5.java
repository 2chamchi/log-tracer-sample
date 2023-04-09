package com.example.log.app.v5;

import com.example.log.trace.callback.TraceTemplate;
import com.example.log.trace.logtrace.LogTrace;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepositoryV5 {
    private final TraceTemplate template;

    public OrderRepositoryV5(LogTrace trace) {
        this.template = new TraceTemplate(trace);
    }

    public void save(String itemId){
        template.execute("OrderRepository.save()", () -> {
            try {
                if("ex".equals(itemId)){
                    throw new IllegalStateException("예외 발생!!");
                }
                sleep(1000);
            } catch (Exception exception){
                throw exception;
            }
            return null;
        });
    }

    private void sleep(int millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
