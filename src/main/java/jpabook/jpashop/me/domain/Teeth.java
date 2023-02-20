package jpabook.jpashop.me.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
public class Teeth {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "people_id")
    private People people;

    @OneToMany(mappedBy = "teeth")
    private List<TeethElement> element = new ArrayList<>();

    private void setPeople(People people){
        this.people = people;
        people.getTeethes().add(this);
    }

    public static Teeth createTeeth(People people, TeethElement ...teethElements) {
        Teeth teeth = new Teeth();
        teeth.setPeople(people);
        for(TeethElement teethElement : teethElements){
            teeth.addElement(teethElement);
        }
        return teeth;
    }

    public void addElement(TeethElement teethElement){
        element.add(teethElement);
        teethElement.setTeeth(this);
    }

}
