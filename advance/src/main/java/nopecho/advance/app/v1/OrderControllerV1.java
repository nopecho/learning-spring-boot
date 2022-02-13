package nopecho.advance.app.v1;

import lombok.RequiredArgsConstructor;
import nopecho.advance.trace.TraceStatus;
import nopecho.advance.trace.mytrace.MyTraceV1;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV1 {

    private final OrderServiceV1 orderService;
    private final MyTraceV1 trace;

    @GetMapping("/v1/request")
    public String request(@RequestParam String itemId){
        TraceStatus status = null;
        try{
            status = trace.begin("OrderController.request");
            orderService.orderItem(itemId);
            trace.end(status);
            return "ok";
        } catch (Exception e){
            trace.exception(status,e);
            throw  e;
        }
    }
}
