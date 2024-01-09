package be.technobel.kitchen.pl.dtos;

import be.technobel.kitchen.dal.models.entities.Dish;
import be.technobel.kitchen.dal.models.entities.Recipe;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public record DishDTO(

         String name,
         String origin,
         Recipe recipe
) {

    public static DishDTO fromEntity(Dish dish){
        return new DishDTO(dish.getName(), dish.getOrigin(), dish.getRecipe());
    }
}
