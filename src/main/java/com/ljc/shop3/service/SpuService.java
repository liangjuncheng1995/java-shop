package com.ljc.shop3.service;


import com.ljc.shop3.model.Spu;
import com.ljc.shop3.repository.SpuResponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpuService {
    @Autowired
    SpuResponsitory spuResponsitory;

    public Spu getSpu(Long id) {
        return this.spuResponsitory.findOneById(id);
    }

    public List<Spu> getLatestPagingSpu() {
        return this.spuResponsitory.findAll();
    }
}
