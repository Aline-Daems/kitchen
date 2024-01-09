package be.technobel.kitchen.bl.services.impl;

import be.technobel.kitchen.bl.services.DishService;
import be.technobel.kitchen.dal.models.entities.Dish;
import be.technobel.kitchen.dal.models.entities.Recipe;
import be.technobel.kitchen.dal.repositories.DishRepository;
import be.technobel.kitchen.dal.repositories.RecipeRepository;
import be.technobel.kitchen.pl.forms.DishForm;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishServiceImpl implements DishService {

    private final RecipeRepository recipeRepository;
    private final DishRepository dishRepository;

    public DishServiceImpl( RecipeRepository recipeRepository,
                           DishRepository dishRepository) {

        this.recipeRepository = recipeRepository;
        this.dishRepository = dishRepository;
    }

    @Override
    public void create(DishForm form) {
        if(form == null){
            throw  new IllegalArgumentException("Le formulaire ne peut pas être null");
        }

        Dish dish = new Dish();
        dish.setName(form.name());
        dish.setOrigin(form.origin());
        Recipe recipe = recipeRepository.findById(form.recipe()).orElseThrow(()-> new EntityNotFoundException("Recette non trouvée"));
        dish.setRecipe(recipe);

        dishRepository.save(dish);

    }

    @Override
    public void update(String name, DishForm form) {

        if(form == null){
            throw  new IllegalArgumentException("Le formulaire ne peut pas être null");
        }

        Dish dish = dishRepository.findByName(name);
        dish.setName(form.name());
        dish.setOrigin(form.origin());
        Recipe recipe = recipeRepository.findById(form.recipe()).orElseThrow(()-> new EntityNotFoundException("Recette non trouvée"));
        dish.setRecipe(recipe);

        dishRepository.save(dish);

    }

    @Override
    public List<Dish> getAll() {
        return dishRepository.findAll();
    }

    @Override
    public Dish getOne(String name) {
        return dishRepository.findByName(name);
    }

    @Override
    @Transactional
    public void delete(String name) {

        dishRepository.deleteByName(name);
    }
}
