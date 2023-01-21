package jpabook.jpashop.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;


//JPA의 내장 타입이기 때문에
@Embeddable
@Getter
public class Address {
    private String city;
    private String street;
    private String zipcode;
}
