package be.technobel.kitchen.dal.models.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Contains {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int quantity;

    @ManyToOne
    @JoinColumn(name="recetteId")
    private Recipe recipe;

    @ManyToOne
    @JoinColumn(name="name")
    private Ingredients ingredients;
}
