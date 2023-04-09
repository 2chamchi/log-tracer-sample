package com.example.log.trace.template;

import com.example.log.trace.TraceStatus;
import com.example.log.trace.logtrace.LogTrace;

public abstract class AbstractTemplate<T> {

    private final LogTrace trace;

    public AbstractTemplate(LogTrace trace){
        this.trace = trace;
    }

    public T execute(String message) {
        TraceStatus status = null;
        try {
            status = trace.begin(message);
            T result = call();
            trace.end(status);
            return result;
        } catch (Exception ex) {
            trace.exception(status, ex);
            throw ex;
        }
    }

    public abstract T call();
}
