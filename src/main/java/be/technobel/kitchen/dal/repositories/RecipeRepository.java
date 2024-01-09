package be.technobel.kitchen.dal.repositories;

import be.technobel.kitchen.dal.models.entities.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    Optional<Recipe> findById(Long id);
}
