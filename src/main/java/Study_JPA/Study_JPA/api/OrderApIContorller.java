package Study_JPA.Study_JPA.api;

import Study_JPA.Study_JPA.domain.Order;
import Study_JPA.Study_JPA.domain.OrderItem;
import Study_JPA.Study_JPA.repository.OrderRepository;
import Study_JPA.Study_JPA.repository.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderApIContorller {

    private final OrderRepository orderRepository;

    @GetMapping("/api/v1/orders")
    public List<Order> ordersV1(){
        List<Order> all = orderRepository.findAllByString(new OrderSearch());
        for (Order order : all) {
            order.getMember().getName();
            order.getDelivery().getAddress();
            List<OrderItem> orderItems = order.getOrderItems();

            orderItems.stream().forEach(o -> o.getItem().getName());
        }
        return all;
    }
}
