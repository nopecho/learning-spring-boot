package nopecho.advance.trace.mytrace;

import nopecho.advance.trace.TraceStatus;
import org.junit.jupiter.api.Test;

public class MyTraceTest  {

    @Test
    void begin_end(){
        MyTraceV1 trace = new MyTraceV1();
        TraceStatus status = trace.begin("hello");
        trace.end(status);
    }

    @Test
    void begin_ex(){
        MyTraceV1 trace = new MyTraceV1();
        TraceStatus status = trace.begin("hello");
        trace.exception(status,new IllegalArgumentException());
    }
}
