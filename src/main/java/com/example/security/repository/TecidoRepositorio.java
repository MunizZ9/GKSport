package com.example.security.repository;

import com.example.security.models.Tecido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TecidoRepositorio extends JpaRepository<Tecido, Long> {
}
