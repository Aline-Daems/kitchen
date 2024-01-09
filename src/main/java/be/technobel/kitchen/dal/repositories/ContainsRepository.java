package be.technobel.kitchen.dal.repositories;

import be.technobel.kitchen.dal.models.entities.Contains;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ContainsRepository extends JpaRepository<Contains, Long>, JpaSpecificationExecutor<Contains> {
}