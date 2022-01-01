package hello.core.order;

public interface orderService {
    Order createOrder(Long memberId,String itemName,int itemPrice);
}
