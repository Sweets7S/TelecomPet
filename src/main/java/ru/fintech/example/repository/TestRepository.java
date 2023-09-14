package ru.fintech.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fintech.example.models.TestEntity;

@Repository
public interface TestRepository extends JpaRepository<TestEntity, Integer> {
}
