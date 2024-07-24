package com.omegron.repository;

import com.omegron.model.entity.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WeatherRepository extends JpaRepository<Weather, Long> {
    Optional<Weather> findByAddress(String address);

    Weather findTopByOrderByIdDesc();

    @Query("SELECT w FROM Weather w JOIN w.days d ORDER BY d.datetime DESC")
    Weather findLatestWeatherByDay();
}