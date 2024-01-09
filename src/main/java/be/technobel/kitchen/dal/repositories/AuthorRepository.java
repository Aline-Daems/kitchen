package be.technobel.kitchen.dal.repositories;

import be.technobel.kitchen.dal.models.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findByLogin(String login);
}
