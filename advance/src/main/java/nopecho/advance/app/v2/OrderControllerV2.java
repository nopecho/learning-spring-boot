package nopecho.advance.app.v2;

import lombok.RequiredArgsConstructor;
import nopecho.advance.trace.TraceStatus;
import nopecho.advance.trace.mytrace.MyTraceV1;
import nopecho.advance.trace.mytrace.MyTraceV2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV2 {

    private final OrderServiceV2 orderService;
    private final MyTraceV2 trace;

    @GetMapping("/v2/request")
    public String request(@RequestParam String itemId){
        TraceStatus status = null;
        try{
            status = trace.begin("OrderController.request");
            orderService.orderItem(status.getTraceId(),itemId);
            trace.end(status);
            return "ok";
        } catch (Exception e){
            trace.exception(status,e);
            throw  e;
        }
    }
}
