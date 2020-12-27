package com.ljc.shop3.repository;

import com.ljc.shop3.model.Spu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpuResponsitory extends JpaRepository<Spu, Long> {
    Spu findOneById(Long id);
}
