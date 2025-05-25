package com.fiap.airlogic.api.repository;

import com.fiap.airlogic.api.model.Reading;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReadingRepository extends JpaRepository<Reading, Long> {
    List<Reading> findBySensorId(String sensorId);
}