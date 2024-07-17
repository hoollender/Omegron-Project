package com.omegron.repository;

import com.omegron.model.entity.Tractor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TractorRepository extends JpaRepository<Tractor, Long> {
    Optional<Tractor> findById(Long id);
}
