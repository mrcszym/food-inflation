package com.example.springsocial.repository;

import com.example.springsocial.model.Inflation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InflationRepository extends JpaRepository<Inflation, Long> {
    Inflation findInflationByMonth(String month);
}
