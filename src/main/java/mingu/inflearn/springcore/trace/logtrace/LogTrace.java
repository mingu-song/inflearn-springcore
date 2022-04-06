package mingu.inflearn.springcore.trace.logtrace;

import mingu.inflearn.springcore.trace.TraceStatus;

public interface LogTrace {

    TraceStatus begin(String message);

    void end(TraceStatus status);

    void exception(TraceStatus status, Exception e);
}
