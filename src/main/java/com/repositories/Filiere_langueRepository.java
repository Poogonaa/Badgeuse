package com.repositories;

import com.entities.Filiere_langue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Filiere_langueRepository extends JpaRepository<Filiere_langue, Long> {
}
