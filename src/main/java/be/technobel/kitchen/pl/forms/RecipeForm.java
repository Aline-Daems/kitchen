package be.technobel.kitchen.pl.forms;

import be.technobel.kitchen.dal.models.entities.Author;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;


public record RecipeForm(
        String instructions,
        Long author
) {
}



