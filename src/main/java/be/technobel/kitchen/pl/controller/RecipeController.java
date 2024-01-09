package be.technobel.kitchen.pl.controller;

import be.technobel.kitchen.bl.services.RecipeService;
import be.technobel.kitchen.dal.models.entities.Recipe;
import be.technobel.kitchen.pl.dtos.RecipeDTO;
import be.technobel.kitchen.pl.forms.QuantityForm;
import be.technobel.kitchen.pl.forms.RecipeForm;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipe")
public class RecipeController {

    private RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }
    @PreAuthorize("isAnonymous()")
    @PostMapping("/create")
    public void createRecipe(@RequestBody RecipeForm form){
        recipeService.create(form);
    }
    @PreAuthorize("isAnonymous()")
    @PutMapping("/update/{id}")
    public void updateRecipe( @PathVariable Long id, @RequestBody RecipeForm form){

        recipeService.update(id, form);
    }
    @PreAuthorize("isAnonymous()")
    @GetMapping("/{id}")
    public ResponseEntity<RecipeDTO> getOne(@PathVariable Long id){
        Recipe recipe = recipeService.getOne(id);

        return  ResponseEntity.ok(RecipeDTO.fromEntity(recipe));
    }
    @PreAuthorize("isAnonymous()")
    @GetMapping("/all")
    public ResponseEntity<List<RecipeDTO>> getAll(){
        List<Recipe> recipes = recipeService.getAll();

        List<RecipeDTO> dtos = recipes.stream().map(RecipeDTO::fromEntity).toList();

        return ResponseEntity.ok(dtos);
    }
    @PreAuthorize("isAnonymous()")
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        recipeService.delete(id);
    }
    @PreAuthorize("isAnonymous()")
    @PostMapping("/addQuantity/{id}/{ingredientName}")
    public void addQuantity(@PathVariable Long id, String ingredientName, @RequestBody QuantityForm form) {
            recipeService.addQuantity(id, ingredientName, form);
    }
}
