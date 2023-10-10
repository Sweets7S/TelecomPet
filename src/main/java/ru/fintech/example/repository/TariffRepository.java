package ru.fintech.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.fintech.example.models.Tariff;

import java.util.Collection;

@Repository
public interface TariffRepository extends JpaRepository<Tariff, Integer> {

    @Query(value = "SELECT * FROM tariff t WHERE t.active=:active",
            nativeQuery = true)
    Collection<Tariff> findAllByActive(@Param("active") boolean active);
    @Query(value = "SELECT * FROM tariff t WHERE t.name=:name",
            nativeQuery = true)
    Tariff getByName(@Param("name") String name);

}
