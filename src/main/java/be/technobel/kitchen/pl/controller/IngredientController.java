package be.technobel.kitchen.pl.controller;

import be.technobel.kitchen.bl.services.IngredientService;
import be.technobel.kitchen.dal.models.entities.Ingredients;
import be.technobel.kitchen.pl.dtos.IngredientDTO;
import be.technobel.kitchen.pl.forms.IngredientForm;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ingredient")
public class IngredientController {

    private IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }
    @PreAuthorize("isAnonymous()")
    @PostMapping("/create")
    public void createIngredient(@RequestBody IngredientForm form){

        ingredientService.create(form);
    }
    @PreAuthorize("isAnonymous()")
    @PutMapping("/update/{name}")
    public void updateIngredient(@PathVariable String name, @RequestBody IngredientForm form){
        ingredientService.update(name, form);
    }
    @PreAuthorize("isAnonymous()")
    @GetMapping("/{name}")
    public ResponseEntity<IngredientDTO> getOne(@PathVariable String name){
        Ingredients ingredients = ingredientService.getOne(name);

        return  ResponseEntity.ok(IngredientDTO.fromEntity(ingredients));
    }
    @PreAuthorize("isAnonymous()")
    @GetMapping("/all")
    public ResponseEntity<List<IngredientDTO>> getAll(){

        List<Ingredients> ingredients = ingredientService.getAll();
        List<IngredientDTO> dtos = ingredients.stream().map(IngredientDTO::fromEntity).toList();

        return  ResponseEntity.ok(dtos);
    }
    @PreAuthorize("isAnonymous()")
    @DeleteMapping("/delete/{name}")
    public void delete(@PathVariable String name){
        ingredientService.delete(name);
    }
}
