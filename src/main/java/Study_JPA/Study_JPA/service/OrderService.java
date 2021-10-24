package Study_JPA.Study_JPA.service;

import Study_JPA.Study_JPA.domain.Delivery;
import Study_JPA.Study_JPA.domain.Member;
import Study_JPA.Study_JPA.domain.Order;
import Study_JPA.Study_JPA.domain.OrderItem;
import Study_JPA.Study_JPA.domain.item.Item;
import Study_JPA.Study_JPA.repository.ItemRepository;
import Study_JPA.Study_JPA.repository.MemberRepository;
import Study_JPA.Study_JPA.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    /**
     *  주문
     */
    @Transactional
    public Long order(Long memberId, Long itemId, int count){

        //엔티티 조회
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        //배송정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        //주문상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        //주문 생성
        Order order = Order.createorder(member, delivery, orderItem);

        //주문 저장
        orderRepository.save(order);

        return order.getId();
    }

    /**
     * 주문 취소
     */
    @Transactional
    public void cancelOrder(Long orderId){
        //주문엔티티 생성
        Order order = orderRepository.findOne(orderId);
        //주문취소
        order.cancel(); // 도메인 모델 패턴
        //
    }

    //검색

/*    public List<Order> findOrders(ORderSearch oRderSearch){
        return orderRepository.findAll(oRderSearch);
    }
*/
}
