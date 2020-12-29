package com.ljc.shop3.service;


import com.ljc.shop3.model.Spu;
import com.ljc.shop3.repository.SpuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Service
public class SpuService {


//    public static void main(String[] args) {
//        Class<SpuService> spuServiceClass = SpuService.class;
//        System.out.println(Arrays.toString(spuServiceClass.getAnnotations()));
//        System.out.println(Arrays.toString(spuServiceClass.getDeclaredMethods()));
//        System.out.println(Arrays.toString(spuServiceClass.getDeclaredFields()));
//
//        HashMap<String, List> map = new HashMap<>();
//        map.put("str", new ArrayList());
//        System.out.println(map.get("str"));
//        System.out.println(map.get("str2"));
//    }
//
//    static class GenericTyp<E> {
//        public String str;
//        public E generic;
//        public GenericTyp() {
//
//        }
//    }

    @Autowired
    SpuRepository SpuRepository;

    public Spu getSpu(Long id) {
        return this.SpuRepository.findOneById(id);
    }

    public Page<Spu> getLatestPagingSpu(Integer pageNum, Integer size) {
        Class<SpuService> spuServiceClass = SpuService.class;
        Pageable page = PageRequest.of(pageNum, size, Sort.by("createTime").descending());
        return this.SpuRepository.findAll(page);
    }

    public Page<Spu> getByCategory(Long cid, Boolean isRoot, Integer pageNum, Integer size) {
        Pageable page = PageRequest.of(pageNum, size);
        Page<Spu> spuPage = null;
        if(isRoot) {
            return this.SpuRepository.findByRootCategoryIdOrderByCreateTime(cid, page);
        } else {
            return this.SpuRepository.findByCategoryIdOrderByCreateTimeDesc(cid, page);
        }
    }
}
