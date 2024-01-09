package be.technobel.kitchen.pl.forms;

import be.technobel.kitchen.dal.models.entities.Recipe;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public record DishForm(
        String name,
        String origin,
        Long recipe
) {
}
