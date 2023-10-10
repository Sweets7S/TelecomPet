package ru.fintech.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.fintech.example.models.Option;

import java.util.Collection;

@Repository
public interface OptionRepository extends JpaRepository<Option, Integer> {
    @Query(value = "SELECT * FROM option o WHERE t.active=:active",
            nativeQuery = true)
    Collection<Option> findAllByActive(@Param("active") boolean active);
    @Query(value = "SELECT * FROM option o WHERE t.name=:name",
            nativeQuery = true)
    Option getByName(@Param("name") String name);
}
