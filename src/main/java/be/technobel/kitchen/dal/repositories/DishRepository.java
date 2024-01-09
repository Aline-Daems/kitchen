package be.technobel.kitchen.dal.repositories;

import be.technobel.kitchen.dal.models.entities.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishRepository extends JpaRepository<Dish, String> {


    Dish findByName(String name);

    void deleteByName(String name);
}
