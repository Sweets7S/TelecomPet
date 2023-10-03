package ru.fintech.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fintech.example.models.Option;

@Repository
public interface OptionRepository extends JpaRepository<Option, Integer> {
}
