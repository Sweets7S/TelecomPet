package ru.fintech.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fintech.example.models.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
}
