package be.technobel.kitchen.bl.services;

import be.technobel.kitchen.dal.models.entities.Dish;
import be.technobel.kitchen.dal.models.entities.Ingredients;
import be.technobel.kitchen.pl.forms.DishForm;
import be.technobel.kitchen.pl.forms.IngredientForm;

import java.util.List;

public interface IngredientService {

    void create(IngredientForm form);
    void update(String name, IngredientForm form);
    List<Ingredients> getAll();
    Ingredients getOne(String name);
    void delete(String name);
}

