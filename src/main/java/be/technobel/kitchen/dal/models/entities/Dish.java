package be.technobel.kitchen.dal.models.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Dish {

    @Id

    private String name;
    private String origin;
    @ManyToOne
    @JoinColumn(name = "recetteId")
    private Recipe recipe;
}
