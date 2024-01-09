package be.technobel.kitchen.pl.controller;

import be.technobel.kitchen.bl.services.DishService;
import be.technobel.kitchen.dal.models.entities.Dish;
import be.technobel.kitchen.pl.dtos.DishDTO;
import be.technobel.kitchen.pl.dtos.RecipeDTO;
import be.technobel.kitchen.pl.forms.DishForm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dish")
public class DishController {

    private DishService dishService;

    public DishController(DishService dishService) {
        this.dishService = dishService;
    }

    @PostMapping("/create")
    public void createDish(@RequestBody DishForm form){
        dishService.create(form);

    }
    @PutMapping("/update/{name}")
    public void updateDish(@PathVariable String name, @RequestBody DishForm form){
        dishService.update(name, form);
    }

    @GetMapping("/{name}")
    public ResponseEntity<DishDTO> getOne(@PathVariable String name){
        Dish dish = dishService.getOne(name);

        return ResponseEntity.ok(DishDTO.fromEntity(dish));
    }

    @GetMapping("/all")
    public ResponseEntity<List<DishDTO>> getAll(){

        List<Dish> dishes = dishService.getAll();

        List<DishDTO> dtos = dishes.stream().map(DishDTO::fromEntity).toList();

        return  ResponseEntity.ok(dtos);
    }

    @DeleteMapping("/delete/{name}")
    public void delete (@PathVariable String name) {
        dishService.delete(name);
    }
}
