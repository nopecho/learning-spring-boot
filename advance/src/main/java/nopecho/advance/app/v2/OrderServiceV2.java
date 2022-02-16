package nopecho.advance.app.v2;

import lombok.RequiredArgsConstructor;
import nopecho.advance.trace.TraceId;
import nopecho.advance.trace.TraceStatus;
import nopecho.advance.trace.mytrace.MyTraceV2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV2 {

    private final OrderRepositoryV2 orderRepository;
    private final MyTraceV2 trace;

    public void orderItem(TraceId traceId, String itemID) {
        TraceStatus status = null;
        try{
            status = trace.beginSync(traceId,"OrderService.orderItem()");
            orderRepository.save(status.getTraceId(),itemID);
            trace.end(status);
        } catch (Exception e){
            trace.exception(status,e);
            throw  e;
        }
    }
}
