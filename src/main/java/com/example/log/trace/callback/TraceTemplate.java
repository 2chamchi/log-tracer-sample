package com.example.log.trace.callback;

import com.example.log.trace.TraceStatus;
import com.example.log.trace.logtrace.LogTrace;

public class TraceTemplate {

    private final LogTrace trace;

    public TraceTemplate(LogTrace trace){
        this.trace = trace;
    }

    public <T> T execute(String message, TraceCallback<T> callback) {
        TraceStatus status = null;
        try {
            status = trace.begin(message);
            T result = callback.call();
            trace.end(status);
            return result;
        } catch (Exception ex){
            trace.exception(status, ex);
            throw  ex;
        }
    }
}
