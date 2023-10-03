package ru.fintech.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fintech.example.models.Sim;

@Repository
public interface SimRepository extends JpaRepository<Sim, Integer> {
}
