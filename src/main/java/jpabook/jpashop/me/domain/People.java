package jpabook.jpashop.me.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
public class People {

    @Id
    @GeneratedValue
    private Long id;

    private Long age;


    @OneToMany(mappedBy = "people")
    private List<Teeth> teethes = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    private void setTeam(Team team){
        this.team = team;
        team.getPeople().add(this);
    }

    public static People createPeople(Long age, Team team){
        People people = new People();
        people.setAge(age);
        people.setTeam(team);
        return people;
    }
}
