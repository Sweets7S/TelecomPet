package ru.fintech.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fintech.example.models.Msisdn;

@Repository
public interface MsisdnRepository extends JpaRepository<Msisdn, Integer> {
}
