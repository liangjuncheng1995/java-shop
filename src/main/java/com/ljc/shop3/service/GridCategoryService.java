package com.ljc.shop3.service;

import com.ljc.shop3.model.GridCategory;
import com.ljc.shop3.repository.GridCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GridCategoryService {

    @Autowired
    private GridCategoryRepository gridCategoryRepository;

    public List<GridCategory> getGridCategoryList() {
        return gridCategoryRepository.findAll();
    }
}
