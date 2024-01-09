package be.technobel.kitchen.bl.services;

import be.technobel.kitchen.dal.models.entities.Dish;
import be.technobel.kitchen.dal.models.entities.Recipe;
import be.technobel.kitchen.pl.forms.DishForm;
import be.technobel.kitchen.pl.forms.RecipeForm;

import java.util.List;

public interface DishService {

    void create(DishForm form);
    void update(String name, DishForm form);
    List<Dish> getAll();
    Dish getOne(String name);
    void delete(String name);
}
