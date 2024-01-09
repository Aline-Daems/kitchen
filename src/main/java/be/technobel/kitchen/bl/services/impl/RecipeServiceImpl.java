package be.technobel.kitchen.bl.services.impl;

import be.technobel.kitchen.bl.services.RecipeService;
import be.technobel.kitchen.dal.models.entities.*;
import be.technobel.kitchen.dal.repositories.AuthorRepository;
import be.technobel.kitchen.dal.repositories.ContainsRepository;
import be.technobel.kitchen.dal.repositories.IngredientRepository;
import be.technobel.kitchen.dal.repositories.RecipeRepository;
import be.technobel.kitchen.pl.forms.RecipeForm;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final AuthorRepository authorRepository;

    private  final IngredientRepository ingredientRepository;
    private final ContainsRepository containsRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository, AuthorRepository authorRepository,
                             ContainsRepository containsRepository) {
        this.recipeRepository = recipeRepository;
        this.authorRepository = authorRepository;
        this.containsRepository = containsRepository;
    }

    @Override
    public void create(RecipeForm form) {

        if(form == null){
            throw  new IllegalArgumentException("Le formulaire ne peut pas être null");
        }

        Recipe recipe = new Recipe();
        recipe.setInstructions(form.instructions());
        Author author = authorRepository.findById(form.author()).orElseThrow(() -> new EntityNotFoundException("Pas trouvé"));
        recipe.setAuthor(author);

        recipeRepository.save(recipe);
    }

    @Override
    public void update(Long id, RecipeForm form) {
        if(form == null){
            throw  new IllegalArgumentException("Le formulaire ne peut pas être null");
        }

        Recipe recipe = recipeRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Id non trouvé"));
        recipe.setInstructions(form.instructions());
        Author author = authorRepository.findById(form.author()).orElseThrow(() -> new EntityNotFoundException("Pas trouvé"));
        recipe.setAuthor(author);

        recipeRepository.save(recipe);
    }


    @Override
    public List<Recipe> getAll() {
        return recipeRepository.findAll();
    }

    @Override
    public Recipe getOne(Long id) {
        return recipeRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public void delete(Long id) {

        recipeRepository.deleteById(id);

    }

    @Override
    public void addQuantity(Long recetteId, String ingredientName, Long id) {
        Recipe recipe = recipeRepository.getOne(recetteId);

        Ingredients ingredients = ingredientRepository.findByName(ingredientName);

        Contains contains = containsRepository.findById(id).orElseGet(()-> {
            Contains contains1 = new Contains();

            contains1.setRecipe(recipe);
            contains1.setIngredients(ingredients);
            contains1.setQuantity(0);
            return containsRepository.save(contains1);
        });

        contains.setQuantity(contains.getQuantity());

        

    }
}
