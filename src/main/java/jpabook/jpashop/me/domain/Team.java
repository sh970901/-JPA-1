package jpabook.jpashop.me.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
public class Team {

    @Id @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "team")
    private List<People> people = new ArrayList<>();

    private String teamName;
}
