package nopecho.advance.trace.mytrace;

import nopecho.advance.trace.TraceStatus;
import org.junit.jupiter.api.Test;

public class MyTraceTestV2 {

    @Test
    void begin_end(){
        MyTraceV2 trace = new MyTraceV2();
        TraceStatus status = trace.begin("hello");
        TraceStatus status1 = trace.beginSync(status.getTraceId(), "hello2");
        trace.end(status1);
        trace.end(status);
    }

    @Test
    void begin_ex(){
        MyTraceV2 trace = new MyTraceV2();
        TraceStatus status = trace.begin("hello");
        TraceStatus status1 = trace.beginSync(status.getTraceId(), "hello2");
        trace.exception(status1,new IllegalArgumentException());
        trace.exception(status,new IllegalArgumentException());
    }
}
