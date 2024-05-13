package com.example.security.repository;

import com.example.security.models.Camisa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CamisaRepositorio extends JpaRepository<Camisa, Long> {
}