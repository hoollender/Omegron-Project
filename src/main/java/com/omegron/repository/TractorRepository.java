package com.omegron.repository;

import com.omegron.model.entity.Tractor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TractorRepository extends JpaRepository<Tractor, Long> {

}
