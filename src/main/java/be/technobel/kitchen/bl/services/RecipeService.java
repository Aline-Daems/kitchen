package be.technobel.kitchen.bl.services;

import be.technobel.kitchen.dal.models.entities.Recipe;
import be.technobel.kitchen.pl.forms.RecipeForm;

import java.util.List;


public interface RecipeService {

    void create(RecipeForm form);
    void update(Long id, RecipeForm form);

    List<Recipe> getAll();

    Recipe getOne(Long id);
    void delete(Long id);

    void addQuantity(Long recetteId, String ingredientName);
}
