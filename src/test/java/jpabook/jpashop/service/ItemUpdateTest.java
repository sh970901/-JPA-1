package jpabook.jpashop.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jpabook.jpashop.domain.item.Book;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ItemUpdateTest {

    @PersistenceContext
    EntityManager em;

    public void updateTest(){
        Book book = em.find(Book.class, 1L);

        //TX
        book.setName("dad");

        //변경감지 == dirty checking
        //TX commit

    }
}
