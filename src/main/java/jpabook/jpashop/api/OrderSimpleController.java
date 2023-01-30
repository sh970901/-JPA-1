package jpabook.jpashop.api;

import jpabook.jpashop.domain.Order;
import jpabook.jpashop.repository.OrderRepository;
import jpabook.jpashop.repository.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * xToOne 관계의 성능 최적화
 * Order
 * Order -> Member (ManyToOOne)
 * Order -> Delivery (OneToOne)
 */
@RestController
@RequiredArgsConstructor
public class OrderSimpleController {

    private final OrderRepository orderRepository;

    //엔티티를 외부에 반환하지말자... 프록시객체 채울라고 Hibernate5JakartaModule받고 값 넣을라고 강제 초기화까지 함 -> 그렇다고 EAGER 걸면 나중에 큰일남...
    @GetMapping("/api/v1/simple-orders")
    public List<Order> ordersV1(){
        List<Order> all = orderRepository.findAllByString(new OrderSearch());
        for(Order order : all){
            order.getMember().getName(); //Lazy가 강제 초기화됨
            order.getDelivery().getAddress();
        }
        return all;
    }



}
