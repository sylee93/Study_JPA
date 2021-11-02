package Study_JPA.Study_JPA.service;

import Study_JPA.Study_JPA.domain.item.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;

/**
 *  변경감지와 병합 예시
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemUpdateTest {

    @Autowired
    EntityManager em;

    @Test
    public void updateTest() throws Exception {
        Book book = em.find(Book.class, 1);

        //TX(트랜젝션)
        book.setName("asdfsadf");
    }
}
