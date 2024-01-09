package be.technobel.kitchen.pl.dtos;

import be.technobel.kitchen.dal.models.entities.Author;
import be.technobel.kitchen.dal.models.entities.Recipe;


public record RecipeDTO(

        Long recetteId,
        String instructions,
        Author author

) {

    public static RecipeDTO fromEntity(Recipe recipe){
        return new RecipeDTO(recipe.getRecetteId(), recipe.getInstructions(), recipe.getAuthor());
    }
}
