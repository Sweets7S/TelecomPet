package ru.fintech.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fintech.example.models.Tariff;

@Repository
public interface TariffRepository extends JpaRepository<Tariff, Integer> {
}
