package com.example.security.repository;

import com.example.security.models.Orcamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrcamentoRepositorio extends JpaRepository<Orcamento, Long> {
}
