package nopecho.advance.trace.mytrace;

import lombok.extern.slf4j.Slf4j;
import nopecho.advance.trace.TraceId;
import nopecho.advance.trace.TraceStatus;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MyTraceV1 {

    private static final String START_PREFIX = "-->";
    private static final String COMPLETE_PREFIX = "<--";
    private static final String EX_PREFIX = "<X-";


    public TraceStatus begin(String message) {
        TraceId traceId = new TraceId();
        Long startTimeMs = System.currentTimeMillis();
        log.info("[{}] {}{}", traceId.getId(), addSpace(START_PREFIX, traceId.getLevel()), message);
        return new TraceStatus(traceId, startTimeMs, message);
    }

    public void end(TraceStatus traceStatus) {
        complete(traceStatus, null);
    }

    public void exception(TraceStatus status, Exception e) {
        complete(status, e);
    }

    private void complete(TraceStatus traceStatus, Exception e) {
        Long stopTimeMs = System.currentTimeMillis();
        long resultTimeMs = stopTimeMs - traceStatus.getStartTimeMs();
        TraceId traceId = traceStatus.getTraceId();

        if (e == null) {
            log.info("[{}] {}{} time={} ms",traceId.getId(),addSpace(COMPLETE_PREFIX,traceId.getLevel()),traceStatus.getMessage(),resultTimeMs);
        } else {
            log.info("[{}] {}{} time={} ms, ex={}",traceId.getId(),addSpace(EX_PREFIX,traceId.getLevel()),traceStatus.getMessage(),resultTimeMs, e.getClass().getSimpleName());
        }
    }

    private static String addSpace(String prefix, int level) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < level; i++) {
            sb.append((i == level - 1) ? "|" + prefix : "|   ");
        }
        return sb.toString();
    }
}
