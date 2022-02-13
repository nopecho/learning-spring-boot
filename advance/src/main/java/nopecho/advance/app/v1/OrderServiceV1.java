package nopecho.advance.app.v1;

import lombok.RequiredArgsConstructor;
import nopecho.advance.trace.TraceStatus;
import nopecho.advance.trace.mytrace.MyTraceV1;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV1 {

    private final OrderRepositoryV1 orderRepository;
    private final MyTraceV1 trace;

    public void orderItem(String itemID) {
        TraceStatus status = null;
        try{
            status = trace.begin("OrderService.orderItem()");
            orderRepository.save(itemID);
            trace.end(status);
        } catch (Exception e){
            trace.exception(status,e);
            throw  e;
        }
    }
}
