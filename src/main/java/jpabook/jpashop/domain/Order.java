package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    //LAZY로 되어있으면 DB에 안감 애초에 member에 손을 안댐, 그렇다해서 null을 넣을 수 없으니 프록시객체를 넣어둠 ByteBuddy
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id") //외래키가 걸림 //Many쪽이 외래키가 걸리고 연관 관계 주인으로 잡으면 편함
    private Member member;


    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL) //persist 시 자동으로 딸려 영속성 저장됨
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id") //1대1 관계에서 연관관계 주인을 Order로 잡음 -> 엑세스를 더 받기 때문, 하지만 상관은 없음 
    private Delivery delivery;

    private LocalDateTime orderDate;

    private OrderStatus status; //주문 상태 [ORDER, CANCEL]

    //==연관관계 메소드
    public void setMember(Member member){
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem){
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery){
        this.delivery = delivery;
        delivery.setOrder(this);
    }

    //==생성 메서드==
    public static Order createOrder(Member member, Delivery delivery, OrderItem... orderItems){
        Order order = new Order();
        order.setMember(member);
        order.setDelivery(delivery);
        for(OrderItem orderItem: orderItems){
            order.addOrderItem(orderItem);
        }
        order.setStatus(OrderStatus.ORDER);
        order.setOrderDate(LocalDateTime.now());
        return order;
    }

    //==비즈니스 로직=//
    /**
     * 주문 취소
     */
    public void cancel(){
        if(delivery.getStatus() == DeliveryStatus.COMP){
            throw new IllegalStateException("이미 배송완료된 상품은 취소가 불가능합니다.");
        }

        this.setStatus(OrderStatus.CANCEL);
        for(OrderItem orderItem : orderItems){
            orderItem.cancel();
        }
    }

    //==조회 로직==

    /**
     * 전체 주문 가격 조회
     * @return
     */
    public int getTotalPrice(){
        int totalPrice = orderItems.stream()
                .mapToInt(OrderItem::getTotalPrice)
                .sum();
//        for(OrderItem orderItem : orderItems){
//            totalPrice += orderItem.getTotalPrice();
//        }
        return totalPrice;
    }

}
