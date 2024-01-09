package be.technobel.kitchen.dal.repositories;

import be.technobel.kitchen.dal.models.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
