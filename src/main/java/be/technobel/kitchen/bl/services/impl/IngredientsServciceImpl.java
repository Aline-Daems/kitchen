package be.technobel.kitchen.bl.services.impl;

import be.technobel.kitchen.bl.services.IngredientService;
import be.technobel.kitchen.dal.models.entities.Ingredients;
import be.technobel.kitchen.dal.repositories.IngredientRepository;
import be.technobel.kitchen.pl.forms.IngredientForm;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientsServciceImpl implements IngredientService {
    private final IngredientRepository ingredientRepository;

    public IngredientsServciceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public void create(IngredientForm form) {
        if(form == null){
            throw  new IllegalArgumentException("Le formulaire ne peut pas être null");
        }

        Ingredients ingredients = new Ingredients();

        ingredients.setName(form.ingredientName());
        ingredients.setMeasure(form.measure());

        ingredientRepository.save(ingredients);
    }

    @Override
    public void update(String name, IngredientForm form) {
        if(form == null){
            throw  new IllegalArgumentException("Le formulaire ne peut pas être null");
        }

        Ingredients ingredients = ingredientRepository.findByName(name);
        ingredients.setName(form.ingredientName());
        ingredients.setMeasure(form.measure());

        ingredientRepository.save(ingredients);
    }

    @Override
    public List<Ingredients> getAll() {
        return ingredientRepository.findAll();
    }

    @Override
    public Ingredients getOne(String name) {
        return ingredientRepository.findByName(name);
    }

    @Override
    public void delete(String name) {
        ingredientRepository.deleteByName(name);
    }
}
