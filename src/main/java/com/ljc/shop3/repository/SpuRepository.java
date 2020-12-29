package com.ljc.shop3.repository;

import com.ljc.shop3.model.Spu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SpuRepository extends JpaRepository<Spu, Long> {
    Spu findOneById(Long id);

    Page<Spu> findByCategoryIdOrderByCreateTimeDesc(Long cid, Pageable pageable);
    Page<Spu> findByRootCategoryIdOrderByCreateTime(Long cid, Pageable pageable);
//    "selecrt * from spu where category_id = cid"
//    or and like order by > <
}
