package Study_JPA.Study_JPA.api;


import Study_JPA.Study_JPA.domain.Order;
import Study_JPA.Study_JPA.repository.OrderRepository;
import Study_JPA.Study_JPA.repository.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * x To one
 * Order
 * Oredr -> Member 연관
 * Order -> Delivery
 */

@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {

    private final OrderRepository orderRepository;

    @GetMapping("/api/v1/simple-orders")
    public List<Order> ordersV1(){
        List<Order> all = orderRepository.findAllByString(new OrderSearch());
        for(Order order : all){
            order.getMember().getName(); // Lazy 강제 초기화
            order.getDelivery().getAddress(); //Lazy 강제 초기화
        }

        return  all;
    }
}
