package Study_JPA.Study_JPA;

import Study_JPA.Study_JPA.domain.*;
import Study_JPA.Study_JPA.domain.item.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

/**
 *  조회용 샘플 데이터 입력 - 총 주문 2개
 * * userA
 *  * JPA1 BOOK
 *  * JPA2 BOOK
 * * userB
 *  * SPRING1 BOOK
 *  * SPRING2 BOOK
 */
@Component
@RequiredArgsConstructor
public class initDb {

    private final InitService initService;

    // PostConstruct 어노테이션에 transectional 어노테이션 먹이는게 잘안될 수 있음
    @PostConstruct
    public void init(){
        initService.doInit1();
        initService.doInit2();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService{

        private final EntityManager em;

        public void doInit1(){
            Member member = createMember("userA", "서울", "1", "1111");

            Book book1 = createBook("JPA1 BOOK", 10000, 100);
            em.persist(book1);

            Book book2 = createBook("JPA2 BOOK", 20000, 100);
            em.persist(book2);

            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 10000, 1);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 20000, 2);

            Order order = createDelivery(member, orderItem1, orderItem2);
            em.persist(order);
        }

        public void doInit2(){
            Member member = createMember("userB", "부산", "2", "2222");

            Book book1 = createBook("SPRING1 BOOK", 20000, 200);
            em.persist(book1);

            Book book2 = createBook("SPRING2 BOOK", 40000, 300);
            em.persist(book2);

            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 20000, 3);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 40000, 4);

            Order order = createDelivery(member, orderItem1, orderItem2);
            em.persist(order);
        }

        private Member createMember(String name, String city, String street, String zipcode) {
            Member member = new Member();
            member.setName(name);
            member.setAddress(new Address(city, street, zipcode));
            em.persist(member); // member 영속상태로 만듦
            return member;
        }

        private Book createBook(String name, int price, int stockQuantity) {
            Book book = new Book();
            book.setName(name);
            book.setPrice(price);
            book.setStockQuantity(stockQuantity);
            return book;
        }

        private Order createDelivery(Member member, OrderItem orderItem1, OrderItem orderItem2) {
            Delivery delivery = new Delivery();
            delivery.setAddress(member.getAddress());
            return Order.createOrder(member, delivery, orderItem1, orderItem2);
        }
    }

}
