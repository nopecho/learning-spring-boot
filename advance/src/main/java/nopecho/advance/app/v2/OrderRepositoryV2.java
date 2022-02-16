package nopecho.advance.app.v2;

import lombok.RequiredArgsConstructor;
import nopecho.advance.trace.TraceId;
import nopecho.advance.trace.TraceStatus;
import nopecho.advance.trace.mytrace.MyTraceV2;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV2 {

    private final MyTraceV2 trace;

    public void save(TraceId traceId, String itemId){
        TraceStatus status = null;
        try{
            status = trace.beginSync(traceId,"OrderRepository.save()");
            if(itemId.equals("ex")){
                throw new IllegalArgumentException("예외 발생!");
            }
            sleep(1000);
            trace.end(status);
        } catch (Exception e){
            trace.exception(status,e);
            throw  e;
        }

    }

    private void sleep(int millis){
        try{
            Thread.sleep(millis);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
