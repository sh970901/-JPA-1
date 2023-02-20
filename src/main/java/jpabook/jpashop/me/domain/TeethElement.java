package jpabook.jpashop.me.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
public class TeethElement {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "teeth_id")
    private Teeth teeth;

    private String elementName;

    public static TeethElement createTeethElement(String elementName) {
        TeethElement teethElement = new TeethElement();
        teethElement.setElementName(elementName);
        return teethElement;
    }
}
