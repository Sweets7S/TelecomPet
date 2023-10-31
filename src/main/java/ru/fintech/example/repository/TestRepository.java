package ru.fintech.example.repository;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fintech.example.models.TestEntity;

@Repository
@Hidden
public interface TestRepository extends JpaRepository<TestEntity, Integer> {
}
