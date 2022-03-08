package com.repositories;

import com.entities.Intervenant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IntervenantRepository extends JpaRepository<Intervenant, Long> {
}
