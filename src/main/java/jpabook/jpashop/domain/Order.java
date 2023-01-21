package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter @Setter
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id") //외래키가 걸림 //Many쪽이 외래키가 걸리고 연관 관계 주인으로 잡으면 편함
    private Member member;


    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "delivery_id") //1대1 관계에서 연관관계 주인을 Order로 잡음 -> 엑세스를 더 받기 때문, 하지만 상관은 없음 
    private Delivery delivery;

    private LocalDateTime orderDate;

    private OrderStatus status; //주문 상태 [ORDER, CANCEL]

}
