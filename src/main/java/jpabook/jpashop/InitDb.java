package jpabook.jpashop;


import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.*;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.me.domain.People;
import jpabook.jpashop.me.domain.Team;
import jpabook.jpashop.me.domain.Teeth;
import jpabook.jpashop.me.domain.TeethElement;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class InitDb {
    private final InitService initService;
    @PostConstruct
    public void init() {
//        initService.dbInit1();
//        initService.dbInit2();
        initService.dbInit3();
//        initService.dbInit4();
    }
    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final EntityManager em;
        public void dbInit1() {
            Member member = createMember("userA", "서울", "1", "1111");
            em.persist(member);
            Book book1 = createBook("JPA1 BOOK", 10000, 100);
            em.persist(book1);
            Book book2 = createBook("JPA2 BOOK", 20000, 100);
            em.persist(book2);
            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 10000, 1);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 20000, 2);
            Order order = Order.createOrder(member, createDelivery(member),
                    orderItem1, orderItem2);
            em.persist(order);
        }
        public void dbInit2() {
            Member member = createMember("userB", "진주", "2", "2222");
            em.persist(member);
            Book book1 = createBook("SPRING1 BOOK", 20000, 200);
            em.persist(book1);
            Book book2 = createBook("SPRING2 BOOK", 40000, 300);
            em.persist(book2);
            Delivery delivery = createDelivery(member);

            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 20000, 3);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 40000, 4);
            Order order = Order.createOrder(member, delivery, orderItem1,
                    orderItem2);
            em.persist(order);
        }
        public void dbInit3(){
            Team team = createTeam("C#");

            People people = People.createPeople(27L, team);
            em.persist(people);

//            TeethElement teethElement1 = TeethElement.createTeethElement("Ca3");
//            TeethElement teethElement2 = TeethElement.createTeethElement("Ca4");
//
//            Teeth teeth = Teeth.createTeeth(people, teethElement1, teethElement2);
//            em.persist(teeth);

        }

        public void dbInit4(){
            Team team2 = createTeam("Yachae");
            em.persist(team2);

            People people2 = People.createPeople(27L, team2);
            em.persist(people2);

            TeethElement teethElement1 = createTeethElement("Ca1");
            TeethElement teethElement2 = createTeethElement("Ca2");

            Teeth teeth2 =Teeth.createTeeth(people2, teethElement1, teethElement2);
            em.persist(teeth2);

        }


        private Team createTeam(String name){
            Team team = new Team();
            team.setTeamName(name);
            return team;
        }

        private TeethElement createTeethElement(String elementName){
            TeethElement teethElement = new TeethElement();
            teethElement.setElementName(elementName);
            return teethElement;
        }











        private Member createMember(String name, String city, String street,
                                    String zipcode) {
            Member member = new Member();
            member.setName(name);
            member.setAddress(new Address(city, street, zipcode));
            return member;
        }
        private Book createBook(String name, int price, int stockQuantity) {
            Book book = new Book();
            book.setName(name);
            book.setPrice(price);
            book.setStockQuantity(stockQuantity);
            return book;
        }
        private Delivery createDelivery(Member member) {
            Delivery delivery = new Delivery();
            delivery.setAddress(member.getAddress());
            return delivery;
        }
    }
}
