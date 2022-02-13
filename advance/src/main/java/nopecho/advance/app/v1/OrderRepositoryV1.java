package nopecho.advance.app.v1;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import nopecho.advance.trace.TraceStatus;
import nopecho.advance.trace.mytrace.MyTraceV1;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV1 {

    private final MyTraceV1 trace;

    public void save(String itemId){
        TraceStatus status = null;
        try{
            status = trace.begin("OrderRepository.save()");
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
