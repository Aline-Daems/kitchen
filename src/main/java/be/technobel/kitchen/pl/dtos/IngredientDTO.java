package be.technobel.kitchen.pl.dtos;

import be.technobel.kitchen.dal.models.entities.Ingredients;

public record IngredientDTO(

         String ingredientName,
         String measure
) {

    public static IngredientDTO fromEntity(Ingredients ingredients) {

        return new IngredientDTO(ingredients.getIngredientName(), ingredients.getMeasure());
    }

}
