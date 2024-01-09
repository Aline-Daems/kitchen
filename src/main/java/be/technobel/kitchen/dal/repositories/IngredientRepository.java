package be.technobel.kitchen.dal.repositories;

import be.technobel.kitchen.dal.models.entities.Dish;
import be.technobel.kitchen.dal.models.entities.Ingredients;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredients, String> {
    Ingredients findByName(String name);

    void deleteByName(String name);
}
