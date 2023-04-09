package com.example.log.trace.logtrace;

import com.example.log.trace.TraceStatus;

public interface LogTrace {

    TraceStatus begin(String message);
    void end(TraceStatus status);
    void exception(TraceStatus status, Exception ex);
}
