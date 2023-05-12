package com.tiago.boliche.repository.rodada;

import com.tiago.boliche.entity.Rodada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RodadaRepository extends JpaRepository<Rodada, Long> {
}
