package ru.fintech.example.repository;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fintech.example.models.Sim;

@Repository
@Hidden
public interface SimRepository extends JpaRepository<Sim, Integer> {
}
